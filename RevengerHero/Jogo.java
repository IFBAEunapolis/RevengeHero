import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsável por montar e controlar o cenário do jogo
 * @author i9 Games
 * @version 1.0.0
 */

public class Jogo extends ScrollWorld
{
    public Jogador jogador ;
    
    private boolean pause;
    
    // atributos para conexao com o banco................
    private final String base = "jdbc:mysql://localhost/dbRevenge";
    private final String user = "root";
    private final String pass = "caiobabler";
    //----------------------------------------------------
    
    private final GreenfootSound musicadefundo;
    private final GreenfootSound musicadefundofloresta;
    private final GreenfootSound musicabatalha;
    public static boolean musica =true;
    
    // controle de diretorios para interfaces...................................
    public static String diretorio;
    public static String idioma="MENUS/PT";
    public static String classeHabilidade;
    //--------------------------------------------------------------------------
    
    // Interfaces, telas e menus................................................
    private MenuPrincipal menuprincipal;
    private NovoJogo novojogo;
    private Sair sair;
    private Opcoes opcoes;
    private Ajuda ajuda;
    private Continuar continuar;
    private Sexo sexo;
    private Homem homem;
    private Mulher mulher;
    private Classe classe;
    private Cavaleiro cavaleiro;
    private Mago mago;
    private Sacerdote sacerdote;
    private Templario templario;
    private AjudaMenu ajudamenu;
    private Voltar volta;
    private Pergaminho pergaminho;
    private opcoesAudio  opcaoAudio;
    private Mudo mudo;
    private Ptbr pt;
    private Eng eng;
    private Somativado somAtivado;
    private Telas pergLeitura;
    private Fundo fundoCenario;
    private MenuHabilidade menuHabilidade;
    private MenuHabilidadePassiva menuHabilidadePassiva;
    private MenuHabilidadeAtaque1 menuHabilidadeAtaque1;
    private MenuHabilidadeAtaque2 menuHabilidadeAtaque2;
    private MenuHabilidadeAtaque3 menuHabilidadeAtaque3;
    private TextField texto;
    private BotaoCadastrar botaoCad;
    private FundoMenuCadastro fundoCadastro;
    private Passiva hbP;
    private Habilidade hb1;
    private Habilidade hb2;
    private Habilidade hbU;
    private Texto txtBatalha;
    private final Atributos atributos;
    //--------------------------------------------------------------------------
        
    // atributos para controle das acoes durante o jogo.........................
    public static boolean emJogo;
    public boolean duranteJogo;
    public boolean emMenu;
    //--------------------------------------------------------------------------
    
    private int advMortos;
    
    //atributos para controle de jogos salvos.........
    private int atCamX, atCamY, atJogX, atJogY;
    private int nivel;
    private int codigo;
    private boolean salvo;
    //------------------------------------------------
    
    private ArrayList<Colisao> colisao;
    private ArrayList<Adversario> oponente;
    private ArrayList<ObjNivel> listObj;
    
    public Jogo()
    {    
        super(800, 600, 1, 2144, 2144);
                
        setBackground("rivets.jpg");
        jogador = new Knight();
        
        musicadefundo  = new GreenfootSound("musica.wav");
        musicadefundofloresta  = new GreenfootSound("somfundo.wav");
        musicabatalha = new GreenfootSound("musicabatalha.wav");
        musica = true;
        
        menuPrincipal();
        pause = true;
        criarColisoesC1();
        criarOponentesC1();
        atCamX = 0;
        atCamY = 1844;
        atJogX = 165;
        atJogY = 1905;
                
        menuHabilidade = new  MenuHabilidade();
        atributos = new Atributos();
        nivel = 0;
        duranteJogo = false;
        Greenfoot.start();
    }
    
