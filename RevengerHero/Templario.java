import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Templario here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Templario extends Menus
{
    /**
     * Act - do whatever the Templario wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Templario(){
        setImage(Jogo.idioma+Jogo.diretorio +"/Templario.png");
    }
    
    @Override
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)){
            ((Jogo)getWorld()).jogador = new Crusader();
            ((Jogo)getWorld()).montarCenario();
        }
    }    
}
