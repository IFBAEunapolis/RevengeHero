import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que faz a conexão e depois fecha o banco de dados.
 * @author i9 Games
 * @version 1.0.0
 */

public class Conexao {

    public Connection conexao;
    private final String banco;
    private final String usuario;
    private final String senha;
    private final String driver = "com.mysql.jdbc.Driver";

    public Conexao(String database, String user, String password) {
            this.banco = database;
            this.usuario = user;
            this.senha = password;
    }

    public void connect(){
        try{
            Class.forName(driver).newInstance();
            conexao=DriverManager.getConnection(banco,usuario,senha);
        }catch(SQLException e){
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disconnect(){
        try {
            conexao.close();
        } catch (SQLException e) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
