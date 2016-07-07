package cn.quicy.tetris.dto;
/** 
 * @author  quicy
 */
public class LeaderBoardsDto
{
	private String name;
	private int score;
	public LeaderBoardsDto()
	{
		this.name = "quicy";
		this.score = 30;
	}
	public LeaderBoardsDto(String name, int score) 
	{
		this.name = name;
		this.score = score;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public int getScore() 
	{
		return score;
	}
	public void setScore(int score) 
	{
		this.score = score;
	}
}
