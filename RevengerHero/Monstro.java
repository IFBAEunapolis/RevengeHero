import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * Classe que representa o adversário Monstro
 * @author i9 Games
 * @version 1.0.0
 */
 
public class Monstro extends Adversario
{
    private String id;
    
    public Monstro(String id, int vida, int ataque, int defesa){
        super(id, vida);
        this.id = id;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        morto = false;
        setImage("MONSTROS/"+id+"/pose01.png");
        this.tipo = MONSTRO;
    }
    
	/**
	 * Causa dano no Monstro
	 * @param dano Informa o dano recebido
	 * @param critical Informa se o dano é crítico ou não
	 */
    
    public void receberDano(int dano, boolean critical){
        int damage = dano - (dano*this.defesa) /100;
        efeitoDano(damage, critical);
        recebeDano(damage);
    }
}
