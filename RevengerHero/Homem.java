
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Homem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Homem extends Menus
{
    /**
     * Act - do whatever the Homem wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Homem(){
        setImage(((Jogo)getWorld()).idioma+"/Homem.png");
    }
    
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)){
            ((Jogo)getWorld()).jogador.setSexo("MALE");
            ((Jogo)getWorld()).setDiretorioSexo("MALE");
            ((Jogo)getWorld()).menuEscolherClasse();
        }
    } 
}
