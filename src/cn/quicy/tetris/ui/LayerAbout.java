package cn.quicy.tetris.ui;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
public class LayerAbout extends Layer {
	private static final long serialVersionUID = 1L;
	private final Image ABOUT_IMG = new ImageIcon("graphics/string/about.png").getImage();
	public LayerAbout(int m_x,int m_y,int m_w,int m_h)
	{
		super(m_x, m_y, m_w, m_h);
	}
	public void Paint(Graphics g)
	{
		this.CreateWindow(g);
		g.drawImage(ABOUT_IMG, this.x+8, this.y+8, null);
	}
}
