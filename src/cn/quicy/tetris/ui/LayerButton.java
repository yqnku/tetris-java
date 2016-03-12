package cn.quicy.tetris.ui;
import java.awt.Graphics;
public class LayerButton extends Layer 
{
	private static final long serialVersionUID = 1L;
	public LayerButton(int m_x,int m_y,int m_w,int m_h)
	{
		super(m_x, m_y, m_w, m_h);
	}
	public void Paint(Graphics g)
	{
		this.CreateWindow(g);
	}
}
