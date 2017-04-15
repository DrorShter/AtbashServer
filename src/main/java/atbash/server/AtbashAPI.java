package atbash.server;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
//
@RestController
public class AtbashAPI
{
    private ServerDAL dal = new ServerDAL();
    @RequestMapping(value = "/getAllStages", method= GET, produces={"application/json; charset=UTF-8"})
    public Stage[] getAllStages() throws SQLException
    {
        System.out.println("Inside AtbashServerAPI: getAllStages");
        return dal.getAllStages();

    }
    @RequestMapping(value = "/getCount", method= GET, produces={"application/json; charset=UTF-8"})
    public String getCount() throws SQLException
    {
        String ret = String.valueOf(dal.getCount());
        System.out.println("Inside AtbashServerAPI: getCount");
        return ret;
    }
    @RequestMapping(value = "/updateFacebookUser/{facebookID}/{name}/{currentStageNumber}", method= GET, produces={"application/json; charset=UTF-8"})
    public Boolean updateFacebookUser(@PathVariable("facebookID") String facebookID, @PathVariable("name") String name, @PathVariable("currentStageNumber") String currentStageNumber) throws SQLException {
        //TODO: Does not work with hebrew
        FacebookUser user = new FacebookUser(facebookID,name,Integer.parseInt(currentStageNumber));
        System.out.println(user.getFacebookID() + user.getName() + user.getCurrentStageNumber());
        if(!dal.isUserExist(facebookID))
        {
            dal.userHandler(facebookID, name, Integer.parseInt(currentStageNumber));
        }
        else
        {
            dal.updateLastLevel(facebookID, Integer.parseInt(currentStageNumber));
        }
        System.out.println("Inside AtbashServerAPI: updateFacebookUser");
        return new Boolean(true);
    }
    @RequestMapping(value = "/getFacebookFriends/{ids}", method= GET, produces={"application/json; charset=UTF-8"})
    public FacebookUser[] getFacebookFriends(@PathVariable("ids") String[] ids) throws SQLException {
        List<FacebookUser> list= new ArrayList<FacebookUser>();
        System.out.println("Inside AtbashServerAPI: getFacebookFriends");
        printer(ids); //debug
        for(int i=0; i<ids.length; i++)
        {
            list.add(dal.getUser(ids[i]));
        }
        list.sort(Comparator.comparing(FacebookUser::getCurrentStageNumber));
        FacebookUser[] facebookUsers = new FacebookUser[10];//debug
        for(int i=0;i<10;i++)
        {
            facebookUsers[i]=list.get(i);
        }
        return facebookUsers;
    }
    @RequestMapping(value = "/getFacebookGlobal", method= GET, produces={"application/json; charset=UTF-8"})
    public FacebookUser[] getFacebookGlobal()
    {
        //TODO: return the 10 most successful FacebookUsers. If less then 10, null;
        System.out.println("Inside AtbashServerAPI: getFacebookGlobal");
        FacebookUser[] facebookUsers = new FacebookUser[10];//debug
        facebookUsers[0] = new FacebookUser("1", "global1", 2); //debug
        return facebookUsers;
    }
    private void printer(String[] ids)
    {
        System.out.println("printer:  /n");
        for (int i=0;i<ids.length;i++)
        {
            System.out.println(ids[i] + "\n");
        }
    }
}