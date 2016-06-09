import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;

public class BtAdd extends AddAtributos
{
    GreenfootImage fundo;
    GreenfootImage imagem;
    Font fonte;
     
    public BtAdd(){
        fundo = new GreenfootImage(2, 20);
        imagem = new GreenfootImage("+", 32, Color.WHITE, null, Color.RED);
        fundo.setTransparency(255);
        fundo.setColor(Color.BLACK);
        fundo.fill();
        fundo.drawImage(imagem, 1, -12);
        setImage(imagem);
    }    
}
