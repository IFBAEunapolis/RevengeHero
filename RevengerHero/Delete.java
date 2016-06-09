import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Delete {

    Conexao con;

    public Delete(String banco, String usuario, String senha){
            con = new Conexao(banco, usuario, senha);
    }

    public void deleteGame(int codigoMundo){

        PreparedStatement stm;
        PreparedStatement stm1;
        PreparedStatement stm2;
        String deleteJogador = "delete from jogador where FK_mundo= ?;";
        String deleteInimigo = "delete from registro_inimigo where FK_mundo= ?;";
        String deleteMundo = "delete from mundo where PK_mundo= ?;";

        con.connect();

        try {
            stm = con.conexao.prepareStatement(deleteJogador);
            stm1 = con.conexao.prepareStatement(deleteInimigo);
            stm2 = con.conexao.prepareStatement(deleteMundo);

            stm.setInt(1, codigoMundo);
            stm1.setInt(1, codigoMundo);
            stm2.setInt(1, codigoMundo);

            stm.execute();
            stm1.execute();
            stm2.execute();

        } catch (SQLException e) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        }

        con.disconnect();
    }
}
