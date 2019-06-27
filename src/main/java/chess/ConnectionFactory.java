package chess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String CONNECTION_FAILURE_MSG = "잘못된 Connection 입니다.";

    private static final String SERVER = "localhost";
    private static final String DATABASE = "chess";
    private static final String USER_NAME = "skygl";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + SERVER + "/"
                    + DATABASE + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", USER_NAME, PASSWORD);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        if (con == null)
            throw new ConnectionFailureException(CONNECTION_FAILURE_MSG);

        return con;
    }
}
