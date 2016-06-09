import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Eng here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Eng extends Menus
{
    public Eng(){
        setImage("MENUS/Eng.png");
    }
    public void act() 
    {   if(Greenfoot.mouseClicked(this)){
             ((Jogo)getWorld()).setIdioma("ENG");
        }
    }    
}
