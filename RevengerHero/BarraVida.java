

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
/**
 * Write a description of class BarraVida here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BarraVida extends Actor
{
    public static final float TamFonte = 18.0f;
    public final int larguraMax = 250;
    public final int altura = 30;
    protected int vidaMax;
    protected int vidaAt;
      
    
    public BarraVida(int vida){
        setVidaMax(vida);
        setVidaAt(vida);
        addBarra();
    }
    
    
    public void addBarra(){
        int barraAt =((larguraMax*vidaAt)/vidaMax);
        //Códigos para a montagem da barra de Life
        GreenfootImage image = new GreenfootImage(larguraMax,altura);      
        image.setColor(new Color(0,0,0,160));
        image.fillRect(0,0,larguraMax,altura);
        image.setColor(new Color(255,0,0));
        image.fillRect(5,5,barraAt-10,altura-10);
        
        Font font = image.getFont();
        font = font.deriveFont(TamFonte);
        String atual = ""+vidaAt;
        String max = ""+vidaMax;
        image.setFont(font);
        image.setColor(Color.WHITE);
        image.drawString(atual+"/"+max,95,22);
        setImage(image);
    }
        
    public void setVidaMax(int vida){
        this.vidaMax=vida;
    }
    
    public void setVidaAt(int vida){
        this.vidaAt=vida;
        addBarra();
    }
}
