package cn.quicy.tetris.ui;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import cn.quicy.tetris.config.GameConfig;
/**
 * The next diamond
 * @author quicy
 */
public class LayerNext extends Layer 
{
	private static final long serialVersionUID = 1L;
	/**
	 * Next diamonds
	 */
	private static final Image[] NEXT_IMGS;
	private static final int IMAGE_COUNT = GameConfig.getFrameConfig().getImageCount();
	static
	{
		NEXT_IMGS = new Image[IMAGE_COUNT];
		for(int i = 0 ; i < IMAGE_COUNT ; i++)
		{
			NEXT_IMGS[i] = new ImageIcon("graphics/game/"+Integer.toString(i)+".png").getImage();
		}
	}
	/**
	 * Constructor
	 * @param m_x
	 * @param m_y
	 * @param m_w
	 * @param m_h
	 */
	public LayerNext(int m_x,int m_y,int m_w,int m_h)
	{
		super(m_x, m_y, m_w, m_h);
	}
	/**
	 * Paint inside Component
	 */
	public void Paint(Graphics g)
	{
		this.CreateWindow(g);
		this.DrawImageAtCenter(g, NEXT_IMGS[this.gameDto.getNext()]);
	}
}
