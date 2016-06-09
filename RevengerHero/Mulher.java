import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mulher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mulher extends Menus
{
    /**
     * Act - do whatever the Mulher wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Mulher(){
        setImage(((Jogo)getWorld()).idioma+"/Mulher.png");
    }
    
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)){
            ((Jogo)getWorld()).jogador.setSexo("FEMALE");
            ((Jogo)getWorld()).setDiretorioSexo("FEMALE");
            ((Jogo)getWorld()).menuEscolherClasse();
        }
    }    
}
