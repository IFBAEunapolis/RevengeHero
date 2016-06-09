import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe que representa o personagem Wizard
 * @author i9 Games
 * @version 1.0.0
 */
 
public class Wizard extends Jogador
{
    public Wizard(){
        super(800);
        this.setClasse("Wizard");
        dirHabs[0] = "HABILIDADES/w1.png";
        dirHabs[1] = "HABILIDADES/w2.png";
        dirHabs[2] = "HABILIDADES/w3.png";
        dirHabs[3] = "HABILIDADES/w4.png";
    }   
    
    
    /**
     * Toda vez que atacado tem a chance de se curar
     * @return true caso a passiva seja ativada
     */
    @Override
    public boolean passiva(){
        return random.nextInt(3) == 1;
    }
    
    /**
     * Calcula e causa o dano no oponente
     */
    @Override
    public void habilidade1(){
        int xEfeito = oponente.getX();
        int yEfeito = oponente.getY()-20;
        acionarEfeito1(xEfeito, yEfeito);
        
        boolean passiva = passiva();
        int damage = (magia*4)/3;
        
        if(random.nextInt(20) == 13){
            oponente.receberDano(0, false);
        }else if(random.nextInt(3) == 1) {
            damage = damage*2;
            oponente.receberDano(damage, true);
            if(passiva)curar(damage/3);
        }else{
            oponente.receberDano(damage, false);
            if(passiva)curar(damage/3);
        }
    }
    
    /**
     * Cura a vida do jogador
     */
    @Override
    public void habilidade2(){
        int cura = (this.magia*9)/5;
        
        curar(cura);
        cdHabilidade2 = 3;
    }
    
    /**
     * Calcula e causa dano no jogador
     */
    @Override
    public void ultimate(){
        int xEfeito = oponente.getX();
        int yEfeito = oponente.getY();
        acionarEfeito2(xEfeito, yEfeito);
        
        int damage = (this.magia*9)/5;
        
        if(random.nextInt(20) == 13){
            oponente.receberDano(0, false);
        }else if(random.nextInt(2) == 0) {
            damage = damage*2;
            oponente.receberDano(damage, true);
        }else{  
            oponente.receberDano(damage, false);
        }
        
        curar(damage/2);
        cdUltimate = 4;
    }
    
        /**
	 * Mostra o efeito da habilidade1 quando o jogador ataca
	 */
	 
    public void acionarEfeito1(int x, int y){
        Greenfoot.delay(5);
        getWorld().addObject(new Efeito("SKILLS/wizard01.png"), x, y);
        Greenfoot.delay(40);
        getWorld().removeObjects(getWorld().getObjects(Efeito.class));
    }
    
	/**
	 * Mostra o efeito do ultimate quando o jogador ataca
	 */
	 
    public void acionarEfeito2(int x, int y){
        Greenfoot.delay(5);
        getWorld().addObject(new Efeito("SKILLS/wizard02.png"), x, y);
        Greenfoot.delay(40);
        getWorld().removeObjects(getWorld().getObjects(Efeito.class));
    }
        
        /**
	 * Adiciona a força do personagem
	 */	   
    @Override
    public void addForca(){
        this.forca++;
        this.ataque++;
    }
    
	/**
	 * Adiciona a inteligencia do personagem
	 */
	 
    @Override
    public void addInteligencia(){
        this.inteligencia++;
        this.magia += 5;
        
        if(this.inteligencia %3 == 0) this.magia += 5;
    }
    
	/**
	 * Adiciona a estamina do personagem
	 */
	 
    @Override
    public void addEstamina(){
        this.estamina++;
        this.vidaMax += 225;
        if(this.estamina %3 == 0) this.magia += 125;
        this.barraVida = new BarraVida(this.vidaMax);
    }
    
	/**
	 * Adiciona a defesa do personagem
	 */
	 
    @Override
    public void addDefesa(){
        this.defesa++;
        this.resistencia+= 7;
    }
    
	/**
	 * Causa dano no jogador quando atacado
	 * @param dano Atributo que informa o dano recebido
	 * @param critical Atributo que informa se o dano é crítico ou não
	 */
    @Override
    public void receberDano(int dano, boolean critical){
        dano -=  this.resistencia;
        
        efeitoDano(dano, critical);
        recebeDano(dano);
    }
}
