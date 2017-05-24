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
    private ServerDAL dal = new ServerDAL(); //member
    //This function gets void and returns stage[]
    //It returns all stages in db
    @RequestMapping(value = "/getAllStages", method= GET, produces={"application/json; charset=UTF-8"})
    public Stage[] getAllStages() throws SQLException
    {
        System.out.println("Inside AtbashServerAPI: getAllStages");
        return dal.getAllStages();

    }
    //This function gets void and returns String
    //It returns count of stages in db
    @RequestMapping(value = "/getCount", method= GET, produces={"application/json; charset=UTF-8"})
    public String getCount() throws SQLException //getCount
    {
        String ret = String.valueOf(dal.getCount());
        System.out.println("Inside AtbashServerAPI: getCount");
        return ret;
    }
    //This function gets String, String, String and returns String
    //It updates the data about facebookUser in db
    @RequestMapping(value = "/updateFacebookUser/{facebookID}/{name}/{currentStageNumber}", method= GET, produces={"application/json; charset=UTF-8"})
    public Boolean updateFacebookUser(@PathVariable("facebookID") String facebookID, @PathVariable("name") String name, @PathVariable("currentStageNumber") String currentStageNumber) throws SQLException {
        name = englishToName(name);
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
    //This function gets String[] and returns  FacebookUser[]
    //It returns all users in the ids
    @RequestMapping(value = "/getFacebookFriends/{ids}", method= GET, produces={"application/json; charset=UTF-8"})
    public FacebookUser[] getFacebookFriends(@PathVariable("ids") String[] ids) throws SQLException {
        return dal.getFacebookFriends(ids);
    }
    //This function gets void and returns FacebookUser[]
    //It returns data for display facebookGlobal
    @RequestMapping(value = "/getFacebookGlobal", method= GET, produces={"application/json; charset=UTF-8"})
    public FacebookUser[] getFacebookGlobal() throws SQLException {
        System.out.println("Inside AtbashServerAPI: getFacebookGlobal");
        return dal.getFacebookGlobal();
    }
    //This function gets String and returns String
    //It converts english back to hebrew (if necessary)
    public String englishToName(String english)
    {
        String name = "";
        System.out.println("in english to hebrew");
        if (english.charAt(0) == '@')
        {
            for (int i=1;i<english.length();i++)
            {
                switch (english.charAt(i))
                {
                    case 'a':
                        name += 'א';
                        break;
                    case 'b':
                        name += "ב";
                        break;
                    case 'c':
                        name += 'ג';
                        break;
                    case 'd':
                        name += 'ד';
                        break;
                    case 'e':
                        name += 'ה';
                        break;
                    case 'f':
                        name += 'ו';
                        break;
                    case 'g':
                        name += 'ז';
                        break;
                    case 'h':
                        name += 'ח';
                        break;
                    case 'i':
                        name += 'ט';
                        break;
                    case 'j':
                        name += 'י';
                        break;
                    case 'k':
                        name += 'כ';
                        break;
                    case 'l':
                        name += 'ל';
                        break;
                    case 'm':
                        name += 'מ';
                        break;
                    case 'n':
                        name += 'נ';
                        break;
                    case 'o':
                        name += 'ס';
                        break;
                    case 'p':
                        name += 'ע';
                        break;
                    case 'q':
                        name += 'פ';
                        break;
                    case 'r':
                        name += 'צ';
                        break;
                    case 's':
                        name += 'ק';
                        break;
                    case 't':
                        name += 'ר';
                        break;
                    case 'u':
                        name += 'ש';
                        break;
                    case 'v':
                        name += 'ת';
                        break;
                    case 'w':
                        name += 'ך';
                        break;
                    case 'x':
                        name += 'ם';
                        break;
                    case 'y':
                        name += 'ן';
                        break;
                    case 'z':
                        name += 'ף';
                        break;
                    case '#':
                        name += 'ץ';
                        break;
                    case '+':
                        name += ' ';
                    default:
                        name += english.charAt(i);
                }
            }
        }
        else
        {
            name=english;
        }
        System.out.println("name = " + name);
        return name;
    }
}