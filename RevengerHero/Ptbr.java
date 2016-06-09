import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ptbr here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ptbr extends Menus
{
   public Ptbr(){
    setImage("MENUS/Pt.png");
    }
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)){
            ((Jogo)getWorld()).setIdioma("PT");
        }
    }    
}
