package atbash.server;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Stage
{
	private int number; //number (id)
	private String question; //question
    private String clue; //clue to question
    private String answer; //answer to question


}
