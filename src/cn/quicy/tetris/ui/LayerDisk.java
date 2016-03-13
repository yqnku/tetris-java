package cn.quicy.tetris.ui;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
/**
 * Local Disk Records
 * @author quicy
 */
public class LayerDisk extends Layer 
{
	private static final long serialVersionUID = 1L;
	/**
	 * Personal Leader Boards
	 */
	private final Image DISK_IMG = new ImageIcon("graphics/string/disk.png").getImage();
	/**
	 * Constructor
	 * @param m_x
	 * @param m_y
	 * @param m_w
	 * @param m_h
	 */
	public LayerDisk(int m_x,int m_y,int m_w,int m_h)
	{
		super(m_x, m_y, m_w, m_h);
	}
	/**
	 * Paint inside Component
	 */
	public void Paint(Graphics g)
	{
		this.CreateWindow(g);
		g.drawImage(DISK_IMG, this.x+16, this.y+16, null);
		//this.drawShadowAndNum(g,this.gameDto.getPersonalLeaderBoardsRecode());
	}
}
