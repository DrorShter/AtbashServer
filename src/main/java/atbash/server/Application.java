package atbash.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Application {//check that it really works

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        ForServer ver= new ForServer();
        try {
            System.out.println(ver.getStage(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}