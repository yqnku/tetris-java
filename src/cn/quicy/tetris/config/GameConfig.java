package cn.quicy.tetris.config;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
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
	private List<LayerConfig> layerConfigs;
	public GameConfig() throws Exception
	{
		SAXReader reader = new SAXReader();
		Document document = reader.read("config/config.xml");
		Element gameElement = document.getRootElement();
		Element frameElement = gameElement.element("frame");
		this.width = Integer.parseInt(frameElement.attributeValue("width"));
		this.height = Integer.parseInt(frameElement.attributeValue("height"));
		this.size = Integer.parseInt(frameElement.attributeValue("size"));
		List<Element> layerElements = frameElement.elements("layer");
		layerConfigs = new ArrayList<LayerConfig>();
		for (Element layerElement : layerElements) 
		{
			LayerConfig layerConfig = new LayerConfig();
			layerConfig.setClassName(layerElement.attributeValue("className"));
			layerConfig.setX(Integer.parseInt(layerElement.attributeValue("x")));
			layerConfig.setY(Integer.parseInt(layerElement.attributeValue("y")));
			layerConfig.setW(Integer.parseInt(layerElement.attributeValue("w")));
			layerConfig.setH(Integer.parseInt(layerElement.attributeValue("h")));
			layerConfigs.add(layerConfig);
		}
		System.out.println("new");
	}
}
