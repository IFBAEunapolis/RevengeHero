import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Opcoes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Opcoes extends Menus
{
   public Opcoes(){
       setImage(((Jogo)getWorld()).idioma+"/b3.png");
    }
    public void act() 
    {
         if(Greenfoot.mouseClicked(this)){
            ((Jogo)getWorld()).menuOpcao();
        }
        // Add your action code here.
    }    
}
