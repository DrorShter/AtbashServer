package atbash.server;

import java.sql.*;
import java.util.List;

public class ForServer
{
    private Connection connection = null;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement = null;
    public static void main()
    {
        System.out.println("hello");
    }

    public ForServer()
    {
        connection=getConnection();
    }
    private Connection getConnection() {
        Connection con=null;
        String name = "AtbashServer.db";
        String DB_PATH = "C:\\Users\\User\\Desktop\\atbash\\Atbash\\app\\src\\main\\assets";
        System.out.println(DB_PATH+name);
        try {
            DriverManager.registerDriver((Driver) Class.forName("org.sqlite.JDBC").newInstance());
        } catch (Exception e) {
            throw new RuntimeException("Failed to register SQLDroidDriver");
        }
        try {
            con = DriverManager.getConnection("jdbc:sqlite:" +DB_PATH+ "/AtbashServer.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }


    public atbash.server.Stage getStage(int num) throws SQLException {
        String question, answer, clue, query="SELECT * FROM Level WHERE NumberOfQuestion=?";
        preparedStatement=connection.prepareStatement(query);
        preparedStatement.setInt(1, num);
        resultSet= preparedStatement.executeQuery();
        question=resultSet.getString("Question");
        answer=resultSet.getString("Answer");
        clue=resultSet.getString("Clue");
        System.out.println(resultSet);
        return new Stage(num, question, clue, answer);
    }
}
