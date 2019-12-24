package DI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleConnection {
    
    private static final SingleConnection singleConnect=new SingleConnection();

    public static Connection getSingleConnect() {
        return singleConnect.connect();
    }
    
    private Connection connect() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/word_game_db?characterEncoding=UTF-8";
            String username = "root";
            String password = "527618349";
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
}
