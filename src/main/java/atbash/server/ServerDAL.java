package atbash.server;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ServerDAL
{
	private Connection connection = null; //member
	private ResultSet resultSet; //member
	private PreparedStatement preparedStatement = null; //member

	//constructor of ServerDAL
	public ServerDAL() {connection=getConnection();}

    //This function gets void and returns Connection
    //It returns connection to db
	private Connection getConnection()
	{
		Connection con=null;
		String name = "atbash/server/AtbashServer.db";
		String DB_PATH_DROR = "C:\\magshimim\\atbashserver\\atbash\\src\\main\\java\\atbash\\server";
        String DB_PATH_NOAM = "C:\\Users\\User\\Documents\\magshimim\\FinalProject\\atbashserver\\src\\main\\java\\atbash\\server";
        String DB_PATH = DB_PATH_NOAM; //CHANGE IF OTHER COMPUTER!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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

    //This function gets String, String, int and returns void
    //It enters info about user to database. update id in "lastLevelofUser" field if id is the biggest until now
	public void userHandler(String id, String user, int lastLevelofUser) throws SQLException {

		String query="INSERT INTO Users (ID, UserName, LastLevelOfUser) VALUES (?, ?, ?)";
		System.out.println(query);
		preparedStatement=connection.prepareStatement(query);
		preparedStatement.setString(1, id);
		preparedStatement.setString(2, user);
		preparedStatement.setInt(3, lastLevelofUser);
		preparedStatement.execute();
	}

    //This function gets void and returns Stage[]
    //It returns all stages in db
	public Stage[] getAllStages() throws SQLException {
		Stage[] result = new Stage[getCount()];
		for(int i=0;i<getCount()-1;i++) {
			result[i] = getStage(i+1);
		}
		return result;
	}

    //This function gets num and returns Stage
    //It returns specific stage from db (by id)
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

    //This function gets num and returns int
    //It returns number of stages stored in db
	public int getCount() throws SQLException {
		int ret = 0;
		String query="SELECT count(*) FROM Level";
		preparedStatement=connection.prepareStatement(query);
		resultSet= preparedStatement.executeQuery();
		resultSet.next();
		ret=resultSet.getInt(1);
		return ret;
	}

    //This function gets id and returns String
    //It returns facebookUser from db
	public FacebookUser getUser (String id) throws SQLException {
		String query="SELECT * FROM Users WHERE ID=?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		if(!rs.next())
        {
            return null;
        }
        String us =rs.getString("userName");
        int tmp=rs.getInt("LastLevelOfUser");
        FacebookUser F=new FacebookUser(id, us, tmp);
        return F;
	}

    //This function gets id and returns last
    //It updates the lastLevel of user
	public void updateLastLevel(String id, int last) throws SQLException {
		String query="UPDATE Users SET LastLevelOfUser =? WHERE ID=?";
		PreparedStatement ps = preparedStatement = connection.prepareStatement(query);
        ps.setInt(1, last);
		ps.setString(2, id);
		ps.execute();
	}

    //This function gets String and returns boolean
    //It checks if user exist
	public boolean isUserExist(String id) throws SQLException {
		String query="SELECT * FROM Users WHERE ID=?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}

    //This function gets String and returns FacebookUser[]
    //It returns all user has the ids
	public FacebookUser[] getFacebookFriends(String ids[]) throws SQLException {
		List<FacebookUser> list= new ArrayList<FacebookUser>();
		for(int i=0; i<ids.length; i++)
		{
			if(ids[i]!=null)
			{
				FacebookUser f = getUser(ids[i]);
				if (f != null)
				{
					list.add(f);
				}
			}
		}
		if (list.size() != 0)
        {
            list.sort(Comparator.comparing(FacebookUser::getCurrentStageNumber));
        }
		return list.stream().toArray(FacebookUser[]::new);
	}

    //This function gets void and returns FacebookUser[]
    //It returns 10 best users in the game
	public FacebookUser[] getFacebookGlobal() throws SQLException
    {
        String query = "SELECT * FROM Users ORDER BY LastLevelOfUser DESC LIMIT 10";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<FacebookUser> l = new ArrayList<>();
        while(rs.next())
        {
            FacebookUser f = new FacebookUser(rs.getString("ID"), rs.getString("UserName"), rs.getInt("LastLevelOfUser"));
            l.add(f);
        }
        return l.stream().toArray(FacebookUser[]::new);
    }

}