    /**
     * metodo que verifica acoes dentro do objeto jogo durante a aplicacao
     */
    @Override
    public void act(){
        if(pergLeitura!= null && Greenfoot.mouseClicked(pergLeitura)){
            removeObject(pergLeitura);
            menuEscolherSexo();
        }
        
        if(!jogador.emLuta()){
            if(Greenfoot.isKeyDown("f1")){

                if(!emMenu){
                    atCamX = getCameraX();
                    atCamY = getCameraY();
                }
                Telas t = new Telas();
                t.setImage("rivets.jpg");
                addObject(t, 400, 300);
                setCameraLocation(400, 300);
                musicadefundofloresta.stop();
                menuPrincipal();
            }

            if(Greenfoot.isKeyDown("f2")) menuHabilidade();
            if(Greenfoot.isKeyDown("f3")) showAtributos();
            if(Greenfoot.isKeyDown("escape")){
                esconderHabilidade();
                hideAtributos();
            }
        }
    }
    
    /**
     * Atribui as chaves primárias ao mundo, jogador e inimigo no banco
     */
     
    private void atribuirCodigos(){
        
        Conexao con = new Conexao(base, user, pass);
        con.connect();
        if(con.conexao != null){
            con.disconnect();
            GeradorCodigo gerador = new GeradorCodigo(base, user, pass);
            
            this.codigo = gerador.getCodMundo();
            jogador.setCodigo(gerador.getCodJogador());
            oponente.stream().forEach((ad) -> {
                ad.setCodigo(gerador.getCodInimigo());
            });
        }
    }
    
        /**
         * Cria o menu que cadastrará o nome de usuário do jogador
     */
     
    public void menuNome(){
        removeObjects(getObjects(Menus.class));
         menuprincipal = new MenuPrincipal();
         fundoCadastro= new FundoMenuCadastro();
         texto = new TextField("");
         botaoCad = new BotaoCadastrar();
         addObject(fundoCadastro,403,314);
         addObject(texto, 459, 322);
         addObject(botaoCad, 415 ,400);
         volta = new Voltar();
         addObject(volta,120,520);
         volta.setLocation(120,520);
         emMenu = false;
    }
    
    /**
     * @return texto Atributo que contém o nome de usuário do jogador
     */
     
    public String getTexto(){
        return texto.getText();
    }
    
    /**
     *Esconde o menu com a descrição das habilidades do usuário
     */
     
    public void esconderHabilidade(){
        removeObjects(getObjects(MenuHabilidade.class));
        removeObjects(getObjects(MenuHabilidadePassiva.class));
        removeObjects(getObjects(MenuHabilidadeAtaque1.class));
        removeObjects(getObjects(MenuHabilidadeAtaque2.class));
        removeObjects(getObjects(MenuHabilidadeAtaque3.class));
    }
   
        /**
        * Mostra o menu com a descrição das habilidades do usuário
    */
     
    public void menuHabilidade(){
        menuHabilidade = new  MenuHabilidade();
        menuHabilidadePassiva = new MenuHabilidadePassiva();
        menuHabilidadeAtaque1 = new MenuHabilidadeAtaque1();
        menuHabilidadeAtaque2 = new MenuHabilidadeAtaque2();
        menuHabilidadeAtaque3 = new MenuHabilidadeAtaque3();
        addObject( menuHabilidade,600,201);
        addObject( menuHabilidadePassiva,593,91);
        addObject( menuHabilidadeAtaque1,595,161);
        addObject( menuHabilidadeAtaque2,599,232);
        addObject( menuHabilidadeAtaque3,593,301);
    }
    
    /**
     * Define o caminho do menu que descreve as habilidades do personagem
     * @param classe Atributo que representa o diretorio onde estão as habilidades do personagem
     */
    
    public void setClasseHabilidade(String classe){
        classeHabilidade= classe;
    }
    
    /**
     * Salva as informações do jogo no banco de dados
     */
    
    private void salvar(){
        Conexao con = new Conexao(base, user, pass);
        con.connect();
        if(con.conexao != null){
        con.disconnect();
        if(salvo){
            Update update = new Update(base, user, pass);
            
            update.mundo(this.codigo, atCamX, atCamY, atJogX, atJogY, nivel);
            update.jogador(jogador.getCodigo(), Jogador.estamina, Jogador.forca, 
                    Jogador.inteligencia, Jogador.defesa, Jogador.atributosRestantes);
            
            oponente.stream().forEach((ad) -> {
                update.inimigo(ad.getCodigo(), ad.isDead());
            });
            
        }else{
            Insert inserir = new Insert(base, user, pass);
            
            inserir.mundo(this.codigo, atCamX, atCamY, atJogX, atJogY, nivel);
            inserir.jogador(jogador.getCodigo(), this.codigo, jogador.getNome(), jogador.getSexo(), jogador.getClasse(),
                    Jogador.atributosRestantes, Jogador.forca, Jogador.inteligencia, Jogador.estamina, Jogador.defesa);
            
            oponente.stream().forEach((ad) -> {
                inserir.inimigo(ad.getCodigo(), this.codigo, ad.getNome(), ad.isDead());
            });
            
            salvo = true;
        }
    }
    }
    
