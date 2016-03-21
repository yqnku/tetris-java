package cn.quicy.tetris.ui;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
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
	static
	{
		//TODO config
		NEXT_IMGS = new Image[7];
		for(int i = 0 ; i < 7 ; i++)
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
