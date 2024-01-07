#Jeu Pong difficulté supposée : abordable
#By @mariame1810
#Objectif : utiliser Turtle


import turtle

wn = turtle.Screen()
wn.title("Pong by @mariame1810")
wn.bgcolor("plum")
wn.setup(width=800,height=600)
wn.tracer(0)

#Score
score_a = 0
score_b = 0

# Premiere raquette
raquette_a = turtle.Turtle()
raquette_a.speed(0) #la vitesse de la raquette relative au mouvement
raquette_a.shape("square")#la forme
raquette_a.color("lavender")#la couleur
raquette_a.shapesize(stretch_wid=5, stretch_len=1)#la taille de la forme de la requette
raquette_a.penup() #ne pas avoir la ligne par défaut cf documentation package turtle
raquette_a.goto(-350,0) #emplacement de départ de la raquette

# Deuxieme raquette
raquette_b = turtle.Turtle()
raquette_b.speed(0) #la vitesse de la raquette relative au mouvement
raquette_b.shape("square")#la forme
raquette_b.color("lavender")#la couleur
raquette_b.shapesize(stretch_wid=5, stretch_len=1)#la taille de la forme de la requette
raquette_b.penup() #ne pas avoir la ligne par défaut cf documentation package turtle
raquette_b.goto(350,0) #emplacement de départ de la raquette

# Balle
balle = turtle.Turtle()
balle.speed(0) #la vitesse de la raquette relative au mouvement
balle.shape("circle")#la forme
balle.color("mediumorchid")#utilisation de la docu Turtle pour les noms de couleur car code rvb non reconnu
balle.penup() #ne pas avoir la ligne par défaut cf documentation package turtle
balle.goto(0,0) #emplacement de départ de la balle : milieu
balle.dx = 2 #d pour delta en gros la difference
balle.dy = -2

# Afficher score avec pen
pen = turtle.Turtle() #cf docu turtle
pen.speed(0)#0 car l'objet ne va pas bouger
pen.color("mediumorchid") #couleur de l'objet
pen.penup()
pen.hideturtle()#on cache l'objet on uniquement qu'il affiche le score
pen.goto(0,260) #on place le score en haut au milieu
pen.write("Joueur A : 0 - Joueur B : 0", align="center",font=("Courier",24,"normal"))



#Fonctions

#mouvement vers le haut et le bas de la raquette a
def raquette_a_up():
    y = raquette_a.ycor() #methode sans argument qui renvoie l'ordonnée de la raquette_a
    y += 20 #ajoute 20 pixels au mouvement de la raquette_a
    raquette_a.sety(y) #change l'ordonnee

def raquette_a_down():
    y = raquette_a.ycor() #methode sans argument qui renvoie l'ordonnée de la raquette_a
    y -= 20 #retire 20 pixels au mouvement de la raquette_a
    raquette_a.sety(y) #change l'ordonnee

#mouvement vers le haut puis le bas de la raquette b
def raquette_b_up():
    y = raquette_b.ycor() #methode sans argument qui renvoie l'ordonnée de la raquette_b
    y += 20 #ajoute 20 pixels au mouvement de la raquette_a
    raquette_b.sety(y) #change l'ordonnee

def raquette_b_down():
    y = raquette_b.ycor() #methode sans argument qui renvoie l'ordonnée de la raquette_b
    y -= 20 #retire 20 pixels au mouvement de la raquette_a
    raquette_b.sety(y) #change l'ordonnee




#Raccourci clavier : pour associer une touche a un mouvement
wn.listen()
wn.onkeypress(raquette_a_up, "w")
wn.onkeypress(raquette_a_down, "x")
wn.onkeypress(raquette_b_up, "b")
wn.onkeypress(raquette_b_down, "n")

#Boucle principale du jeu
while True:
    wn.update()

    #Faire bouger la balle
    balle.setx(balle.xcor() + balle.dx) #depend de la vitesse de l'ordinateur mais permet de faire bouger la balle en abcisse
    balle.sety(balle.ycor() + balle.dy) #identique mais pour l'ordonnée

    #Limiter la balle a l'écran : la faire rebondir en ordonnée
    #En haut
    if balle.ycor() > 290 : # condition sur l'ordonne (taille de l'ecran 300 px si ca dépasse 290 on la fait revenir)
        balle.sety(290) #change taille de l'odonnée en 290px
        balle.dy *= -1 #change la direction de la balle en inversant son ordonnée donc la balle revient
    #En bas
    if balle.ycor() < -290 : # condition sur l'ordonne (taille de l'ecran 300 px si ca dépasse 290 on la fait revenir)
        balle.sety(-290) #change taille de l'odonnée en 290px
        balle.dy *= -1 #change la direction de la balle en inversant son ordonnée donc la balle revient

    #Limiter la balle à l'écran : la faire rebondir en abcisse
    #A droite
    if balle.xcor() > 390:
        balle.goto(0,0)
        balle.dx *= -1
        score_a += 1 #augmente d'un point si la balle sort de droite le score du joueur a
        pen.clear() #clear le pen pour pouvoir le reafficher avec un nouveau score mais tellement rapide qu'inperceptible
        pen.write("Joueur A : {} - Joueur B : {}".format(score_a,score_b), align="center",font=("Courier",24,"normal"))

    #A gauche
    if balle.xcor() < -390:
        balle.goto(0,0)
        balle.dx *=-1
        score_b +=1 #idem mais pour le joueur b
        pen.clear() #idem
        pen.write("Joueur A : {} - Joueur B : {}".format(score_a,score_b), align="center",font=("Courier",24,"normal"))

    #raquette et balle se rencontrent <3
    #rebondissement raquette de gauche
    if (balle.xcor() > 340 and balle.xcor() < 350) and (balle.ycor() < raquette_b.ycor() + 40 and balle.ycor() > raquette_b.ycor() - 40):
        balle.setx(340)
        balle.dx *= -1
    #rebondissement raquette de droite
    if (balle.xcor() < -340 and balle.xcor() < -350) and (balle.ycor() < raquette_a.ycor() + 40 and balle.ycor() > raquette_a.ycor() - 40):
        balle.setx(-340)
        balle.dx *= -1
