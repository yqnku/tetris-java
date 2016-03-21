package cn.quicy.tetris.config;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/** 
 * @author  quicy
 */
public class ConfigReader 
{
	public static void readConfig() throws DocumentException
	{
		SAXReader reader = new SAXReader();
		Document document = reader.read("config/config.xml");
		Element gameElement = document.getRootElement();
		Element frameElement = gameElement.element("frame");
		String string = frameElement.attributeValue("size");
		System.out.println(string);
		List<Element> layElements = frameElement.elements("layer");
		for (Element layElement : layElements) 
		{
			System.out.println(layElement.attributeValue("className"));
		}
	}
	public static void main(String[] args) throws Exception 
	{
		System.out.println("ConfigReader.main()");
		//readConfig();
		GameConfig gameConfig = new GameConfig();
	}
}
