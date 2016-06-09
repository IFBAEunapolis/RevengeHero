import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que realiza a leitura no banco de dados
 * @author i9 Games
 * @version  1.0.0
 */

public class LeitorBanco {
    
    Conexao con;
    int cod_mundo;
    
	/**
	 * Constructor
	 * @param banco Atributo que contém o caminho do banco de dados
	 * @param usuario Atributo que contém o nome de usuário do banco de  dados
	 * @param senha Atributo que contém a senha do banco de dados
	 * @param cod_mundo Atributo que contém o código do mundo
	 */
    
    public LeitorBanco(String banco, String usuario, String senha, int cod_mundo){
        con = new Conexao(banco, usuario, senha);
        this.cod_mundo = cod_mundo;
    }
    
	/**
	 * @return jogador Atributo que contém o jogador
	 */
	 
    public Jogador getJogador(){
        Jogador jogador = new Knight();
        
        ResultSet rs;
        PreparedStatement stm;
        String select = "select * from jogador where FK_mundo = ?;";
                
        
        con.connect();
        
        try {
            stm = con.conexao.prepareStatement(select);
            stm.setInt(1, cod_mundo);
            
            rs = stm.executeQuery();
            
            if(rs != null){
                if(rs.first()){
                                        
                    switch(rs.getInt("FK_sexo")){
                        case 1:
                            jogador.setSexo("MALE");
                            break;
                        case 2:
                            jogador.setSexo("FEMALE");
                            break;
                    }
                    
                    switch (rs.getInt("FK_classe")) {
                        case 1:
                            jogador = new Knight();
                            break;
                        case 2:
                            jogador = new Crusader();
                            break;
                        case 3:
                            jogador = new Wizard();
                            break;
                        case 4:
                            jogador = new Priest();
                            break;
                        default:
                            break;
                    }
                    
                    jogador.setNome(rs.getString("nome"));
                    jogador.setCodigo(rs.getInt("PK_jogador"));
                    Jogador.atributosRestantes = rs.getInt("pontos_restantes");
                    
                    int estamina = rs.getInt("estamina");
                    int forca = rs.getInt("forca");
                    int inteligencia = rs.getInt("inteligencia");
                    int defesa = rs.getInt("defesa");
                    
                    for(int i= 0; i< estamina; i++){
                        jogador.addEstamina();
                    }
                    
                    for(int i= 0; i< forca; i++){
                        jogador.addForca();
                    }
                    
                    for(int i= 0; i< inteligencia; i++){
                        jogador.addInteligencia();
                    }
                    
                    for(int i= 0; i< defesa; i++){
                        jogador.addDefesa();
                    }
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LeitorBanco.class.getName()).log(Level.SEVERE, null, ex);
            con.disconnect();
        }
        
        con.disconnect();
        
        return jogador;
    }
    
	/**
	 * @return inimigos Atributo do tipo ArrayList<Adversario> que contém os adversários restantes no mundo
	 */
    
    public ArrayList<Adversario> getInimigos(){
        ArrayList<Adversario> inimigos = new ArrayList<>();
        
        ResultSet rs;
        PreparedStatement stm;
        String select = "select * from registro_inimigo where FK_mundo = ?;";
        
        ResultSet rsInimigo;
        PreparedStatement stmInimigo;
        String selectInimigo = "select * from inimigo where PK_inimigo = ?;";
        
        con.connect();
        
        try {
            stm = con.conexao.prepareStatement(select);
            stm.setInt(1, cod_mundo);
            
            rs = stm.executeQuery();
            
            if(rs.first()){
                do{
                    stmInimigo = con.conexao.prepareStatement(selectInimigo);
                    stmInimigo.setInt(1, rs.getInt("FK_inimigo"));
                    rsInimigo = stmInimigo.executeQuery();
                    
                    if(rsInimigo.first()){
                        
                        Adversario ad = null;
                                                
                        switch(rsInimigo.getInt("FK_tipo_inimigo")){
                            case 1:
                                ad = new Monstro(rsInimigo.getString("nome"), rsInimigo.getInt("vida"), rsInimigo.getInt("forca"), rsInimigo.getInt("defesa"));
                                ad.setMorto(rs.getBoolean("status_morte"));
                                ad.setCodigo(rs.getInt("PK_registro_inimigo"));
                                break;
                            case 2:
                                ad = new Sub_Boss(rsInimigo.getString("nome"), rsInimigo.getInt("vida"), rsInimigo.getInt("forca"), rsInimigo.getInt("defesa"));
                                ad.setMorto(rs.getBoolean("status_morte"));
                                ad.setCodigo(rs.getInt("PK_registro_inimigo"));
                                break;
                            case 3:
                                ad = new BossFinal(rsInimigo.getString("nome"), rsInimigo.getInt("vida"), rsInimigo.getInt("forca"), rsInimigo.getInt("defesa"));
                                ad.setMorto(rs.getBoolean("status_morte"));
                                ad.setCodigo(rs.getInt("PK_registro_inimigo"));
                                break;
                        }
                        
                        inimigos.add(ad);
                        rsInimigo.next();
                    }
                }while(rs.next());
            }
            
        }catch(SQLException ex){
            Logger.getLogger(LeitorBanco.class.getName()).log(Level.SEVERE, null, ex);
            con.disconnect();
        }
        
        con.disconnect();
        return inimigos;
    }
    
	/**
	 * @return stt Atributo que contém as chaves primárias do mundo
	 */
	 
    public int[] getMundo(){
        int[] stt = new int[6];
        
        ResultSet rs;
        PreparedStatement stm;
        String select = "select * from mundo where PK_mundo = ?;";
        
        con.connect();
        
        try {
            stm = con.conexao.prepareStatement(select);
            stm.setInt(1, cod_mundo);
            
            rs = stm.executeQuery();
            
            if(rs.first()){
                stt[0] = rs.getInt("PK_mundo");
                stt[1] = rs.getInt("camX");
                stt[2] = rs.getInt("camY");
                stt[3] = rs.getInt("jogadorX");
                stt[4] = rs.getInt("jogadorY");
                stt[5] = rs.getInt("nivel");
            }
            
        }catch(SQLException ex){
            Logger.getLogger(LeitorBanco.class.getName()).log(Level.SEVERE, null, ex);
            con.disconnect();
        }
        
        con.disconnect();
        return stt;
    }
}
