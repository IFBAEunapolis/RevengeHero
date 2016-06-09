import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe que representa o personagem Crusader
 * @author i9 Games
 * @version 1.0.0
 */
 
public class Crusader extends Jogador
{
    public Crusader(){
        super(1000);
        this.setClasse("Crusader");
        dirHabs[0] = "HABILIDADES/c1.png";
        dirHabs[1] = "HABILIDADES/c2.png";
        dirHabs[2] = "HABILIDADES/c3.png";
        dirHabs[3] = "HABILIDADES/c4.png";
    }
    
	/**
	 * Toda vez que é atacado tem a chance de reduzir o dano recebido
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
        int yEfeito = oponente.getY();
        acionarEfeito1(xEfeito, yEfeito);
        
        int damage = (this.ataque*7)/5;
        
        if(random.nextInt(20) == 13){
            oponente.receberDano(0, false);
        }else if(random.nextInt(3) == 1) {
            damage = damage*2;
            oponente.receberDano(damage, true);
        }else{
            oponente.receberDano(damage, false);
        }
    }
    
	/**
	 * Calcula e aumenta a vida do jogador
	 */
	 
    @Override
    public void habilidade2(){
        int cura = this.resistencia*10;
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
        
        int damage = (this.ataque*7)/5;
        
        damage += (this.resistencia*7)/3;
        
        if(random.nextInt(20) == 13){
            oponente.receberDano(0, false);
        }else if(random.nextInt(2) == 0) {
            damage = damage*2;
            oponente.receberDano(damage, true);
        }else{
            oponente.receberDano(damage, false);
        }
        cdUltimate = 4;
    }
    
	/**
	 * Mostra o efeito da habilidade1 quando o jogador ataca
	 */
	
    public void acionarEfeito1(int x, int y){
        Greenfoot.delay(5);
        getWorld().addObject(new Efeito("SKILLS/crusader01.png"), x, y);
        Greenfoot.delay(40);
        getWorld().removeObjects(getWorld().getObjects(Efeito.class));
    }
    
	/**
	 * Mostra o efeito do ultimate quando o jogador ataca
	 */
    
    public void acionarEfeito2(int x, int y){
        Greenfoot.delay(5);
        getWorld().addObject(new Efeito("SKILLS/crusader02.png"), x, y);
        Greenfoot.delay(40);
        getWorld().removeObjects(getWorld().getObjects(Efeito.class));
    }
     
	/**
	 * Adiciona a força do personagem
	 */
	 
    @Override
    public void addForca(){
        this.forca++;
        this.ataque += 4;
        
        if(this.forca%3 == 0){
            this.ataque += 5;
        }
        this.barraVida = new BarraVida(this.vidaMax);
    }
    
	/**
	 * Adiciona a inteligencia do personagem
	 */
	 
    @Override
    public void addInteligencia(){
        this.inteligencia++;
        this.magia ++;
    }
    
	/**
	 * Adiciona a estamina do personagem
	 */
	 
    @Override
    public void addEstamina(){
        this.estamina++;
        this.vidaMax += 320;
        if(this.estamina%3 == 0) this.vidaMax += 240;
        this.barraVida = new BarraVida(this.vidaMax);
    }
    
	/**
	 * Adiciona a defesa do personagem
	 */
	 
    @Override
    public void addDefesa(){
        this.defesa++;
        this.resistencia += 5;
        if(this.defesa%3 == 0){
            this.resistencia += 5;
        }
    }
    
	/**
	 * Causa dano no jogador quando atacado
	 * @param dano Atributo que informa o dano recebido
	 * @param critical Atributo que informa se o dano é crítico ou não
	 */
	 
    @Override
    public void receberDano(int dano, boolean critical){
        dano -= this.resistencia;
        
        if(passiva()) dano = (dano*3)/5;
        
        efeitoDano(dano, critical);
        recebeDano(dano);
    }
}
