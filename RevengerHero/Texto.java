

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;

public class Texto extends InterfaceLuta
{
    GreenfootImage imagem;
    Font fonte;
    private static final Color textColor = Color.WHITE;
    
    public Texto(String texto){
        imagem = new GreenfootImage(texto, 42, textColor, null);
        setImage(imagem);
    }    
}
