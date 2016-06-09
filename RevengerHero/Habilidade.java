import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Habilidade here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Habilidade extends InterfaceLuta
{
    int habilidade;
    
    public Habilidade(int hab, String imagem){
        setImage(imagem);
        this.habilidade = hab;
    }   
    
    public void act(){
        if(Greenfoot.mouseClicked(this)){
            ((Jogo)getWorld()).jogador.atacar(this.habilidade);
        }
    }
    
    public int getHabilidade(){
        return habilidade;
    }
}
