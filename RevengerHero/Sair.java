import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Sair here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sair extends Menus
{
  public Sair(){
      setImage(((Jogo)getWorld()).idioma+"/b5.png");
    } 
    public void act() 
    {
         if(Greenfoot.mouseClicked(this)){
        
             System.exit(0);
          
        }
        // Add your action code here.
    }    
}
