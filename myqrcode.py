#mini code pour generer un qr code assez simple je testait le paquet qrcode je l'ai fait sur venv
import modules
import qrcode
#Methode 1 
var = qrcode.make("https://github.com/mariame1810")
var.save('qrcode.png')

# Methode 2 
qr = qrcode.QRCode(version=3,
error_correction=qrcode.constants.ERROR_CORRECT_L,
box_size=5,
border=5,)
qr.add_data("https://github.com/mariame1810")
qr.make(fit=True)
var = qr.make_image(fill_color="black", back_color= "black")

