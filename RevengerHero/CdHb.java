

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;

public class CdHb extends InterfaceLuta
{
    GreenfootImage fundo;
    GreenfootImage imagem;
    Font fonte;
    private static final Color textColor = Color.BLACK;
    
    public CdHb(String texto){
        fundo = new GreenfootImage(80, 80);
        imagem = new GreenfootImage(texto, 92, textColor, null, Color.RED);
        fundo.setTransparency(200);
        fundo.setColor(Color.WHITE);
        fundo.fill();
        fundo.drawImage(imagem, 20, -10);
        setImage(fundo);
    }    
    
    public void setText(String texto){
        fundo = new GreenfootImage(80, 80);
        imagem = new GreenfootImage(texto, 92, textColor, null, Color.RED);
        fundo.setTransparency(200);
        fundo.setColor(Color.WHITE);
        fundo.fill();
        fundo.drawImage(imagem, 20, -10);
        setImage(fundo);
    }
    
    public void removeImagem(){
        fundo = new GreenfootImage(1, 1);
        setImage(fundo);
    }
}