    /**
     * Recupera o jogo que foi salvo pelo usuário
     */
    
    public void carregar(int cod_mundo){
        
        LeitorBanco lb = new LeitorBanco(base, user, pass, cod_mundo);
        
        this.jogador = lb.getJogador();
        this.oponente = lb.getInimigos();
        
        int[] stt = lb.getMundo();
        
        this.codigo = stt[0];
        this.atCamX = stt[1];
        this.atCamY = stt[2];
        this.atJogX = stt[3];
        this.atJogY = stt[4];
        this.nivel = stt[5];
        
        salvo = true;
        montarCena();
    }
    
    /**
     * Continua o jogo atual
     */

    public void continuar(){
        removeObjects(getObjects(Menus.class));
        removeObjects(getObjects(Telas.class));
        setCameraLocation(atCamX, atCamY);
        musicadefundo.stop();
        if(musica){
            musicadefundofloresta.playLoop();
        }
    }
    
    /**
     * Monta o cenário do jogo quando se inicia um novo jogo
     */
    
    public void montarCenario(){
        jogador.setNome(texto.getText());
        salvo = false;
        atCamX = 0;
        atCamY = 1844;
        atJogX = 165;
        atJogY = 1905;
        advMortos = 0;
        criarColisoesC1();
        criarOponentesC1();
        atribuirCodigos();
        montarCena();
    }
    
    /**
     * Monta o cenário do jogo
     */
    
    public void montarCena(){ // montagem do cenário 1
    
        removeObjects(getObjects(null));
        
        fundoCenario = new Fundo();
                
        addObject(fundoCenario, 1072, 1072);
        setCameraLocation(atCamX, atCamY);
        
        criarObjectNivel();
        verificaOponentesMortos();
        addColisoesC1();
        addOponentesC1();
        addObjectNivel();
        addObject(jogador, atJogX, atJogY);
        showAtributos();
        
        duranteJogo = true;
        
        pause = false;
        musicadefundo.stop();
        if (musica){
            musicadefundofloresta.playLoop();
        }
        
        this.setDiretorioSexo(jogador.getSexo());
        this.setClasseHabilidade(jogador.getClasse());
    }
    
    /**
     * @return pause Atributo que indica se o jogo está pausado ou não
     */
     
    public boolean pause(){
        return pause;
    }
    
    /**
     * Define o idioma do jogo
     * @param idioma Atributo que possui o nome do diretorio do idioma
     */
    
    public void setIdioma(String idi){
        idioma="MENUS/"+idi;
        menuVoltar();
    }
    
    /**
     * Define o diretório em que se encontra o sexo do personagem escolhido pelo usuário
     */
     
    public void setDiretorioSexo(String sexo){
        diretorio = "/"+sexo;
    }
    
    /**
     * Chama o menu contendo a introdução da história do jogo
     */
     
    public void inicioJogo(){
        removeObjects(getObjects(Menus.class));
        pergLeitura = new Telas();
        pergLeitura.setImage("MENUS/pergInicio.png");
        addObject(pergLeitura, 400, 400);
    }
    
    /**
     * Chama o último menu quando o jogador vence todos os adversários e chega ao castelo
     */
     
    public void zerarJogo(){
        pergLeitura = new Telas();
        removeObjects(getObjects(null));
        setBackground("rivets.jpg");
        setCameraLocation(0, 0);
        pergLeitura.setImage("MENUS/pergFim.png");
        Delete del = new Delete(base, user, pass);
        del.deleteGame(this.codigo);
        addObject(pergLeitura, 400, 380);
        Greenfoot.delay(600);
        Greenfoot.setWorld(new Jogo());
    }
    
