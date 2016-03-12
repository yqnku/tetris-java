package cn.quicy.tetris.ui;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
public class LayerDisk extends Layer 
{
	private static final long serialVersionUID = 1L;
	private final Image DISK_IMG = new ImageIcon("graphics/string/disk.png").getImage();
	public LayerDisk(int m_x,int m_y,int m_w,int m_h)
	{
		super(m_x, m_y, m_w, m_h);
	}
	public void Paint(Graphics g)
	{
		this.CreateWindow(g);
		g.drawImage(DISK_IMG, this.x+16, this.y+16, null);
	}
}
