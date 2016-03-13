package cn.quicy.tetris.ui;
import java.awt.Graphics;
/**
 * start/pause/configure button
 * @author quicy
 */
public class LayerButton extends Layer 
{
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor
	 * @param m_x
	 * @param m_y
	 * @param m_w
	 * @param m_h
	 */
	public LayerButton(int m_x,int m_y,int m_w,int m_h)
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
	}
}
