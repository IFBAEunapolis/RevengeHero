import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que representa as inserções no banco de dados
 * @author i9 Games
 * @version 1.0.0
 */
 
public class Insert{
	
    Conexao con;

	/**
	 * Constructor
	 * @param banco Atributo que contém o caminho do banco de dados
	 * @param usuario Atributo que contém o nome de usuário do banco de dados
	 * @param senha Atributo que contém a senha do banco de dados
	 */
	 
    public Insert(String banco, String usuario, String senha){
        con = new Conexao(banco, usuario, senha);
    }

	/**
	 * Realiza insert no mundo detro do banco de dados
	 * @param codigo Atributo que contém o código do mundo
	 * @param camX Atributo que contém a posição da camera na horizontal
	 * @param camY Atributo que contém a posição da camera na vertical
	 * @param jogX Atributo que contém a posição do jogador na horizontal
	 * @param jogY Atributo que contém a posição do jogador na vertical
	 * @param nivel Atributo que contém o nível do personagem
	 */
	 
    public void mundo(int codigo, int camX, int camY, int jogX, int jogY, int nivel){

        PreparedStatement stm;
        String insert = "insert into mundo values(?, ?, ?, ?, ?, ?);";

        con.connect();

        try {
            stm = con.conexao.prepareStatement(insert);

            stm.setInt(1, codigo);
            stm.setInt(2, camX);
            stm.setInt(3, camY);
            stm.setInt(4, jogX);
            stm.setInt(5, jogY);
            stm.setInt(6, nivel);

            stm.execute();
        } catch (SQLException e) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        }

        con.disconnect();
    }

	/**
	 * Realiza o insert no jogador dentro do banco de dados
	 * @param codigo Atributo que contém o código do jogador
	 * @param mundo Atributo que contém o mundo do jogador
	 * @param nome Atributo que o nome do jogador
	 * @param sexo Atributo que contém o sexo do jogador
	 * @param classe Atributo que contém o diretório do jogador
	 * @param pontos Atributo que contém os pontos do jogador
	 * @param forca Atributo que contém a forca do jogador
	 * @param inteligencia Atributo que contém a inteligencia do jogador
	 * @param estamina Atributo que contém a estamina do jogador
	 * @param defesa Atributo que contém a defesa do jogador
	 */
	 
    public void jogador(int codigo, int mundo, String nome, String sexo, String classe, int pontos,
                int forca, int inteligencia, int estamina, int defesa){

        PreparedStatement stm;
        String insert = "insert into jogador values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        String selectSexo = "select PK_sexo from sexo where nome= ?;";
        String selectClasse = "select PK_classe from classe where nome= ?;";
        ResultSet rs;
        con.connect();

        int pkSexo = 0;
        int pkClasse = 0;
        
        try {
            stm = con.conexao.prepareStatement(selectSexo);
            stm.setString(1, sexo);
            rs = stm.executeQuery();
            
            if(rs != null){
                if(rs.first()){
                    pkSexo = rs.getInt("PK_sexo");
                }
                rs.close();
            }
            stm.close();
            
            
            stm = con.conexao.prepareStatement(selectClasse);
            stm.setString(1, classe);
            rs = stm.executeQuery();
            
            if(rs != null){
                if(rs.first()){
                    pkClasse = rs.getInt("PK_classe");
                }
                rs.close();
            }
            stm.close();
            
            
            stm = con.conexao.prepareStatement(insert);

            stm.setInt(1, codigo);
            stm.setInt(2, pkClasse);
            stm.setInt(3, mundo);
            stm.setInt(4, pkSexo);
            stm.setString(5, nome);
            stm.setInt(6, pontos);
            stm.setInt(7, estamina);
            stm.setInt(8, forca);
            stm.setInt(9, inteligencia);
            stm.setInt(10, defesa);

            stm.execute();
            
            stm.close();
        } catch (SQLException e) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        }

        con.disconnect();
    }

	/**
	 * Realiza as inserções do adversário no banco de dados
	 * @param codigo Atributo que contém o código do adversário
	 * @param mundo Atributo que contém o mundo do adversário
	 * @param tipo Atributo que contém a categoria do adversário
	 * @param nome Atributo que contém o nome do adversário
	 * @param vida Atributo que contém a vida do inimigo
	 * @param ataque Atributo que contém o ataque do adversário
	 * @param defesa Atributo que contém a defesa do adversário
	 * @param morto Atributo que contém se o adversário está vivo ou não
	 */
	 
    public void inimigo(int codigo, int mundo, String nome, boolean morto){

        PreparedStatement stm;
        String insert = "insert into registro_inimigo values(?, ?, ?, ?);";
        String selectInimigo = "select * from inimigo where nome= ?;";
        ResultSet rs;
        
        int pkInimigo = 0;

        con.connect();

        try {
            
            stm = con.conexao.prepareStatement(selectInimigo);
            stm.setString(1, nome);
            
            rs = stm.executeQuery();
            
            if(rs != null){
                if(rs.first()){
                    pkInimigo = rs.getInt("PK_inimigo");
                }
                rs.close();
            }
            stm.close();
            
            
            stm = con.conexao.prepareStatement(insert);

            stm.setInt(1, codigo);
            stm.setInt(2, mundo);
            stm.setInt(3, pkInimigo);
            stm.setBoolean(4, morto);

            stm.execute();
        } catch (SQLException e) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        }
        con.disconnect();
    }
}