import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Classe que representa os adversários da batalha.
 * @author i9 Games
 * @version 1.0.0
 */
public abstract class Adversario extends ScrollActor
{
    public static final int MONSTRO = 1;
    public static final int SUB_BOSS = 2;
    public static final int BOSS = 3;
    
    protected String diretorioBatalha;

    protected int vidaMax;
    protected int vida;
    protected int ataque;
    protected int defesa;
    protected boolean morto;
    
    protected BarraVida barraVida;
    
    protected int tipo;
    private int codigo;
    private final String nome;
        
    /**
     * Contrutor que define o estado inicial do adversario
     * @param id nome da pasta que se encontra as imagens para o adversario
     * @param vida quantidade de vida que o adversario tera
     */
    public Adversario(String id, int vida){
        diretorioBatalha = "MONSTROS/"+id+"/posebatalha.png";
        this.nome = id;
        this.vidaMax = vida;
        barraVida = new BarraVida(vida);
    }
    
    /**
     * retorna o tipo de adversario
     * @return inteiro que representa o tipo de Adversario,
     * 1 caso seja Monstro, 2 caso seja Sub_Boss e 3 caso seja BossFinal
     */
    public int getTipo(){
        return this.tipo;
    }
    
    /**
     * configura o codigo identificador
     * @param cod codigo que sera o identificador do adversario
     */
    public void setCodigo(int cod){
        this.codigo = cod;
    }
    
    /**
     * retorna o codigo do adversario
     * @return inteiro identificador do adversario
     */
    public int getCodigo(){
        return this.codigo;
    }
    
    /**
     * metodo que verifica as acoes dos oponentes
     */
    @Override
    public void act() 
    {
        agro();
    }    
    
    /**
     * metodo que chama o metodo lutar do cenario 
     * para que possa se iniciar uma luta entre o jogador e o adversario em questao
     */
    protected void agro(){
        if(getOneIntersectingObject(Jogador.class) != null){
            setImage(diretorioBatalha);
            ((Jogo)getWorld()).lutar(this);
        }
    }
    
    /**
     * metodo que da acao de causar dano ao adversario
     * @param dano quantidade de dano que o adversario esta recebendo
     * @param critical boleano que indica se o dano e um dano critico ou nao
     */
    public abstract void receberDano(int dano, boolean critical);
    
    /**
     * metodo que configura a vida do adversario de acordo com o dano recebido
     * @param dano
     */
    protected void recebeDano(int dano){
        this.vida -= dano;
        if(this.vida < 1){
            this.vida = 0;
            this.morto = true;
        }
        barraVida.setVidaAt(this.vida);
    }
    
    /**
     * retorna barra de vida
     * @return Objeto que representa a barra de vida para que possa ser usada na cena de batalha
     */
    public BarraVida getBarraVida(){
        return barraVida;
    }
    
    /**
     * retorna a vida atual
     * @return inteiro que representa a vida atual do adversario
     */
    public int getVida(){
        return this.vida;
    }
    
    /**
     * retorna a vida maxima 
     * @return inteiro que representa a vida maxima do adversario
     */
    public int getVidaMax(){
        return this.vidaMax;
    }
    
    /**
     * retorna vida perdida
     * @return inteiro que representa a vida perdida do adversario
     */
    public int getVidaPerdida(){
        return this.vidaMax - this.vida;
    }
    
    /**
     * retorna o o atributo morto
     * @return true caso o adversario esteja morto, false caso contrario
     */
    public boolean isDead(){
        return this.morto;
    }
    
    /**
     * configura atributo morto
     * @param morto boleano que indica se o adversario esta morto ou nao
     */
    public void setMorto(boolean morto){
        this.morto = morto;
    }
    
    /**
     * retorna defesa
     * @return inteiro que representa a defesa do adversario
     */
    public int getDefesa(){
        return this.defesa;
    }
    
    /**
     * retorna nome do adversario
     * @return String que indica a pasta onde se encontra as imagens do adversario
     */
    public String getNome(){
        return this.nome;
    }
    
    /**
     * retorna ataque
     * @return inteiro que representa a quantidade de ataque do adversario
     */
    public int ataque(){
        return this.ataque;
    } 
    
    /**
     * Constroi o efeito de dano recebido na cena de luta
     * @param dano inteiro que indica a quantia de dano a ser recebida
     * @param critical boleano que indica se foi um dano critico
     */
    public void efeitoDano(int dano, boolean critical){
        if(dano  <  1){
            ((Jogo)getWorld()).addObject(new Dano("Miss!", Color.BLACK), this.getX(), this.getY()-120);
            Greenfoot.delay(30);
            getWorld().removeObjects(getWorld().getObjects(Dano.class));
        }else{
            if(critical){
                ((Jogo)getWorld()).addObject(new Dano(dano+" Critical!!", Color.BLACK), this.getX(), this.getY()-120);
                Greenfoot.delay(30);
                getWorld().removeObjects(getWorld().getObjects(Dano.class));
            }else{
                ((Jogo)getWorld()).addObject(new Dano(""+dano, Color.BLACK), this.getX(), this.getY()-120);
                Greenfoot.delay(30);
                getWorld().removeObjects(getWorld().getObjects(Dano.class));
            }
        }
    }
}
