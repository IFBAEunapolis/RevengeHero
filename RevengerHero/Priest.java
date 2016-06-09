import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe que representa o personagem Priest
 * @author i9 Games
 * @version 1.0.0
 */
 
public class Priest extends Jogador
{
    public Priest(){
        super(900);
        this.setClasse("Priest");
        dirHabs[0] = "HABILIDADES/p1.png";
        dirHabs[1] = "HABILIDADES/p2.png";
        dirHabs[2] = "HABILIDADES/p3.png";
        dirHabs[3] = "HABILIDADES/p4.png";
    }
    
	/**
	 * O jogador tem a chance de aumentar o efeito da habilidade usada
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
        
        int damage = magia;
        
        if(passiva())damage = (damage*9)/5;
        
        if(random.nextInt(20) == 13){
            oponente.receberDano(0, false);
        }else if(random.nextInt(5) == 2) {
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
        int cura = this.magia*2;
        int vidaPerdida = vidaMax - this.vida;
        
        cura += vidaPerdida/5;
        if(passiva())cura = (cura*9)/5;
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
        
        int damage = (magia*7)/4;
        
        damage += oponente.getVida()/5;
        
        if(passiva())damage = (damage*9)/5;
        
        if(random.nextInt(20) == 13){
            oponente.receberDano(0, false);
        }else if(random.nextInt(3) == 1) {
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
        getWorld().addObject(new Efeito("SKILLS/priest01.png"), x, y);
        Greenfoot.delay(40);
        getWorld().removeObjects(getWorld().getObjects(Efeito.class));
    }
    
	/**
	 * Mostra o efeito do ultimate quando o jogador ataca
	 */
    
    public void acionarEfeito2(int x, int y){
        Greenfoot.delay(5);
        getWorld().addObject(new Efeito("SKILLS/priest02.png"), x, y);
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
        
        if(this.inteligencia%3 == 0) this.magia +=5;
    }
    
	/**
	 * Adiciona a estamina do personagem
	 */
	 
    @Override
    public void addEstamina(){
        this.estamina++;
        this.vidaMax += 280;
        if(this.estamina%3 == 0) this.vidaMax += 160;
        this.barraVida = new BarraVida(this.vidaMax);
    }
    
	/**
	 * Adiciona a defesa do personagem
	 */
	 
    @Override
    public void addDefesa(){
        this.defesa++;
        this.resistencia += 5;
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
