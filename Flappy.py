import pygame
import random

# Définir les dimensions de la fenêtre
WIDTH, HEIGHT = 400, 600
FPS = 30

# Couleurs
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)

# Constantes de saut
JUMP_POWER = -10
GRAVITY = 0.5

# Initialiser pygame et créer la fenêtre
pygame.init()
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Flappy Bird")
clock = pygame.time.Clock()

# Charger les images et les redimensionner
bird_img = pygame.image.load('/Users/mariame/Desktop/MesProjets/Python/bird.png').convert_alpha()
bird_img = pygame.transform.scale(bird_img, (40, 30))
pipe_img = pygame.image.load('/Users/mariame/Desktop/MesProjets/Python/pipe3.png').convert_alpha()
background_img = pygame.image.load('/Users/mariame/Desktop/MesProjets/Python/background.png').convert()
pipe_img = pygame.transform.scale(pipe_img, (50, 300))
background_img = pygame.transform.scale(background_img, (WIDTH, HEIGHT))

# Vérifier le chargement des images
if bird_img is None:
    print("Erreur: Impossible de charger l'image de l'oiseau.")
if pipe_img is None:
    print("Erreur: Impossible de charger l'image du tuyau.")
if background_img is None:
    print("Erreur: Impossible de charger l'image de fond.")

# Classe du joueur (oiseau)
class Bird(pygame.sprite.Sprite):
    def __init__(self):
        super().__init__()
        self.image = bird_img
        self.rect = self.image.get_rect()
        self.rect.center = (100, HEIGHT // 2)
        self.vel_y = 0

    def update(self):
        # Appliquer la gravité
        self.vel_y += GRAVITY
        self.rect.y += self.vel_y

    def jump(self):
        self.vel_y = JUMP_POWER

# Classe des tuyaux
class Pipe(pygame.sprite.Sprite):
    def __init__(self, x, y, inverted=False):
        super().__init__()
        self.image = pipe_img
        self.rect = self.image.get_rect()
        if inverted:
            self.image = pygame.transform.flip(self.image, False, True)
            self.rect.bottomleft = (x, y)
        else:
            self.rect.topleft = (x, y)
        self.vel_x = -5

    def update(self):
        self.rect.x += self.vel_x
        if self.rect.right < 0:
            self.kill()

# Générer les tuyaux
def spawn_pipes():
    y_gap = 500  # Écart vertical entre les tuyaux
    pipe_height = random.randint(50, 300)  # Hauteur aléatoire des tuyaux
    min_pipe_distance = 100  # Distance minimale entre les tuyaux

    # Position du tuyau supérieur (la tête en bas) au bord de l'écran en haut
    pipe_top_y = 0
    # Position du tuyau inférieur au bord de l'écran en bas
    pipe_bottom_y = HEIGHT - pipe_height

    # Créer les nouveaux tuyaux
    pipe_top = Pipe(WIDTH, pipe_top_y + pipe_height, inverted=True)  # Tuyau vers le haut (la tête en bas)
    pipe_bottom = Pipe(WIDTH, HEIGHT - pipe_height, inverted=False)  # Tuyau vers le bas

    # Vérifier que les nouveaux tuyaux ne se superposent pas avec les tuyaux existants
    overlapping = pygame.sprite.spritecollideany(pipe_top, pipes) or pygame.sprite.spritecollideany(pipe_bottom, pipes)
    if not overlapping:
        all_sprites.add(pipe_top)  # Ajouter le tuyau supérieur à tous les sprites
        all_sprites.add(pipe_bottom)  # Ajouter le tuyau inférieur à tous les sprites
        pipes.add(pipe_top)  # Ajouter le tuyau supérieur au groupe de tuyaux
        pipes.add(pipe_bottom)  # Ajouter le tuyau inférieur au groupe de tuyaux

    # Vérifier si la distance entre les tuyaux est suffisante
    while abs(pipe_top_y - pipe_bottom_y) < min_pipe_distance:
        pipe_height = random.randint(50, 300)
        pipe_bottom_y = HEIGHT - pipe_height

    # Supprimer les tuyaux existants s'ils dépassent le nombre maximal
    if len(pipes) >= 3:
        for pipe in pipes:
            pipe.kill()


    pipe_top = Pipe(WIDTH, pipe_top_y + pipe_height, inverted=True)  # Tuyau vers le haut (la tête en bas)
    pipe_bottom = Pipe(WIDTH, pipe_bottom_y, inverted=False)  # Tuyau vers le bas
    all_sprites.add(pipe_top)  # Ajouter le tuyau supérieur à tous les sprites
    all_sprites.add(pipe_bottom)  # Ajouter le tuyau inférieur à tous les sprites
    pipes.add(pipe_top)  # Ajouter le tuyau supérieur au groupe de tuyaux
    pipes.add(pipe_bottom)  # Ajouter le tuyau inférieur au groupe de tuyaux

# Groupes de sprites
all_sprites = pygame.sprite.Group()
pipes = pygame.sprite.Group()

# Créer le joueur
bird = Bird()
all_sprites.add(bird)

# Charger une police
font = pygame.font.Font("Tetris.ttf", 36)

# Importer la police pygame
pygame.font.init()
game_over_font = pygame.font.Font("Tetris.ttf", 36)  # Choisir une taille de police

# Boucle de jeu
running = True
game_over = False  # Variable pour suivre l'état du jeu
while running:
    clock.tick(FPS)

    # Gestion des événements
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        elif event.type == pygame.KEYDOWN:
            if event.key == pygame.K_SPACE:
                if game_over:  # Si le jeu est terminé, recommencer le jeu en appuyant sur la barre d'espace
                    game_over = False
                    bird.rect.center = (100, HEIGHT // 2)  # Réinitialiser la position de l'oiseau
                    for pipe in pipes:
                        pipe.rect.right = 0  # Réinitialiser la position des tuyaux
                    pipes.empty()  # Supprimer tous les tuyaux du groupe
                else:  # Si le jeu est en cours, permettre au joueur de faire sauter l'oiseau
                    bird.jump()

    if not game_over:  # Si le jeu n'est pas terminé
        # Mise à jour des sprites
        all_sprites.update()

        # Générer les tuyaux
        if len(pipes) < 4:
            spawn_pipes()

        # Vérifier si l'oiseau a touché le bas de l'écran ou s'il y a eu une collision avec un tuyau
        if bird.rect.bottom >= HEIGHT or pygame.sprite.spritecollide(bird, pipes, False):
            game_over = True

        # Supprimer les tuyaux hors de l'écran
        pipes = pygame.sprite.Group([pipe for pipe in pipes if pipe.rect.right > 0])

    # Affichage
    screen.blit(background_img, (0, 0))  # Dessiner l'image de fond
    all_sprites.draw(screen)

    if game_over:
        # Dessiner "GAME OVER" à l'écran
        game_over_text = game_over_font.render("GAME OVER", True, (242, 193, 38))  # Couleur rouge
        screen.blit(game_over_text, (WIDTH // 2 - game_over_text.get_width() // 2, HEIGHT // 2 - game_over_text.get_height() // 2))

    pygame.display.flip()

pygame.quit()
