import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class fundo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fundo extends ScrollActor
{
    GreenfootImage imagem;
    public Fundo(){
        imagem = new GreenfootImage("MAPAS/fase1/cenario1.png");
        setImage(imagem);
    }  
}
