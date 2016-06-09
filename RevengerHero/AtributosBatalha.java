

import greenfoot.*;
import java.awt.Color;
import java.awt.Font;

/**
 * @author i9 GAMES
 */

public class AtributosBatalha extends InterfaceLuta
{
   public static final float tamFonte = 17.0f;
   public final int largura = 215;
   public final int altura = 121;
   
   private int ataque;
   private int magia;
   private int resistencia;
   
   /**
    * @return contrutor - inicia os atributos da classe BarraStatus.
    */   
   public AtributosBatalha(){}
   /**
    * @return contrutor - inicia os atributos da classe BarraStatus.
    * @param ataque int - valor de ataque da barra de status
    * @param magia int - valor de magia da barra de status
    * @param resistencia int - valor de resistencia da barra de status
    */  
   public AtributosBatalha(int ataque,int magia,int resistencia){
       this.ataque=ataque;
       this.magia = magia;
       this.resistencia=resistencia;
       addBarraStatus();
    }
   /**
    * @return void - monta a barra de status
    */
   public void addBarraStatus(){
       GreenfootImage image = new GreenfootImage(largura,altura);
       //Definindo o texto que vai aparecer na barra de Status
       String fontAtaque = "Ataque:";
       String fontMagia = "Magia:";
       String fontResistencia = "Resistencia:";
       
       Font font = image.getFont();
       font = font.deriveFont(tamFonte);
       
       //textos e valores dentro do quadro principal
       image.setColor(new Color(0,0,0,160));
       image.fillRect(5,5,largura,altura);
       image.setFont(font);
       image.setColor(Color.WHITE);
       
       image.drawString(fontAtaque,25,35);
       image.drawString(""+this.ataque,130,35);
       image.drawString(fontMagia,25,65);
       image.drawString(""+this.magia,130,65);
       image.drawString(fontResistencia,25,95);
       image.drawString(""+this.resistencia,130,95);
       
       setImage(image);
    }
   
    /**
     * @return void - passa o valor de força e executa o método addBarraStatus.
     * @param ataque int - valor de ataque da barra de status
     */
    public void setAtaque(int ataque){
        this.ataque=ataque;
        addBarraStatus();
    }
    /**
     * @return void - passa o valor de força e executa o método addBarraStatus.
     * @param ataque magia - valor de magia da barra de status
     */   
     public void setMagia(int magia){
        this.magia=magia;
        addBarraStatus();
    }
    /**
     * @return void - passa o valor de força e executa o método addBarraStatus.
     * @param resistencia magia - valor de resistencia da barra de status
     */   
    public void setResistencia(int resistencia){
        this.resistencia=resistencia;
        addBarraStatus();
    }
}
