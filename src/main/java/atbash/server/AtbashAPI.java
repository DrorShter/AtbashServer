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
        name = englishToHebrew(name);
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
        return dal.getFacebookFreinds(ids);
    }
    @RequestMapping(value = "/getFacebookGlobal", method= GET, produces={"application/json; charset=UTF-8"})
    public FacebookUser[] getFacebookGlobal() throws SQLException {
        //TODO: return the 10 most successful FacebookUsers. If less then 10, null;
        System.out.println("Inside AtbashServerAPI: getFacebookGlobal");
        return dal.getFacebookGlobal();
    }
    private void printer(String[] ids)
    {
        System.out.println("printer:  /n");
        for (int i=0;i<ids.length;i++)
        {
            System.out.println(ids[i] + "\n");
        }
    }
    public String englishToHebrew(String english)
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
                    default:
                        name += english.charAt(i);
                }
            }
        }
        System.out.println("name = " + name);
        return name;
    }
}