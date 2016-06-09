



import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que representa os updates no banco de dados
 * @author i9 Games
 * @version 1.0.0
 */

public class Update {

    Conexao con;

	/**
	 * Constructor
	 * @param banco Atributo que contém o caminho do banco de dados
	 * @param usuario Atributo que contém o nome de usuário do banco de dados
	 * @param senha Atributo que contém a senha do banco de dados
	 */
	 
    public Update(String banco, String usuario, String senha){
        con = new Conexao(banco, usuario, senha);
    }

	/**
	 * Realiza update no mundo detro do banco de dados
	 * @param codigo Atributo que contém o código do mundo
	 * @param camX Atributo que contém a posição da camera na horizontal
	 * @param camY Atributo que contém a posição da camera na vertical
	 * @param jogX Atributo que contém a posição do jogador na horizontal
	 * @param jogY Atributo que contém a posição do jogador na vertical
	 * @param nivel Atributo que contém o nível do personagem
	 */
    public void mundo(int codigo, int camX, int camY, int jogX, int jogY, int nivel){

        PreparedStatement stm;
        StringBuilder update = new StringBuilder("UPDATE mundo SET ");
        update.append("camX=?, camY=?, jogadorX=?, jogadorY=?, nivel=? ");
        update.append("where PK_mundo = ?;");

        con.connect();

        try {
            stm = con.conexao.prepareStatement(update.toString());

            stm.setInt(1, camX);
            stm.setInt(2, camY);
            stm.setInt(3, jogX);
            stm.setInt(4, jogY);
            stm.setInt(5, nivel);
            stm.setInt(6, codigo);

            stm.execute();
        } catch (SQLException e) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        }

        con.disconnect();
    }

	/**
	 * Realiza o update no jogador dentro do banco de dados
	 * @param codigo Atributo que contém o código do jogador
	 * @param estamina Atributo que contém a estamina do jogador
	 * @param forca Atributo que contém a forca do jogador
	 * @param inteligencia Atributo que contém a inteligencia do jogador
	 * @param defesa Atributo que contém a defesa do jogador
	 *
	 */
    public void jogador(int codigo, int estamina, int forca, int inteligencia, int defesa, int pontos){

        PreparedStatement stm;
        StringBuilder update = new StringBuilder("UPDATE jogador SET ");
        update.append("estamina=?, forca=?, inteligencia=?, defesa=?, pontos_restantes= ?");
        update.append(" where PK_jogador = ?;");

        con.connect();

        try {
            stm = con.conexao.prepareStatement(update.toString());

            stm.setInt(1, estamina);
            stm.setInt(2, forca);
            stm.setInt(3, inteligencia);
            stm.setInt(4, defesa);
            stm.setInt(5, pontos);
            stm.setInt(6, codigo);

            stm.execute();
        } catch (SQLException e) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        }

        con.disconnect();
    }

	/**
	 * Realiza o update no adversario dentro do banco de dados
	 * @param codigo Atributo que contém o código do jogador
	 * @param morto Atributo que contém se o jogador está vivo ou não
	 */
    public void inimigo(int codigo, boolean morto){

        PreparedStatement stm;
        StringBuilder update = new StringBuilder("UPDATE registro_inimigo SET ");
        update.append("status_morte=? where PK_registro_inimigo = ?;");

        con.connect();

        try {
            stm = con.conexao.prepareStatement(update.toString());

            stm.setBoolean(1, morto);
            stm.setInt(2, codigo);

            stm.execute();
        } catch (SQLException e) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        }

        con.disconnect();
    }
}