    /**
     * Coloca o jogador e oponente para lutar
     * @param oponente Recebe como parametro o oponente que lutará com o jogador
     */
     
    public void lutar(Adversario oponente)
    {
        atCamX = getCameraX();
        atCamY = getCameraY();
        
        atJogX = jogador.getGlobalX();
        atJogY = jogador.getGlobalY(); 
        
        removeObjects(getObjects(null));
        
        cinematicLuta();
        montarCenaLuta(oponente);
    }
    
    /**
     * Adiciona o cenário da batalha e coloca o jogador em posição de luta
     */
    
    public void cinematicLuta(){
        jogador.entrarLuta();
        setCameraLocation(0, 0);
        addObject(new CinematicLuta(), 400, 300);
        Greenfoot.delay(100);
    }
    
    /**
     * Monta a cena da luta e adiciona o jogador e o oponente no cenário da batalha
     * @param oponente Recebe por parâmetro o oponente do jogador na batalha
     */
     
    private void montarCenaLuta(Adversario oponente){
        GreenfootImage img = new GreenfootImage(800, 600);
        img.setColor(Color.BLACK);
        img.fill();
        setBackground(img);
        
        removeObjects(getObjects(CinematicLuta.class));
        
        addObject(new Bordas(), 394, 479);
        
        hbP = new Passiva(jogador.dirHabs[0]);
        hb1 = new Habilidade(0, jogador.dirHabs[1]);
        hb2 = new Habilidade(1, jogador.dirHabs[2]);
        hbU = new Habilidade(2, jogador.dirHabs[3]);
        
        addObject(new CenaLuta(), 400, 300);
        addObject(jogador, 250, 400);
        addObject(oponente, 550, 385);
        addObject(jogador.getBarraVida(), 195, 200);
        addObject(oponente.getBarraVida(), 590, 200);
        
        jogador.getBarraVida().addBarra();
        oponente.getBarraVida().addBarra();
        
        addObject(hbP, 335, 520);
        addObject(hb1, 445, 520);
        addObject(hb2, 555, 520);
        addObject(hbU, 661, 520);
        
        AtributosBatalha atB = new AtributosBatalha(Jogador.ataque, Jogador.magia, Jogador.resistencia);
        
        addObject(atB, 152, 534);
        
        jogador.setOponente(oponente);
        setCameraLocation(0, 0);
        
        iniciarLuta();
    }
        
    /**
     * Inicia a luta entre o jogador e o oponente
     */
        
    private void iniciarLuta(){
        musicadefundofloresta.stop();
        musicabatalha.playLoop();
        jogador.iniciarLuta();
        txtBatalha = new Texto("Sua vez de jogar!");
        addObject(txtBatalha, 400, 80);
        addObject(jogador.cdHb2, 555, 520);
        addObject(jogador.cdUlt, 661, 520);
        jogador.vezJogar();
    }
    
    /**
     * Passa a vez de jogar para o adversário
     */
     
    public void vezOponente(Adversario oponente){
        if(oponente.isDead()){
            if(oponente.getTipo() == 3) {
                zerarJogo();
                return;
            }
            ganhaLuta();
        }else{
            int damage = oponente.ataque();
            
            removeObject(txtBatalha);
            txtBatalha = new Texto("Vez do oponente!");
            addObject(txtBatalha, 400, 80);
                 
            Greenfoot.delay(65);
            addObject(new Efeito("SKILLS/atkOponente.png"), jogador.getX(), jogador.getY());
            Greenfoot.delay(40);
            removeObjects(getObjects(Efeito.class));
        
            if(new Random().nextInt(20) == 13){
                jogador.receberDano(0, false);
            }else if(new Random().nextInt(3) == 1) {
                damage = damage*2;
                jogador.receberDano(damage, true);
            }else{
                jogador.receberDano(damage, false);
            }
            
            if(jogador.isDead()) {
                perdeLuta();
                return;
            }
            
            removeObject(txtBatalha);
            txtBatalha = new Texto("Sua vez de jogar!");
            addObject(txtBatalha, 400, 80);
            jogador.vezJogar();
        }
    }
    
