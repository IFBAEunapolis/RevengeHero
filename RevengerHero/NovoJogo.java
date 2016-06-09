import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NovoJogo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NovoJogo extends Menus
{
    public NovoJogo(){
         setImage(((Jogo)getWorld()).idioma+"/b1.png");
    }
    public void act() 
    {
       if(Greenfoot.mouseClicked(this)){
            ((Jogo)getWorld()).menuNome();
        }
    }    
}
