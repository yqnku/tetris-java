package cn.quicy.tetris.ui;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
/**
 * About
 * Logo
 * @author quicy
 * @version 1.0
 */
public class LayerAbout extends Layer 
{
	private static final long serialVersionUID = 1L;
	/**
	 * About-Logo Image
	 */
	private final Image ABOUT_IMG = new ImageIcon("graphics/string/about.png").getImage();
	/**
	 * Constructor
	 * @param m_x start-position-x
	 * @param m_y start-position-y
	 * @param m_w width
	 * @param m_h height
	 */
	public LayerAbout(int m_x,int m_y,int m_w,int m_h)
	{
		super(m_x, m_y, m_w, m_h);
	}
	/**
	 * Paint Component
	 * @param g Graphics
	 */
	@Override
	public void Paint(Graphics g)
	{
		this.CreateWindow(g);
		this.DrawImageAtCenter(g, ABOUT_IMG);
	}
}