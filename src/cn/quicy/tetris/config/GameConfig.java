package cn.quicy.tetris.config;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/** 
 * @author  quicy
 */
//TODO 单例模式
public class GameConfig 
{
	private static FrameConfig frameConfig = null;
	private static SystemConfig systemConfig = null;
	private static DataConfig dataConfig = null;
	static
	{
		try 
		{
			//创建XML读取器
			SAXReader reader = new SAXReader();
			//读取XML文件
			Document document = reader.read("config/config.xml");
			//获取根结点
			Element gameElement = document.getRootElement();
			frameConfig = new FrameConfig(gameElement);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public static FrameConfig getFrameConfig() 
	{
		return frameConfig;
	}
	public static SystemConfig getSystemConfig() 
	{
		return systemConfig;
	}
	public static DataConfig getDataConfig() 
	{
		return dataConfig;
	}
	//构造器私有化
	private GameConfig(){}
}
