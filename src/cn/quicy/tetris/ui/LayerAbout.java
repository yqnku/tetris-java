package cn.quicy.tetris.ui;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
/**
 * about----logo
 * @author quicy
 */
public class LayerAbout extends Layer 
{
	private static final long serialVersionUID = 1L;
	/**
	 * about----logo
	 */
	private final Image ABOUT_IMG = new ImageIcon("graphics/string/about.png").getImage();
	/**
	 * Constructor
	 * @param m_x
	 * @param m_y
	 * @param m_w
	 * @param m_h
	 */
	public LayerAbout(int m_x,int m_y,int m_w,int m_h)
	{
		super(m_x, m_y, m_w, m_h);
	}
	/**
	 * Paint inside Component
	 */
	@Override
	public void Paint(Graphics g)
	{
		this.CreateWindow(g);
		this.DrawImageAtCenter(g, ABOUT_IMG);
	}
}