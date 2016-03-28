package cn.quicy.tetris.ui;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
/**
 * Level
 * @author quicy
 */
public class LayerLevel extends Layer {
	private static final long serialVersionUID = 1L;
	/**
	 * level----String
	 */
	private final Image Level_IMG = new ImageIcon("graphics/string/level.png").getImage();
	/**
	 * Level----Num
	 */
	private final Image LEVEL_NUM_IMG = new ImageIcon("graphics/string/num.png").getImage();
	/**
	 * Constructor
	 * @param m_x
	 * @param m_y
	 * @param m_w
	 * @param m_h
	 */
	public LayerLevel(int m_x,int m_y,int m_w,int m_h)
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
		g.drawImage(Level_IMG, this.x+26, this.y+16, null);
		this.DrawLevel(this.gameDto.getLevel(), g);
	}
	/**
	 * draw level
	 * @param level
	 * @param g
	 */
	public void DrawLevel(int level,Graphics g)
	{
		if(level == 1)
		{
			g.drawImage(LEVEL_NUM_IMG, this.x+38,this.y+58,this.x+64,this.y+94,26*level,0,26*level+26, 36, null);
		}
		else 
		{
			g.drawImage(LEVEL_NUM_IMG, this.x+48,this.y+58,this.x+74,this.y+94,26*level,0,26*level+26, 36, null);
		}
	}
}
