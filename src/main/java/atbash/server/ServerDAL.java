package atbash.server;

public class ServerDAL 
{
	public void userHandler(String user, int id)
	{
		//enter info about user to database. update id in "lastLevelofUser" field if id is the biggest until now
	}
	public Stage[] getAllStages()
	{
		Stage[] result = new Stage[getCount()];
		for (int i=0;i<getCount();i++)
		{
			result[i] = getStage(i);
		}	
		return result;
	}
	public Stage getStage (int id)
	{
		//need to be from the database
		Stage s = null;
		switch (id)
		{
		case 0:
			s = new Stage(id, "121", "aba", "אבא");
			break;
		case 1:
			s = new Stage(id, "131", "aga", "אגא");
			break;
		case 2:
			s = new Stage(id, "141", "ada", "אדא");
			break;
		}
		return s;
	}
	public int getCount()
	{
		int ret = 0;
		//ret = something from database
		ret = 3;
		return ret;
	}
}