    /**
     * Tira o jogador da batalha sem adicioar pontos
     */
     
    public void perdeLuta(){
        musicabatalha.stop();
        musicadefundofloresta.playLoop();
        jogador.sairLuta();
        jogador.curar(99999);
            
        if(salvo){
            removeObjects(getObjects(InterfaceLuta.class)); 
            carregar(this.codigo);
        }else{
            removeObjects(getObjects(InterfaceLuta.class)); 
            atCamX = 0;
            atCamY = 1844;
            atJogX = 165;
            atJogY = 1905;
            montarCena();
        }
    }
    
    /**
     * Atribui pontos ao jogador quando vence uma batalha e coloca ele no cenário normal do jogo
     */
    
    public void ganhaLuta(){
        musicabatalha.stop();
        musicadefundofloresta.playLoop();
        Jogador.atributosRestantes += 5;
        jogador.sairLuta();
        jogador.curar(99999);
        advMortos++;
        
        if(advMortos == 4 ||advMortos == 5 ||advMortos == 9 ||advMortos == 10||
           advMortos == 14||advMortos == 15||advMortos == 19||advMortos == 20){
            nivel++;
            removeObjects(getObjects(ObjNivel.class));
            addObjectNivel();
        }
        
        montarCena();
        salvar();
    }
    
    /**
     * Adiciona e mostra o menu de atributos do usuário
     */
    
    public void showAtributos(){
        addObject(atributos, 400, 300);
        atributos.mostrar();
    }
    
    /**
     * Remove e esconde o menu de atributos do usuário
     */
    
    public void hideAtributos(){
        removeObject(atributos);
        removeObjects(getObjects(BtAdd.class));
    }
    
    /**
     * Adiciona os desenhos nos lugares onde o jogador não poderá atravessar no cenário
     */
    
    private void addColisoesC1(){ // adicionando os blocos de colisão pelos cenário 1
        addObject(colisao.get(0), 1952, 1118);
        addObject(colisao.get(1), 1353, 942);
        addObject(colisao.get(2), 2115, 1160);
        addObject(colisao.get(3), 1807, 1454);
        addObject(colisao.get(4), 1520, 1294);
        addObject(colisao.get(5), 1280, 1134);
        addObject(colisao.get(6), 1405, 413);
        addObject(colisao.get(7), 1980, 620);
        addObject(colisao.get(8), 370, 960);
        addObject(colisao.get(9), 366, 734);
        addObject(colisao.get(10), 30, 740);
        addObject(colisao.get(11), 594, 626);
        addObject(colisao.get(12), 687, 410);
        addObject(colisao.get(13), 1530, 95);
        addObject(colisao.get(14), 380, 32);
        addObject(colisao.get(15), 461, 194);
        addObject(colisao.get(16), 195, 510);
        addObject(colisao.get(17), 1170, 717);
        addObject(colisao.get(18), 616, 1308);
        addObject(colisao.get(19), 395, 1140);
        addObject(colisao.get(20), 172, 1100);
        addObject(colisao.get(21), 863, 1155);
        addObject(colisao.get(22), 744, 1172);
        addObject(colisao.get(23), 1131, 1323);
        addObject(colisao.get(24), 1294, 1513);
        addObject(colisao.get(25), 1630, 1630);
        addObject(colisao.get(26), 2095, 1738);
        addObject(colisao.get(27), 1975, 1887);
        addObject(colisao.get(28), 2132, 2064);
        addObject(colisao.get(29), 1425, 2130);
        addObject(colisao.get(30), 1535, 1955);
        addObject(colisao.get(31), 1285, 2020);
        addObject(colisao.get(32), 995, 1727);
        addObject(colisao.get(33), 604, 1614);
        addObject(colisao.get(34), 834, 1536);
        addObject(colisao.get(35), 872, 1638);
        addObject(colisao.get(36), 155, 1676);
        addObject(colisao.get(37), 545, 2070);
        addObject(colisao.get(38), 511, 1870);
        addObject(colisao.get(39), 65, 2005);
        addObject(colisao.get(40), 810, 1710);
    }
    
