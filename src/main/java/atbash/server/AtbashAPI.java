package atbash.server;
import net.minidev.json.JSONObject;
import org.json.JSONException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

//Path: http://localhost/<appln-folder-name>/register
@RestController

public class AtbashAPI
{
    private ServerDAL dal = new ServerDAL();
    @RequestMapping(value = "/getAllStages", method= GET, produces={"application/json; charset=UTF-8"})
    public Stage[] getAllStages() throws SQLException {
        return dal.getAllStages();
    }
    @RequestMapping(value = "/getCount", method= GET, produces={"application/json; charset=UTF-8"})
    public String getCount() throws SQLException {
        String ret;
        ret = String.valueOf(dal.getCount());
        System.out.println("Inside AtbashServerAPI: getCount" + ret);
        return ret;
    }
}