package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ResourceBundle;

public class Dbconn {
    private static Dbconn instance;
    private static Connection conn;

    private Dbconn() throws SQLException {
        ResourceBundle rb = ResourceBundle.getBundle( "db" );
        String host = rb.getString( "host" );
        String port = rb.getString( "port" );
        String db = rb.getString( "db" );
        String user = rb.getString( "user" );
        String password = rb.getString( "password" );

        // String url = "jdbc:mysql://127.0.0.1:3306/test"; // можно так создать адрес
        String jdbcURL = MessageFormat.format( "jdbc:mysql://{0}:{1}/{2}",
                host, port, db );
        conn = DriverManager.getConnection( jdbcURL, user, password );  // подключаем через драйвер менеджер
    }

    public static Dbconn getInstance() {
        if (instance == null) {
            try {
                instance = new Dbconn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    public Connection connection() {
        return conn;
    }
}