    /**
     * Cria desenhos que impedirão o usuário de atravessar determinados lugares do cenário
     */
     
    private void criarColisoesC1(){ // criação dos blocos de colisão para o cenário 1
        colisao = new ArrayList<>();
        colisao.add(new Colisao(116, 438));
        colisao.add(new Colisao(1100, 86));
        colisao.add(new Colisao(58, 534));
        colisao.add(new Colisao(666, 86));
        colisao.add(new Colisao(90, 410));
        colisao.add(new Colisao(506, 92));
        colisao.add(new Colisao(886, 248));
        colisao.add(new Colisao(364, 342));
        colisao.add(new Colisao(730, 124));
        colisao.add(new Colisao(346, 118));
        colisao.add(new Colisao(56, 1476));
        colisao.add(new Colisao(154, 334));
        colisao.add(new Colisao(340, 182));
        colisao.add(new Colisao(1464, 182));
        colisao.add(new Colisao(836, 52));
        colisao.add(new Colisao(285, 368));
        colisao.add(new Colisao(378, 122));
        colisao.add(new Colisao(1045, 154));
        colisao.add(new Colisao(594, 116));
        colisao.add(new Colisao(152, 242));
        colisao.add(new Colisao(322, 156));
        colisao.add(new Colisao(116, 400));
        colisao.add(new Colisao(134, 170));
        colisao.add(new Colisao(478, 85));
        colisao.add(new Colisao(152, 350));
        colisao.add(new Colisao(582, 116));
        colisao.add(new Colisao(90, 484));
        colisao.add(new Colisao(164, 186));
        colisao.add(new Colisao(30, 178));
        colisao.add(new Colisao(1394, 34));
        colisao.add(new Colisao(380, 324));
        colisao.add(new Colisao(138, 194));
        colisao.add(new Colisao(186, 506));
        colisao.add(new Colisao(312, 280));
        colisao.add(new Colisao(212, 124));
        colisao.add(new Colisao(94, 96));
        colisao.add(new Colisao(316, 404));
        colisao.add(new Colisao(450, 168));
        colisao.add(new Colisao(126, 266));
        colisao.add(new Colisao(122, 280));
        colisao.add(new Colisao(166, 86));
    }
    
    /**
     * Adiciona os oponentes em seus devidos lugares no cenário
     */
    
    private void addOponentesC1(){ // adicionando os oponentes em seus lugares no cenário 1
        // monstros
        if(!oponente.get(0).isDead())addObject(oponente.get(0), 145, 1275);//undead
        if(!oponente.get(1).isDead())addObject(oponente.get(1), 385, 1480);//pirate
        if(!oponente.get(2).isDead())addObject(oponente.get(2), 1215, 1795);//aliot
        if(!oponente.get(3).isDead())addObject(oponente.get(3), 980, 2035);//belzebull
        if(!oponente.get(4).isDead())addObject(oponente.get(4), 1815, 1845);
        if(!oponente.get(5).isDead())addObject(oponente.get(5), 2005, 2035);
        if(!oponente.get(6).isDead())addObject(oponente.get(6), 1845, 1525);
        if(!oponente.get(7).isDead())addObject(oponente.get(7), 985, 1115);
        if(!oponente.get(8).isDead())addObject(oponente.get(8), 1825, 845);
        if(!oponente.get(9).isDead())addObject(oponente.get(9), 1265, 826);
        if(!oponente.get(10).isDead())addObject(oponente.get(10), 585, 1125);
        if(!oponente.get(11).isDead())addObject(oponente.get(11), 135, 795);
        if(!oponente.get(12).isDead())addObject(oponente.get(12), 1525, 557);
        if(!oponente.get(13).isDead())addObject(oponente.get(13), 765, 552);
        if(!oponente.get(14).isDead())addObject(oponente.get(14), 895, 255);
        if(!oponente.get(15).isDead())addObject(oponente.get(15), 715, 205);
        
        // sub bosses
        if(!oponente.get(16).isDead())addObject(oponente.get(16), 700, 1870);
        if(!oponente.get(17).isDead())addObject(oponente.get(17), 1737, 1180);
        if(!oponente.get(18).isDead())addObject(oponente.get(18), 195, 245);
        if(!oponente.get(19).isDead())addObject(oponente.get(19), 1725, 205);
        
        // boss final
        if(!oponente.get(20).isDead())addObject(oponente.get(20), 2005, 295);
    }
    
