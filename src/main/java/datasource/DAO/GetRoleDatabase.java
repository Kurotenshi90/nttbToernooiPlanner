package datasource.DAO;

import domain.Klasse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Peter-Paul on 19/05/2016.
 */
public class GetRoleDatabase extends DAO {
    public String getRole(String username){
        connect();
        ResultSet resultSet = null;
        String role = "";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("select mp.name as Gebruiker, rp.name as Rol\n" +
                    "from sys.database_role_members drm\n" +
                    "join sys.database_principals rp on (drm.role_principal_id = rp.principal_id)\n" +
                    "join sys.database_principals mp on (drm.member_principal_id = mp.principal_id)\n" +
                    "WHERE mp.name = ?");

            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
               role = resultSet.getNString(2);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
        return role;
    }
}
