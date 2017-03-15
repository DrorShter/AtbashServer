package atbash.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootApplication
public class Application {//check that it really works

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        ServerDAL ver=new ServerDAL();
        int count=0;
        Stage s=new Stage(0,"","","");
        try {
            s= ver.getStage(1);
            count=ver.getCount();
            ver.userHandler("Yossi", 2
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(s.getQuestion()+s.getAnswer()+"\n"+count);
    }
}