    /**
     * Cria os oponentes que existirão no cenário
     */
    
    private void criarOponentesC1(){ // criação dos oponentes que serão encontrados no cenário 1
        oponente = new ArrayList<>();
        
        oponente.add(new Monstro("01 Undead", 2500, 90, 12));
        oponente.add(new Monstro("02 Pirate", 2000, 70, 10));
        oponente.add(new Monstro("03 Aliot", 3200, 120, 5));
        oponente.add(new Monstro("04 Belzebull", 3800, 170, 35));
        
        oponente.add(new Monstro("05 Turtle", 4500, 150, 60));
        oponente.add(new Monstro("06 Invoker", 4200, 280, 2));
        oponente.add(new Monstro("07 Incubus", 4700, 225, 10));
        oponente.add(new Monstro("08 Angel", 5250, 300, 15));
        
        oponente.add(new Monstro("09 Yun", 6200, 380, 20));
        oponente.add(new Monstro("10 Zenfi", 7500, 420, 1));
        oponente.add(new Monstro("11 Loki", 8500, 480, 25));
        oponente.add(new Monstro("12 Baphometh", 9250, 540, 2));
        
        oponente.add(new Monstro("13 Blood", 10500, 585, 20));
        oponente.add(new Monstro("14 Wolf", 11000, 650, 10));
        oponente.add(new Monstro("15 Shinobi", 12250, 725, 25));
        oponente.add(new Monstro("16 Bijou", 15000, 750, 40));
        
        oponente.add(new Sub_Boss("SB01 Dracula", 4500, 250, 5));
        oponente.add(new Sub_Boss("SB02 Giant", 6500, 325, 5));
        oponente.add(new Sub_Boss("SB03 Samurai", 12000, 580, 25));
        oponente.add(new Sub_Boss("SB04 Valkyr", 18500, 780, 15));
        
        oponente.add(new BossFinal("Dark Lord", 25000, 650, 5));
    }
    
    private void menuPrincipal(){
        menuprincipal = new MenuPrincipal();
        novojogo = new NovoJogo();
        sair = new Sair();
        opcoes = new Opcoes();
        continuar = new Continuar();
        ajuda = new Ajuda();
        volta = new Voltar();
        addObject(menuprincipal,412,270);
        addObject(novojogo,415,269);
        addObject(continuar,415,339);
        addObject(opcoes,415,409);
        addObject(ajuda,415,479);
        addObject(sair,415,549);
        
        emMenu = true;
         
        if(duranteJogo){
            addObject(volta, 120, 520);
        }
        
        if (musica){
            musicadefundo.playLoop();
        }
    }
    
