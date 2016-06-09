import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.List;
import java.awt.Color;

/**
 * Classe que representa o personagem escolhido pelo usuário
 * @author i9 Games
 * @version 1.0.0
 */
 
public abstract class Jogador extends ScrollActor implements Habilidades
{
    private final GreenfootSound musicamover;
    
    //atributos para identificação da tabela do banco...
    private int codigo;
    String nome;
    //-------------------------------------------------
        
    private final int cdUltInicial = 5;
    private final int cdHb2Inicial = 4;
    
    // controle de acoes do jogador durante o jogo...........................
    private final int distancia = 2;
    private boolean emMovimento;
    private boolean isDead;
    private boolean inFight;
    protected boolean vezJogar;
    private int countCima, countBaixo, countDireita, countEsquerda; // contadores para utilização da troca de imagens ao mover
    //-----------------------------------------------------------------------
    
    public String[] dirHabs;
        
    public Adversario oponente;
    
    // strings com diretorios das imagens de posicoes:
    private static String sexo;
    private static String classe;
    private static String diretorio;
    
    public CdHb cdHb2;
    public CdHb cdUlt;
    
    // atritubots............................
    public static int atributosRestantes;
    public static int estamina;
    public static int forca;
    public static int inteligencia;
    public static int defesa;
    public static int vidaMax;
    public static int vida;
    public static int ataque;
    public static int magia;
    public static int resistencia;
    //---------------------------------------
    
    protected Random random;
    
    public int cdHabilidade2;
    public int cdUltimate;
    
    protected BarraVida barraVida;
    
    /**
     * Contrutor que defini estado inicial do Jogador
     * @param vidaJog inteiro com a vida que o jogador ira iniciar
     */
    public Jogador(int vidaJog){
        estamina = 0;
        forca = 0;
        inteligencia = 0;
        defesa = 0;
        ataque = 0;
        magia = 0;
        resistencia = 0;
        vidaMax = vidaJog;
        vida = vidaJog;
        
        
        random = new Random();
        emMovimento = false;
        inFight = false;
        vezJogar = false;
        
        barraVida = new BarraVida(Jogador.vidaMax);
        
        dirHabs = new String[4];
        
        musicamover = new GreenfootSound("movimento.wav");
        atributosRestantes = 15;
    }
    
    /**
     * metodo que verifica acoes durante o jogo
     */
    @Override
    public void act() 
    {
        if(!((Jogo)getWorld()).pause() && !inFight && !Jogo.emJogo){
            mover();
            verificaMovimento();
        }
    }
    
    /**
     * incrementa o atributo forca
     */
    public abstract void addForca();

    /**
     * incrementa o atributo inteligencia
     */
    public abstract void addInteligencia();

    /**
     * incrementa o atributo estamina
     */
    public abstract void addEstamina();

    /**
     * incrementa o atributo defesa
     */
    public abstract void addDefesa();
    
    /**
     * @retun emLuta Atributo do tipo boolean que informa quando o jogador está em luta ou não
     */
	 
    public boolean emLuta(){
        return inFight;
    }
    
	/**
	 * Define o nome de usuário do jogador
	 * @param nome Atributo do tipo String que contém o nome de usuário do jogador
	 */
	 
    public void setNome(String nome){
        this.nome = nome;
    }
    
	/**
	 * @return nome Atributo do tipo String que contém o nome de usuário do jogador
	 */
    
    public String getNome(){
        return this.nome;
    }
    
	/**
	 * Define o código do jogador
	 * @param cod Atributo do tipo int que contém o código do jogador
	 */
    
    public void setCodigo(int cod){
        this.codigo = cod;
    }
    
	/**
	 * @return codigo Atributo do tipo int que contém o código do jogador
	 */
    
    public int getCodigo(){
        return this.codigo;
    }
    
	/**
	 * @return sexo Atributo do tipo int que contém o sexo do personagem
	 */
	 
    public String getSexo(){
        return sexo;
    }
    
