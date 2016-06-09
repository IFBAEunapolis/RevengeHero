import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Voltar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Voltar extends Menus
{
    public Voltar(){
     setImage(((Jogo)getWorld()).idioma+"/voltar.png");
    }
    public void act() 
    {
         if(Greenfoot.mouseClicked(this)){
             ((Jogo)getWorld()).menuVoltar();
            }
        
    }    
}
