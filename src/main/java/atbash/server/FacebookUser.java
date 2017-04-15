package atbash.server;
//TODO:LOMBOK
public class FacebookUser
{
    private String facebookID;
    private String name;
    private int currentStageNumber;

    public FacebookUser()
    {

    }
    public FacebookUser(String facebookID, String name, int currentStageNumber)
    {
        this.facebookID=facebookID;
        this.name=name;
        this.currentStageNumber=currentStageNumber;
    }
    public String getFacebookID()
    {
        return facebookID;
    }
    public String getName()
    {
        return name;
    }
    public int getCurrentStageNumber()
    {
        return currentStageNumber;
    }

}