	/**
	 * @return classe Atributo do tipo String que contém o diretório do personagem escolhido
	 */
	 
    public String getClasse(){
        return classe;
    }
    
	/**
	 * Define o adversário do jogador para cada batalha
	 * @param oponente Atributo que contém o adversário que o jogador enfrentará na batalha
	 */
	 
    public void setOponente(Adversario oponente){
        this.oponente = oponente;
    }
    
	/**
	 * Ataca o adversário quando a vez é do jogador
	 */
	 
    public void atacar(int hb){
        if(vezJogar){
            if(hb == 0){
                habilidade1(); 
                vezJogar = false;
            }else if (hb == 1 && cdHabilidade2 < 1){
                habilidade2();
                cdHabilidade2 = 4;
                vezJogar = false;
            }else if(hb == 2 && cdUltimate < 1){
                ultimate();
                cdUltimate = 5;
                vezJogar = false;
            }
            
            if(!vezJogar){
                cdHabilidade2--;
                cdUltimate--;
                ((Jogo)getWorld()).vezOponente(oponente);
            }
        }
    }
        
        /**
	 * Define o sexo do jogador e atualiza o nome do diretorio
	 * @param sexoJog Atributo que contém o sexo do jogador
	 */
	 
    public void setSexo(String sexoJog){
        sexo = sexoJog;
        atualizaDiretorio();
    }
    
	/**
	 * Define o diretório do personagem
	 * @param classeJog Atributo que contém o diretório do personagem
	 */
	 
    public void setClasse(String classeJog){
        classe = classeJog;
        atualizaDiretorio();
    }
    
	/**
	 * Atualiza o diretorio para pegar o personagem escolhido pelo jogador
	 */
    
    private void atualizaDiretorio(){
        diretorio = sexo+"/"+classe+"/";
    }
    
	/**
	 * @return vida Atributo do tipo inteiro que informa quanto o jogador tem de vida
	 */
	 
    public int getVida(){
        return vida;
    }
    
	/**
	 * @return morto Atributo do tipo boolean que informa se o jogador perdeu a batalha ou não
	 */
    
    public boolean isDead(){
        return isDead;
    }
    
	/**
	 * Coloca o jogador em luta e o deixa em posição de batalha
	 */
	 
    public void entrarLuta(){
        this.inFight = true;
        setImage(diretorio+"Poses/POSEBATALHA.png");
    }
    
	/**
	 * Tira o jogador da luta quando alguém vence
	 */
	 
    public void sairLuta(){
        oponente = null;
        this.inFight = false;
    }
    
	/**
	 * Inicia a batalha
	 */
    
    public void iniciarLuta(){
        cdUltimate = cdUltInicial;
        cdHabilidade2 = cdHb2Inicial;
        cdUlt = new CdHb(""+cdUltimate);
        cdHb2 = new CdHb(""+cdHabilidade2);
        this.inFight = true;
        this.vida = this.vidaMax;
    }
    
	/**
	 * Coloca o jogador quando morto assim que perde uma batalha
	 */
    
    public void morrer(){
        isDead = true;
    }
    
	/**
	 * Muda a vez de jogar para o usuário
	 */
    
    public void vezJogar(){
        this.vezJogar = true;
        if(cdHabilidade2 > 0){
            cdHb2.setText(""+cdHabilidade2);
        }else{
            if(cdHb2 != null) cdHb2.removeImagem();
        }
                
        if(cdUltimate > 0){
            cdUlt.setText(""+cdUltimate);
        }else{
            if(cdUlt != null)cdUlt.removeImagem();
        }
    }
    
	/**
	 * Recebe o dano causado pelo adversário
	 * @param dano Atributo que contém o dano que o jogador recebeu
	 * @param critical Atributo boolean que informa se o dano recebido foi crítico ou não
	 */
	 
    public abstract void receberDano(int dano, boolean critical);
    
	/**
	 * Diminui a vida do jogador quando ele sobre um ataque
	 * @param dano Atributo que contém o dano recebido
	 */
	 
