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
 
public class GeradorCodigo {

    Conexao con;

    private static int cod_mundo;
    private static int cod_jogador;
    private static int cod_inimigo;


	/**
	 * Constructor
	 * @param banco Atributo que contém o caminho do banco de dados
	 * @param usuario Atributo que contém o nome de usuário do banco de dados
	 * @param senha Atributo que contém a senha do banco de dados
	 */
	 
    public GeradorCodigo(String banco, String usuario, String senha){
        con = new Conexao(banco, usuario, senha);
        carregarCodigos();
    }

        /**
         * metodo que carrega a maior chave primaria do banco de dados, para que as proximas
         * chaves geradas a partir dela sejam maiores, evitando 
         */
    private void carregarCodigos(){

        PreparedStatement stmMundo;
        PreparedStatement stmJogador;
        PreparedStatement stmInimigo;

        ResultSet rsMundo;
        ResultSet rsJogador;
        ResultSet rsInimigo;

        String selectMundo = "select PK_mundo from mundo order by PK_mundo desc limit 1;";
        String selectJogador = "select PK_jogador from jogador order by PK_jogador desc limit 1;";
        String selectInimigo = "select PK_registro_inimigo from registro_inimigo order by PK_registro_inimigo desc limit 1;";

        con.connect();

        try {
            stmMundo = con.conexao.prepareStatement(selectMundo);
            stmJogador = con.conexao.prepareStatement(selectJogador);
            stmInimigo = con.conexao.prepareStatement(selectInimigo);

            rsMundo = stmMundo.executeQuery();
            rsJogador = stmJogador.executeQuery();
            rsInimigo = stmInimigo.executeQuery();

            if(rsMundo.first()){
                cod_mundo = rsMundo.getInt("PK_mundo");
            }else{
                cod_mundo = 0;
            }

            if(rsJogador.first()){
                cod_jogador = rsJogador.getInt("PK_jogador");
            }else{
                cod_jogador = 0;
            }

            if(rsInimigo.first()){
                cod_inimigo = rsInimigo.getInt("PK_registro_inimigo");
            }else{
                cod_inimigo = 0;
            }
        } catch (SQLException e) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        }

        con.disconnect();
    }
    
    /**
     * 
     * @return gera a chave primaria para o mundo 
     */
    public int getCodMundo() {
        return ++cod_mundo;
    }

    /**
     * 
     * @return gera a chave primaria para o jogador 
     */
    public int getCodJogador() {
        return ++cod_jogador;
    }

    /**
     * 
     * @return gera a chave primaria para o inimigo 
     */
    public int getCodInimigo() {
        return ++cod_inimigo;
    }
}
