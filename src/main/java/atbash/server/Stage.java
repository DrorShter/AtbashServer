package atbash.server;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Stage
{
	private int number;
	private String question;
    private String clue;
    private String answer;
}