    protected void recebeDano(int dano){
        if(dano > 0){
            vida -= dano;
            if(vida < 1) vida = 0;
            barraVida.setVidaAt(vida);
            if(vida == 0) this.morrer();
        }
    }
    
	/**
	 * Recupera a vida do jogador quando ele sai da luta
	 * @param cura Atributo que informa a quantidade de vida que será adicionada à vida atual do jogador
	 */
	
    public void curar(int cura){
            vida += cura;
            if(vida > vidaMax) vida = vidaMax;
            efeitoCura(cura);
            barraVida.setVidaAt(vida);
    }
    
	/**
	 * Informa a vida que está sendo adicionada à vida do jogador
	 * @param cura Atributo que informa a quantidade de vida que o jogador está recebendo
	 */
	
    private void efeitoCura(int cura){
        ((Jogo)getWorld()).addObject(new Dano(""+cura, new Color(0,125,0)), this.getX(), this.getY()-100);
        Greenfoot.delay(30);
        getWorld().removeObjects(getWorld().getObjects(Dano.class));
    }
    
	/**
	 * Informa a vida que está sendo retirada da vida do usuário
	 * @param dano Atributo que informa a quantidade de vida que o jogador está perdendo
	 * @param critical Atributo que informa se o dano é crítico ou não
	 */
    
    public void efeitoDano(int dano, boolean critical){
        if(dano  <  1){
            ((Jogo)getWorld()).addObject(new Dano("Miss!", Color.BLACK), this.getX(), this.getY()-100);
            Greenfoot.delay(30);
            getWorld().removeObjects(getWorld().getObjects(Dano.class));
        }else{
            if(critical){
                ((Jogo)getWorld()).addObject(new Dano(dano+" Critical!!", Color.BLACK), this.getX(), this.getY()-100);
                Greenfoot.delay(30);
                getWorld().removeObjects(getWorld().getObjects(Dano.class));
            }else{
                ((Jogo)getWorld()).addObject(new Dano(""+dano, Color.BLACK), this.getX(), this.getY()-100);
                Greenfoot.delay(30);
                getWorld().removeObjects(getWorld().getObjects(Dano.class));
            }
        }
    }
    
	/**
	 * @return barraVida Atributo que retorna a barra de vida do usuário
	 */
	 
    public BarraVida getBarraVida(){
        return barraVida;
    }
    
    
	/**
	 * Verifica o movimento do jogador e muda a posição
	 */
	 
    private void verificaMovimento(){
        if(!emMovimento) setImage(diretorio+"Poses/POSE01.png");
    }
    
	/**
	 * Verificação para saber para qual lado o jogador está indo 
	 */
	 
    private void mover(){
        
        if(Greenfoot.isKeyDown("down") && Greenfoot.isKeyDown("right")) movBaixo(distancia); 
           
        if(Greenfoot.isKeyDown("right") && Greenfoot.isKeyDown("up")) movDireita(distancia); 
           
        if(Greenfoot.isKeyDown("left") && Greenfoot.isKeyDown("up")) movCima(distancia); 
           
        if(Greenfoot.isKeyDown("down") && Greenfoot.isKeyDown("left")) movEsquerda(distancia); 
           
        if(Greenfoot.isKeyDown("down") && !Greenfoot.isKeyDown("right") && !Greenfoot.isKeyDown("left")
           && !Greenfoot.isKeyDown("up")) movBaixo(distancia); 
           
        if(Greenfoot.isKeyDown("up") && !Greenfoot.isKeyDown("down") && !Greenfoot.isKeyDown("right")
           && !Greenfoot.isKeyDown("left"))movCima(distancia);
           
        if(Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("down") && !Greenfoot.isKeyDown("right")
           && !Greenfoot.isKeyDown("up")) movEsquerda(distancia);
                    
        if(Greenfoot.isKeyDown("right") && !Greenfoot.isKeyDown("down") && !Greenfoot.isKeyDown("left")
           && !Greenfoot.isKeyDown("up")) movDireita(distancia);
                    
        if(!Greenfoot.isKeyDown("down") && !Greenfoot.isKeyDown("right") && !Greenfoot.isKeyDown("left")
           && !Greenfoot.isKeyDown("up") && !Greenfoot.isKeyDown("a") && !Greenfoot.isKeyDown("s")) emMovimento = false;
    }
    
