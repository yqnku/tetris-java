package cn.quicy.tetris.ui;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
/**
 * Layer Game
 * @author quicy
 */
public class LayerGame extends Layer 
{
	private static final long serialVersionUID = 1L;
	/**
	 * the diamond
	 */
	private static Image ACT_IMG = new ImageIcon("graphics/game/rect.png").getImage(); 
	/**
	 * Pause---String
	 */
	private static Image PAUSE_IMG = new ImageIcon("graphics/string/pause.png").getImage(); 
	private static Image GAMEOVER_IMG = new ImageIcon("graphics/string/gameover.png").getImage(); 
	/**
	 * Constructor
	 * @param m_x
	 * @param m_y
	 * @param m_w
	 * @param m_h
	 */
	public LayerGame(int m_x,int m_y,int m_w,int m_h)
	{
		super(m_x, m_y, m_w, m_h);
	}
	/**
	 * Paint inside Component
	 */
	public void Paint(Graphics g)
	{
		this.CreateWindow(g);
		this.DrawAct(g);
		this.DrawPause(g);
	}
	/**
	 * 
	 * @param m_x
	 * @param m_y
	 * @param imgIndex
	 * @param g
	 */
	private void DrawByPoints(int m_x,int m_y,int imgIndex,Graphics g)
	{
		g.drawImage(ACT_IMG,  this.x + this.getSIZE() + (m_x<<5) , this.y + this.getSIZE() + (m_y<<5), this.x + this.getSIZE() + (m_x<<5) + 32, this.y + this.getSIZE() + (m_y<<5) + 32, imgIndex<<5, 0, (imgIndex<<5)+32, 32, null);
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
	public void DrawPause(Graphics g)
	{
		//当游戏尚未开始，且游戏暂停时，显示暂停文字
		if(this.gameDto.isGameStart() && this.gameDto.isPause())
		{
			this.DrawImageAtCenter(g, PAUSE_IMG);
		}
		//游戏已经开始以及在结束时显示game over
		if(this.gameDto.isGameStart() && this.gameDto.isGameover())
		{
			this.DrawImageAtCenter(g, GAMEOVER_IMG);
		}
	}
	
}
