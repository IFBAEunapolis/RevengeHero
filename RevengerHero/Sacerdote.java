import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Sacerdote here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sacerdote extends Menus
{
    /**
     * Act - do whatever the Sacerdote wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Sacerdote(){
        setImage(Jogo.idioma+Jogo.diretorio +"/Sacerdote.png");
    }
    
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)){
            ((Jogo)getWorld()).jogador = new Priest();
            ((Jogo)getWorld()).montarCenario();
        }
    }    
}
