import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mudo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mudo extends Menus
{
    public Mudo(){
        setImage(((Jogo)getWorld()).idioma+"/somdesativado.png");
    }
    public void act() 
    {
         if(Greenfoot.mouseClicked(this)){
              ((Jogo)getWorld()).mudo();
            
            }
        // Add your action code here.
    }    
}
