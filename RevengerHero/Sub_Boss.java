import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * Classe que representa o adversário Sub_Boss
 * @author i9 Games
 * @version 1.0.0
 */
 
public class Sub_Boss extends Adversario
{
    private String id;
    
    boolean emMovimento;
    
    public Sub_Boss(String id, int vida, int ataque, int defesa){
        super(id, vida);
        emMovimento = false;
        this.id = id;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        morto = false;
        setImage("MONSTROS/"+id+"/pose01.png");
        this.tipo = SUB_BOSS;
    }
    
	/**
	 * Causa dano no Sub_Boss
	 * @param dano Informa o dano recebido
	 * @param critical Informa se o dano é crítico ou não
	 */
    public void receberDano(int dano, boolean critical){
        int damage = dano - (dano*this.defesa) /100;
        efeitoDano(damage, critical);
        recebeDano(damage);
    }
    
        /**
	 * @return damage Atributo que informa o dano causado no jogador
	 */
    
    @Override
    public int ataque(){
        int damage = 0;
        
        Random r = new Random();
        
        if(r.nextBoolean()){
            damage = (this.ataque*4)/3;
        }else{
            damage = this.ataque;
        }
        
        return damage;
    }
}
