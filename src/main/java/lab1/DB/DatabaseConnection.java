package lab1.DB;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/Labpreparcial";
    private static final String USER = "root";
    private static final String PASSWORD = "Leonel/2005";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
