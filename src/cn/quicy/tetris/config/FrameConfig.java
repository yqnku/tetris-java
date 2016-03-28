package cn.quicy.tetris.config;
import java.util.ArrayList;
import java.util.List;
import org.dom4j.Element;
/** 
 * @author  quicy
 */
public class FrameConfig 
{
	private int width;
	private int height;
	private int size;
	private int padding;
	private int imageCount;
	private String title;
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
	public int getPadding() 
	{
		return padding;
	}
	public int getImageCount() 
	{
		return imageCount;
	}
	public String getTitle() 
	{
		return title;
	}
	public List<LayerConfig> getLayerConfigs() 
	{
		return layerConfigs;
	}
	public ButtonConfig getStartButton() 
	{
		return startButton;
	}
	public ButtonConfig getOptionsButton() 
	{
		return optionsButton;
	}
	private List<LayerConfig> layerConfigs;
	private ButtonConfig startButton;
	private ButtonConfig optionsButton;
	public FrameConfig(Element e) 
	{
		this.title = e.attributeValue("title");
		this.setUIConfig(e.element("frame"));
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
		this.padding = Integer.parseInt(element.attributeValue("padding"));
		this.imageCount = Integer.parseInt(element.attributeValue("imageCount"));
		@SuppressWarnings("unchecked")
		List<Element> layerElements = element.elements("layer");
		layerConfigs = new ArrayList<LayerConfig>();
		for (Element layerElement : layerElements) 
		{
			LayerConfig layerConfig = new LayerConfig(layerElement.attributeValue("className"),Integer.parseInt(layerElement.attributeValue("x")),Integer.parseInt(layerElement.attributeValue("y")),Integer.parseInt(layerElement.attributeValue("w")),Integer.parseInt(layerElement.attributeValue("h")));
			layerConfigs.add(layerConfig);
		}
		Element startElement = element.element("start");
		startButton = new ButtonConfig(Integer.parseInt(startElement.attributeValue("x")), Integer.parseInt(startElement.attributeValue("y")), Integer.parseInt(startElement.attributeValue("w")),Integer.parseInt(startElement.attributeValue("h")) );
		Element optionsElement = element.element("options");
		optionsButton = new ButtonConfig(Integer.parseInt(optionsElement.attributeValue("x")),Integer.parseInt(optionsElement.attributeValue("y")), Integer.parseInt(optionsElement.attributeValue("w")), Integer.parseInt(optionsElement.attributeValue("h")));
	}
}
