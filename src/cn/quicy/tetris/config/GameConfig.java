package cn.quicy.tetris.config;
import java.util.ArrayList;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/** 
 * @author  quicy
 */
public class GameConfig 
{
	private int width;
	private int height;
	private int size;
	private String title;
	private List<LayerConfig> layerConfigs;
	public GameConfig() throws Exception
	{
		//创建XML读取器
		SAXReader reader = new SAXReader();
		//读取XML文件
		Document document = reader.read("config/config.xml");
		//获取根结点
		Element gameElement = document.getRootElement();
		//获取title
		this.title = gameElement.attributeValue("title");
		//配置窗口参数
		this.setUIConfig(gameElement.element("frame"));
		//配置系统参数
		this.setSystemConfig(gameElement.element("system"));
		//配置数据访问参数
		this.setDataConfig(gameElement.element("data"));
	}
	public int getWidth() 
	{
		return width;
	}
	public int getHeight() 
	{
		return height;
	}
	public int getSize() 
	{
		return size;
	}
	public String getTitle() 
	{
		return title;
	}
	public List<LayerConfig> getLayerConfigs() 
	{
		return layerConfigs;
	}
	/**
	 * 配置窗口
	 * @param element 参数
	 */
	private void setUIConfig(Element element)
	{
		this.width = Integer.parseInt(element.attributeValue("width"));
		this.height = Integer.parseInt(element.attributeValue("height"));
		this.size = Integer.parseInt(element.attributeValue("size"));
		@SuppressWarnings("unchecked")
		List<Element> layerElements = element.elements("layer");
		layerConfigs = new ArrayList<LayerConfig>();
		for (Element layerElement : layerElements) 
		{
			LayerConfig layerConfig = new LayerConfig(layerElement.attributeValue("className"),Integer.parseInt(layerElement.attributeValue("x")),Integer.parseInt(layerElement.attributeValue("y")),Integer.parseInt(layerElement.attributeValue("w")),Integer.parseInt(layerElement.attributeValue("h")));
			layerConfigs.add(layerConfig);
		}
	}
	//TODO 以后的配置
	/**
	 * 配置系统参数
	 * @param element
	 */
	private void setSystemConfig(Element element)
	{
		
	}
	/**
	 * 配置数据访问参数
	 * @param element
	 */
	private void setDataConfig(Element element)
	{
		
	}
}
