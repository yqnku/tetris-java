package cn.quicy.tetris.ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerLevel extends Layer {
	private static final long serialVersionUID = 1L;
	private final Image Level_IMG = new ImageIcon("graphics/string/level.png").getImage();
	private final Image LEVEL_NUM_IMG = new ImageIcon("graphics/string/num.png").getImage();
	public LayerLevel(int m_x,int m_y,int m_w,int m_h)
	{
		super(m_x, m_y, m_w, m_h);
	}
	public void Paint(Graphics g)
	{
		this.CreateWindow(g);
		g.drawImage(Level_IMG, this.x+16, this.y+16, null);
		DrawLevel(this.gameDto.getLevel(), g);
	}
	public void DrawLevel(int level,Graphics g)
	{
		g.drawImage(LEVEL_NUM_IMG, this.x+32,this.y+58,this.x+58,this.y+94,26*level,0,26*level+26, 36, null);
	}
}
