import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Continuar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Continuar extends Menus
{
    public Continuar(){
        setImage(((Jogo)getWorld()).idioma+"/b2.png");
    }
    
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)){
            ((Jogo)getWorld()).menuContinuar();
        }
    }    
}
