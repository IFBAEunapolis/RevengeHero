

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ajuda here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ajuda extends Menus
{
   public Ajuda(){
        setImage(((Jogo)getWorld()).idioma+"/b4.png");
   }
    
    public void act() 
    { 
        if(Greenfoot.mouseClicked(this)){
            ((Jogo)getWorld()).menuAjuda();
        }
    }    
}
