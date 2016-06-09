import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BotaoCadastrar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BotaoCadastrar extends Menus
{
    /**
     * Act - do whatever the BotaoCadastrar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public BotaoCadastrar(){
        setImage("MENUS/cadastrar.png");
    }
    
    @Override
    public void act() 
    {
        // Add your action code here.
        if(Greenfoot.mouseClicked(this)){
            if(!"".equals(((Jogo)getWorld()).getTexto()) && ((Jogo)getWorld()).getTexto().length() > 2 && ((Jogo)getWorld()).getTexto().length() < 11){
                ((Jogo)getWorld()).inicioJogo();
            }
        }
    }    
}
