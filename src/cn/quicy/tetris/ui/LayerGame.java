package cn.quicy.tetris.ui;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
public class LayerGame extends Layer 
{
	private static final long serialVersionUID = 1L;
	private static Image ACT_IMG = new ImageIcon("graphics/game/rect.png").getImage(); 
	private static Image PAUSE_IMG = new ImageIcon("graphics/string/pause.png").getImage(); 
	public LayerGame(int m_x,int m_y,int m_w,int m_h)
	{
		super(m_x, m_y, m_w, m_h);
	}
	public void Paint(Graphics g)
	{
		this.CreateWindow(g);
		this.DrawAct(g);
		this.DrawPause(g);
	}
	public void DrawAct(Graphics g)
	{
		Point[] points = this.gameDto.getGameAct().getActPoints();
		int typeCode = this.gameDto.getGameAct().getTypeCode();
		for (int i = 0; i < points.length; i++) 
		{
			this.DrawByPoints(points[i].x, points[i].y, typeCode+1, g);
		}
		boolean[][] map = this.gameDto.getGameMap();
		//10 == map.length;
		for (int x = 0 ; x < map.length ; x++)
		{
			for (int y = 0 ; y < map[x].length ; y++)
			{
				if(map[x][y])
				{
					this.DrawByPoints(x, y, 0, g);
				}
			}
		}
	}
	public void DrawByPoints(int m_x,int m_y,int imgIndex,Graphics g)
	{
		g.drawImage(ACT_IMG,  this.x + this.getSIZE() + (m_x<<5) , this.y + this.getSIZE() + (m_y<<5), this.x + this.getSIZE() + (m_x<<5) + 32, this.y + this.getSIZE() + (m_y<<5) + 32, imgIndex<<5, 0, (imgIndex<<5)+32, 32, null);
	}
	public void DrawPause(Graphics g)
	{
		//TODO 绘制图片居中应该写在基类里
		if(this.gameDto.isPause())
		{
			int imgW = PAUSE_IMG.getWidth(null);
			int imgH = PAUSE_IMG.getHeight(null);
			int m_x = this.x + ((this.w-imgW)>>1);
			int m_y = this.y + ((this.h-imgH)>>1);
			g.drawImage(PAUSE_IMG, m_x, m_y, null);
		}
	}
	
}
