package atbash.server;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacebookUser
{
    private String facebookID;
    private String name;
    private int currentStageNumber;

}