	/**
	 * Coloca o jogador em movimento
	 * Muda as posições do jogador para a direção esquerda 
	 * Verifica por onde ele está passando
	 * @param distancia Atributo que informa a distancia de cada passo do jogador
	 */
	 
    private void movEsquerda(int distancia){  
        emMovimento = true;
        int x = getX()-distancia;
        int y = getY();
        List colisao1 = getWorld().getObjectsAt(x - 20, y + 20, Colisao.class);
        List colisao2 = getWorld().getObjectsAt(x - 20, y + 45, Colisao.class);
        List colisao3 = getWorld().getObjectsAt(x - 20, y + 20, ObjNivel.class);
        List colisao4 = getWorld().getObjectsAt(x - 20, y + 45, ObjNivel.class);
        countEsquerda++;
        
        if(Jogo.musica)musicamover.play();
        
        if(countEsquerda == 32)countEsquerda =0;
        if(countEsquerda == 4)setImage(diretorio+"Movimentos/L01.png");
        if(countEsquerda == 8)setImage(diretorio+"Movimentos/L02.png");
        if(countEsquerda == 12)setImage(diretorio+"Movimentos/L03.png");
        if(countEsquerda == 16)setImage(diretorio+"Movimentos/L04.png");
        if(countEsquerda == 20)setImage(diretorio+"Movimentos/L05.png");
        if(countEsquerda == 24)setImage(diretorio+"Movimentos/L06.png");
        if(countEsquerda == 28)setImage(diretorio+"Movimentos/L07.png");
        if(countEsquerda == 32)setImage(diretorio+"Movimentos/L08.png");
            
        if (colisao1.isEmpty() && colisao2.isEmpty() && colisao3.isEmpty() && colisao4.isEmpty()) {
            setLocation(x,y);
            
            if(getX() <250){
                
                getWorld().setCameraDirection(0);
                getWorld().moveCamera(-distancia);
            }
        }
    }
    
	/**
	 * Coloca o jogador em movimento
	 * Muda as posições do jogador para a direção direita 
	 * Verifica por onde ele está passando
	 * @param distancia Atributo que informa a distancia de cada passo do jogador
	 */
	 
    private void movDireita(int distancia){
            
        emMovimento = true;
        int x = getX()+distancia;
        int y =  getY();
        List colisao1 = getWorld().getObjectsAt(x + 20, y + 20, Colisao.class);
        List colisao2 = getWorld().getObjectsAt(x + 20, y + 45, Colisao.class);
        List colisao3 = getWorld().getObjectsAt(x + 20, y + 20, ObjNivel.class);
        List colisao4 = getWorld().getObjectsAt(x + 20, y + 45, ObjNivel.class);
        countDireita++;
        
        if(Jogo.musica)musicamover.play();
        
        if(countDireita == 32)countDireita =0;
        if(countDireita == 4)setImage(diretorio+"Movimentos/R01.png");
        if(countDireita == 8)setImage(diretorio+"Movimentos/R02.png");
        if(countDireita == 12)setImage(diretorio+"Movimentos/R03.png");
        if(countDireita == 16)setImage(diretorio+"Movimentos/R04.png");
        if(countDireita == 20)setImage(diretorio+"Movimentos/R05.png");
        if(countDireita == 24)setImage(diretorio+"Movimentos/R06.png");
        if(countDireita == 28)setImage(diretorio+"Movimentos/R07.png");
        if(countDireita == 32)setImage(diretorio+"Movimentos/R08.png");
           
        if (colisao1.isEmpty() && colisao2.isEmpty() && colisao3.isEmpty() && colisao4.isEmpty() && getGlobalX() < 2125) {
            setLocation(x,y);
            
            if(getX() >550){
                
                getWorld().setCameraDirection(0);
                getWorld().moveCamera(distancia);
            }
        }
    }
    
