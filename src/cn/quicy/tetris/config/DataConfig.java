package cn.quicy.tetris.config;
import org.dom4j.Element;
/** 
 * @author  quicy
 */
public class DataConfig 
{
	private String playerName;
	private int score;
	public String getPlayerName() 
	{
		return playerName;
	}
	public int getScore() 
	{
		return score;
	}
	public DataConfig(Element e)
	{
		
	}
}
