package cn.quicy.tetris.ui;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Map;

import javax.swing.ImageIcon;
/**
 * DataBase
 * @author quicy
 */
public class LayerData extends Layer 
{
	private static final long serialVersionUID = 1L;
	/**
	 * Global Leader Boards
	 */
	private final Image DATA_IMG = new ImageIcon("graphics/string/db.png").getImage();
	/**
	 * Constructor
	 * @param m_x
	 * @param m_y
	 * @param m_w
	 * @param m_h
	 */
	public LayerData(int m_x,int m_y,int m_w,int m_h)
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
		g.drawImage(DATA_IMG, this.x+16, this.y+16, null);	
		//this.drawShadowAndNum(g,this.gameDto.getGlobalLeaderBoardsRecode());
	}
}