    public void menuContinuar(){
        removeObjects(getObjects(Menus.class));
        
        ArrayList<BTContinuar> bts = new ArrayList<>();
        
        Conexao con = new Conexao(base, user, pass);
        
        con.connect();
        if(con.conexao != null){
            ResultSet rs;
            PreparedStatement stm;
            String selectJogador = "select FK_mundo, nome, FK_classe from jogador order by FK_mundo desc limit 5;";
            String selectClasse = "select nome from classe where PK_classe =?;";
            ResultSet rsClasse;
            String classeJog = null;
            PreparedStatement stmClasse;
            
            try {
                stm = con.conexao.prepareStatement(selectJogador);
                rs = stm.executeQuery();
                
                if(rs != null){
                    if(rs.first()){
                        do{
                            stmClasse = con.conexao.prepareStatement(selectClasse);
                            stmClasse.setInt(1, rs.getInt("FK_classe"));
                            rsClasse = stmClasse.executeQuery();
                            
                            if(rsClasse != null){
                                if(rsClasse.first()){
                                    classeJog = rsClasse.getString("nome");
                                }
                            }
                            
                            BTContinuar bt = new BTContinuar(classeJog, rs.getString("nome"), rs.getInt("FK_mundo"));
                            bts.add(bt);
                        }while(rs.next());
                    }
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
            }
            con.disconnect();
        }
        
        int x = 410;
        int y = 200;
        if(!bts.isEmpty()){
            for(BTContinuar bt: bts){
                addObject(bt, x, y);
                y+= 70;
            }
        }
        
        emMenu = false;
        volta = new Voltar();
        addObject(volta,120,520);
    }
    
    public void menuEscolherSexo()
    {
        removeObjects(getObjects(Menus.class));
        sexo = new Sexo();
        homem = new Homem();
        mulher = new Mulher();
        volta = new Voltar();
        addObject(volta,120,520);
        volta.setLocation(120,520);
        addObject(sexo, 412, 277);
        addObject(mulher, 415, 376);
        addObject(homem, 415, 269);
        mulher.setLocation(415, 376);
        homem.setLocation(415, 269);
    }
    
    public void menuEscolherClasse()
    {
        removeObjects(getObjects(Menus.class));
        
        classe = new Classe();
        cavaleiro = new Cavaleiro();
        mago = new Mago();
        sacerdote = new Sacerdote();
        templario = new Templario();
        
        addObject(classe, 405, 295);
        addObject(cavaleiro, 415, 245);
        addObject(mago, 415, 326);
        addObject(sacerdote, 415, 407);
        addObject(templario, 415, 488);
    }
    
    public void menuAjuda()
    {
        removeObjects(getObjects(Menus.class));
        pergaminho = new Pergaminho();
        volta = new Voltar();
        pergaminho.setLocation(395,370);
        addObject(pergaminho,395,370);
        volta.setLocation(120,520);
        addObject(volta,120,540);
        ajudamenu = new AjudaMenu();
        addObject(ajudamenu,412,270);
         emMenu = false;
    }
    
    public void menuOpcao(){
        removeObjects(getObjects(Menus.class));
        opcaoAudio = new opcoesAudio();
        volta = new Voltar();
        mudo = new Mudo();
        pt=new Ptbr();
        eng=new Eng();
        somAtivado = new Somativado();
        addObject(volta,120,550);
        addObject(opcaoAudio,415,250);
        addObject(somAtivado,550,385);
        addObject(mudo,230,385);
        addObject(pt,230,485);
        addObject(eng,550,485);
        emMenu = false;
    }
    
    public void menuVoltar(){
        removeObjects(getObjects(Menus.class));
        if(emMenu){
            continuar();
        }else{
            menuPrincipal();
        }
    }
    
    public void mudo(){
        musicadefundo.stop();
        musica=false;
    }
    
    public void play(){
        musicadefundo.playLoop();
        musica=true;
    }
    
    
    public void criarObjectNivel() {
        listObj = new ArrayList<ObjNivel>();
        listObj.add(new ObjNivel());
        listObj.add(new ObjNivel());
        listObj.add(new ObjNivel());
        listObj.add(new ObjNivel());
        listObj.add(new ObjNivel());
        listObj.add(new ObjNivel());
        listObj.add(new ObjNivel());
        listObj.add(new ObjNivel());
        listObj.add(new ObjNivel());
    }
    
    public void addObjectNivel(){
        if(nivel < 8){
            addObject(listObj.get(8),1838,238);
        }else{return;}
            
        if(nivel < 7){
            addObject(listObj.get(7),1607,234);
        }else{return;}
        
        if(nivel < 6){
            addObject(listObj.get(6),1743,720);
        }else{return;}
        
        if(nivel < 5){
            addObject(listObj.get(5),446,509);
        }else{return;}
        
        if(nivel < 4){
            addObject(listObj.get(4),1952,1371);
        }else{return;}
        
        if(nivel < 3){
            addObject(listObj.get(3),1542,1039);
        }else{return;}
        
        if(nivel < 2){
            addObject(listObj.get(2),1397,1742);
        }else{return;}
        
        if(nivel < 1){
            addObject(listObj.get(0),807,1986);
            addObject(listObj.get(1),869,1965);
        }else{return;}
    }
    
    private void verificaOponentesMortos(){
        advMortos = 0;
        for(Adversario ad: oponente){
            if(ad.isDead()) advMortos++;
        }
    }    
}
