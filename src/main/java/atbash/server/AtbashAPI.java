package atbash.server;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

//Path: http://localhost/<appln-folder-name>/register
@RestController

public class AtbashAPI
{
    /*
    private ServerDAL dal = new ServerDAL();
    @RequestMapping(value = "/getAllStages", method= RequestMethod.GET, produces={"application/json; charset=UTF-8"})
    public Stage[] getAllStages()
    {
        try {
            return dal.getAllStages();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    /*
    @GET
    @Path("/getCount")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCount()
    {
        String ret;
        ret = String.valueOf(dal.getCount());
        System.out.println("Inside AtbashServerAPI: getCount" + ret);
        return ret;
    }
    //getStage is just for check, not for use.
    @GET
    @Path("/getStage")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getStage() throws JSONException
    {
        JSONObject json = new JSONObject();
        json.put("number", 1);
        json.put("question", "2");
        json.put("clue", "3");
        json.put("answer", "4");
        return json;
    }*/
    //example for parameters input : (@QueryParam("user") String user, @QueryParam("id") int id, @QueryParam("getLastNumber") String getLastNumber, @QueryParam("getAllStages") String getAllStages
}