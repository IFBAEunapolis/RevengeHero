import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Somativado here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Somativado extends Menus
{
    public Somativado(){
       setImage(((Jogo)getWorld()).idioma+"/somativado.png");
    }
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)){
              ((Jogo)getWorld()).play();
            }
       
    }    
}
