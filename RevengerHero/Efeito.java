

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Efeito here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Efeito extends InterfaceLuta
{
    GreenfootImage imagem;
    
    public Efeito(String image){
        imagem = new GreenfootImage(image);
        setImage(imagem);
    }
}