	/**
	 * Coloca o jogador em movimento
	 * Muda as posições do jogador para a direção norte 
	 * Verifica por onde ele está passando
	 * @param distancia Atributo que informa a distancia de cada passo do jogador
	 */
	 
    private void movCima(int distancia){
           
        emMovimento = true;
        int x = getX();
        int y =  getY()-distancia;
        List colisao1 = getWorld().getObjectsAt(x + 20, y + 20, Colisao.class);
        List colisao2 = getWorld().getObjectsAt(x - 20, y + 20, Colisao.class);
        List colisao3 = getWorld().getObjectsAt(x + 20, y + 20, ObjNivel.class);
        List colisao4 = getWorld().getObjectsAt(x - 20, y + 20, ObjNivel.class);
        countCima++;
        
        if(Jogo.musica)musicamover.play();
        
        if(countCima == 32)countCima =0;
        if(countCima == 4)setImage(diretorio+"Movimentos/U01.png");
        if(countCima == 8)setImage(diretorio+"Movimentos/U02.png");
        if(countCima == 12)setImage(diretorio+"Movimentos/U03.png");
        if(countCima == 16)setImage(diretorio+"Movimentos/U04.png");
        if(countCima == 20)setImage(diretorio+"Movimentos/U05.png");
        if(countCima == 24)setImage(diretorio+"Movimentos/U06.png");
        if(countCima == 28)setImage(diretorio+"Movimentos/U07.png");
        if(countCima == 32)setImage(diretorio+"Movimentos/U08.png");
            
        if(colisao1.isEmpty() && colisao2.isEmpty() && colisao3.isEmpty() && colisao4.isEmpty()) {
            setLocation(x,y);
            
            if(getY() < 200){
                
                getWorld().setCameraDirection(90);
                getWorld().moveCamera(-distancia);
            }
        }
    }
   
        /**
	 * Coloca o jogador em movimento
	 * Muda as posições do jogador para a direção sul 
	 * Verifica por onde ele está passando
	 * @param distancia Atributo que informa a distancia de cada passo do jogador
	 */ 
	 
    private void movBaixo(int distancia){
        
        emMovimento = true;
        int x = getX();
        int y =  getY()+distancia;
        List colisao1 = getWorld().getObjectsAt(x + 20, y + 45, Colisao.class); 
        List colisao2 = getWorld().getObjectsAt(x - 20, y + 45, Colisao.class);   
        List colisao3 = getWorld().getObjectsAt(x + 20, y + 45, ObjNivel.class);
        List colisao4 = getWorld().getObjectsAt(x - 20, y + 45, ObjNivel.class);
        countBaixo++;
        
        if(Jogo.musica)musicamover.play();
        
        if(countBaixo == 32)countBaixo =0;
        if(countBaixo == 4)setImage(diretorio+"Movimentos/D01.png");
        if(countBaixo == 8)setImage(diretorio+"Movimentos/D02.png");
        if(countBaixo == 12)setImage(diretorio+"Movimentos/D03.png");
        if(countBaixo == 16)setImage(diretorio+"Movimentos/D04.png");
        if(countBaixo == 20)setImage(diretorio+"Movimentos/D05.png");
        if(countBaixo == 24)setImage(diretorio+"Movimentos/D06.png");
        if(countBaixo == 28)setImage(diretorio+"Movimentos/D07.png");
        if(countBaixo == 32)setImage(diretorio+"Movimentos/D08.png");
           
        emMovimento = true;
             
        if(colisao1.isEmpty() && colisao2.isEmpty() && colisao3.isEmpty() && colisao4.isEmpty() && getGlobalY() < 2105) {
            setLocation(x,y);
            
            if(getY() > 400){
                
                getWorld().setCameraDirection(90);
                getWorld().moveCamera(distancia);
            }
        }
    }
}
