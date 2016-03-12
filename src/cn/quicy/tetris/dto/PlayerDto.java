package cn.quicy.tetris.dto;
public class PlayerDto 
{
	private String nameString;
	private int grades;
	public PlayerDto(String nameString, int grades) 
	{
		super();
		this.nameString = nameString;
		this.grades = grades;
	}
	public String getNameString() 
	{
		return nameString;
	}
	public void setNameString(String nameString) 
	{
		this.nameString = nameString;
	}
	public int getGrades() 
	{
		return grades;
	}
	public void setGrades(int grades) 
	{
		this.grades = grades;
	}
	
}
