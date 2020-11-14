package Server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Users {
    public List<UserEntity> usersList;
    private PreparedStatement ps;
    private Dbconn Dbсonn;

    public Users() throws SQLException {
        usersList = new LinkedList<>();
        ps = Dbconn.getInstance()
                .connection()
                .prepareStatement("SELECT * FROM users_t");
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            UserEntity userEntity = new UserEntity();
            userEntity.setLogin(set.getString("login"));
            userEntity.setPassword(set.getString("password"));
            userEntity.setRooth(set.getString("rooth"));
            usersList.add(userEntity);
        }
        ps.close();
    }
    public String getNick(String login, String password) {
        for (UserEntity u : usersList) {
            if (u.login.equals(login) && u.password.equals(password)) {
                return u.rooth;
            }
        }
        return "notok";
    }

    public String addNick(String login, String password)  {
        try {
            ps = Dbсonn.getInstance()
                    .connection()
                    .prepareStatement("INSERT INTO users_t (login, password, rooth) VALUES (?,?,?)");
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, login);
            ps.executeUpdate();
            ps.close();
            return "addOk";
        } catch (SQLException s){
            return "addNotOk";
        }
    }
    public static class UserEntity {
        private String login;
        private String password;
        private String rooth;

        public void setLogin(String login) {
            this.login = login;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setRooth(String rooth) {
            this.rooth = rooth;
        }

    }
}