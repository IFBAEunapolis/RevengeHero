import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mago here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mago extends Menus
{
    /**
     * Act - do whatever the Mago wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Mago(){
        setImage(Jogo.idioma+Jogo.diretorio +"/Mago.png");
    }
    
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)){
            ((Jogo)getWorld()).jogador = new Wizard();
            ((Jogo)getWorld()).montarCenario();
        }
    }    
}
