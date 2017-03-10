package atbash.server;

public class Stage
{
	private int number;
	public String question;
	public String clue;
	public String answer;
	public Stage(int id, String question, String clue, String answer)
	{
		this.number = id;
		this.question = question;
		this.clue = clue;
		this.answer = answer;
	}
	public int getNumber()
	{
		return number;
	}
	public String getQuestion()
	{
		return question;
	}
	public String getClue()
	{
		return clue;
	}
	public String getAnswer()
	{
		return answer;
	}
}
