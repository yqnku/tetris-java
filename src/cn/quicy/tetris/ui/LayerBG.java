package cn.quicy.tetris.ui;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
/**
 * Background
 * @author quicy
 */
public class LayerBG extends Layer 
{
	private static final long serialVersionUID = 1L;
	private final Image BG_IMG = new ImageIcon("graphics/background/Sea.jpg").getImage();
	/**
	 * Constructor
	 * @param m_x
	 * @param m_y
	 * @param m_w
	 * @param m_h
	 */
	public LayerBG(int m_x,int m_y,int m_w,int m_h)
	{
		super(m_x, m_y, m_w, m_h);
	}
	/**
	 * Paint inside Component
	 */
	@Override
	public void Paint(Graphics g)
	{
		g.drawImage(BG_IMG, this.x, this.y, this.w,this.h,0,0,800,450,null);
	}
}
