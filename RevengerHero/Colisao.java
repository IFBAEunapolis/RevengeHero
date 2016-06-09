 

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Colisao here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Colisao extends ScrollActor
{
    GreenfootImage imagem;
    public Colisao(int x, int y){
        imagem = new GreenfootImage(x, y);
        imagem.setTransparency(0);
        imagem.fill();
        this.setImage(imagem);
    }
}
