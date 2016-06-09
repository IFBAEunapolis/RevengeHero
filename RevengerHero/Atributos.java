import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;

public class Atributos extends AddAtributos
{
    BtAdd[] btAdd;
    Font fonte;
    
    public Atributos(){
        btAdd = new BtAdd[4];
        btAdd[0] = new BtAdd();
        btAdd[1] = new BtAdd();
        btAdd[2] = new BtAdd();
        btAdd[3] = new BtAdd();
        
        atualizaBarra();
    }
    
    public void act()
    {
        if(Jogador.atributosRestantes == 0) {
            getWorld().removeObjects(getWorld().getObjects(BtAdd.class));
        }
                
        if(Greenfoot.mouseClicked(btAdd[0])) {
            ((Jogo)getWorld()).jogador.addForca();
            Jogador.atributosRestantes--;
            atualizaBarra();
        }
        
        if(Greenfoot.mouseClicked(btAdd[1])) {
            ((Jogo)getWorld()).jogador.addInteligencia();
            Jogador.atributosRestantes--;
            atualizaBarra();
        }
        
        if(Greenfoot.mouseClicked(btAdd[2])) {
            ((Jogo)getWorld()).jogador.addEstamina();
            Jogador.atributosRestantes--;
            atualizaBarra();
        }
        
        if(Greenfoot.mouseClicked(btAdd[3])) {
            ((Jogo)getWorld()).jogador.addDefesa();
            Jogador.atributosRestantes--;
            atualizaBarra();
        }
   }
    
    public void mostrar(){
        if(Jogador.atributosRestantes > 0 ){
            ((Jogo)getWorld()).addObject(btAdd[0], 615, 230);
            ((Jogo)getWorld()).addObject(btAdd[1], 615, 260);
            ((Jogo)getWorld()).addObject(btAdd[2], 615, 290);
            ((Jogo)getWorld()).addObject(btAdd[3], 615, 320);
        }
        atualizaBarra();
    }
    
    public void atualizaBarra(){
       GreenfootImage image = new GreenfootImage(480, 175);
       
       fonte = new Font(Font.MONOSPACED, 0, 18);
       
       image.setFont(fonte);
       
       int forca = Jogador.forca;
       int inteligencia = Jogador.inteligencia;
       int defesa = Jogador.defesa;
       int estamina = Jogador.estamina;
       
       int ataque = Jogador.ataque;
       int magia = Jogador.magia;
       int vida = Jogador.vidaMax;
       int resistencia = Jogador.resistencia;
       
       image.fill();
       
       image.setColor(Color.DARK_GRAY);
       
       image.fillRect(5, 5, 470, 165);
       
       image.setColor(Color.WHITE);
       
       image.drawString("Forca:", 235, 25);
       image.drawString(""+forca, 400, 25);
       image.drawString("Inteligencia:", 235, 55);
       image.drawString(""+inteligencia, 400, 55);
       image.drawString("Estamina:", 235, 85);
       image.drawString(""+estamina, 400, 85);
       image.drawString("Defesa:", 235, 115);
       image.drawString(""+defesa, 400, 115);
       
       image.drawString("Ataque:      ", 20, 25);
       image.drawString(""+ataque, 175, 25);
       image.drawString("Magia:       ", 20, 55);
       image.drawString(""+magia, 175, 55);
       image.drawString("Vida:        ", 20, 85);
       image.drawString(""+vida, 175, 85);
       image.drawString("Resistencia: ", 20, 115);
       image.drawString(""+resistencia, 175, 115);
       
       
       fonte = new Font(Font.MONOSPACED, Font.BOLD, 18);
       image.setFont(fonte);
       
       image.drawString("Pontos restantes: ", 150, 160);
       image.drawString(""+Jogador.atributosRestantes, 350, 160);
       
       setImage(image);
    }    
}
