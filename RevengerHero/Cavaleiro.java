import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cavaleiro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cavaleiro extends Menus
{
    public Cavaleiro(){
        setImage(((Jogo)getWorld()).idioma+((Jogo)getWorld()).diretorio +"/Cavaleiro.png");
    }
    
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)){
            ((Jogo)getWorld()).jogador = new Knight();
            ((Jogo)getWorld()).montarCenario();
        }
    }    
}
