package atbash.server;

import java.sql.*;

public class ServerDAL
{
	private Connection connection = null;
	private ResultSet resultSet;
	private PreparedStatement preparedStatement = null;

	public ServerDAL() {connection=getConnection();}

	private Connection getConnection() {
		Connection con=null;
		String name = "atbash/server/AtbashServer.db";
		String DB_PATH_DROR = "C:\\magshimim\\atbashserver\\atbash\\src\\main\\java\\atbash\\server";
        String DB_PATH_NOAM = "C:\\Users\\User\\Documents\\magshimim\\FinalProject\\atbashserver\\src\\main\\resources";
        String DB_PATH = DB_PATH_DROR; //CHANGE IF OTHER COMPUTER!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		System.out.println(DB_PATH+name);
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e)
		{
			System.out.println("faild connecting");
		}
		try {
			con = DriverManager.getConnection("jdbc:sqlite:" +DB_PATH+ "\\AtbashServer.db");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public void userHandler(String id, String user, int lastLevelofUser) throws SQLException {
		//enter info about user to database. update id in "lastLevelofUser" field if id is the biggest until now
		String query="INSERT INTO Users (UserName, LastLevelOfUser) VALUES (?, ?)";
		preparedStatement=connection.prepareStatement(query);
		preparedStatement.setString(1, user);
		preparedStatement.setInt(2, lastLevelofUser);
		preparedStatement.execute();
	}

	public Stage[] getAllStages() throws SQLException {
		Stage[] result = new Stage[getCount()];
		for(int i=0;i<getCount()-1;i++) {
			result[i] = getStage(i+1);
		}
		return result;
	}
	public Stage getStage (int num) throws SQLException {
		String question, answer, clue, query="SELECT * FROM Level WHERE NumberOfQuestion=?";//k
		preparedStatement=connection.prepareStatement(query);
		preparedStatement.setInt(1, num);
		resultSet= preparedStatement.executeQuery();
		System.out.println(resultSet.isClosed());
		question=resultSet.getString("Question");
		answer=resultSet.getString("Answer");
		clue=resultSet.getString("Clue");
		System.out.println(num+clue+answer+question);
		return new Stage(num, question, clue, answer);
	}
	public int getCount() throws SQLException {
		int ret = 0;
		String query="SELECT count(*) FROM Level";
		preparedStatement=connection.prepareStatement(query);
		resultSet= preparedStatement.executeQuery();
		resultSet.next();
		ret=resultSet.getInt(1);
		return ret;
	}//
	public FacebookUser getUser (String id) throws SQLException {
		String query="SELECT * FROM Users WHERE ID=?";
		preparedStatement=connection.prepareStatement(query);
		preparedStatement.setString(1, id);
		resultSet= preparedStatement.executeQuery();
		return new FacebookUser(resultSet.getString("ID"), resultSet.getString("UserName"), resultSet.getInt("LastLevelOfUser"));
	}
	public void updateLastLevel(String id, int last) throws SQLException {
		String query="UPDATE Users SET LastLevelOfUser =? WHERE ID=?";
		preparedStatement=connection.prepareStatement(query);
		preparedStatement.setInt(1, last);
		preparedStatement.setString(2, id);
		resultSet= preparedStatement.executeQuery();
	}
	public boolean isUserExist(String id) throws SQLException {
		String query="SELECT * FROM Users WHERE ID=?";
		preparedStatement=connection.prepareStatement(query);
		preparedStatement.setString(1, id);
		resultSet= preparedStatement.executeQuery();
		if(resultSet==null)
		{
			return false;
		}
		return true;
	}
}
