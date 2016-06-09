

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
/**
 * Write a description of class Dano here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dano extends InterfaceLuta
{
    GreenfootImage imagem;
    Font fonte;
    
    public Dano(String dano, Color corTexto){
        imagem = new GreenfootImage(dano, 32, corTexto, Color.LIGHT_GRAY);
        setImage(imagem);
    }
}
