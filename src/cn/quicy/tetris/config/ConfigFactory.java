package cn.quicy.tetris.config;
/** 
 * @author  quicy
 */
//TODO 单例？不过也感觉怪怪的，看要不有空改一改
public class ConfigFactory 
{
	private static GameConfig GAME_CONFIG = null;
	static
	{
		try 
		{
			GAME_CONFIG = new GameConfig();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public static GameConfig getGameConfig()
	{
		return GAME_CONFIG;
	}
}
