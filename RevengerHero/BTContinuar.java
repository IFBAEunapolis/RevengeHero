import greenfoot.*;
import java.awt.Color;
import java.awt.Font;

/**
 * @author Matheus Thales <mtxthales@hotmail.com>
 */

public class BTContinuar extends Menus{

    String classe;
    String nome_jogador;
    int cod_mundo;
    
    public BTContinuar(String classe, String jogador, int cod_mundo){
        this.classe = classe;
        this.nome_jogador = jogador;
        this.cod_mundo = cod_mundo;
        preencher();
    }
    
    private void preencher(){
        GreenfootImage imagem = new GreenfootImage(nome_jogador+"->"+classe+" cod:"+cod_mundo, 32, Color.white, null, Color.red);
        imagem.setFont(new Font(Font.MONOSPACED, 1, 44));
        GreenfootImage fundo = new GreenfootImage("MENUS/btcontinuar.png");
        fundo.drawImage(imagem, 30, 18);
        setImage(fundo);
    }
    
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)){
            ((Jogo)getWorld()).carregar(cod_mundo);
        }
    } 
}
