package cn.quicy.tetris.ui;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
/**
 * Scores
 * @author quicy
 */
public class LayerScore extends Layer 
{
	private static final long serialVersionUID = 1L;
	/**
	 * Score--String
	 */
	private final Image GRADES_IMG = new ImageIcon("graphics/string/point.png").getImage();
	/**
	 * Remove---String
	 */
	private final Image RMLINNE_IMG = new ImageIcon("graphics/string/rmline.png").getImage();
	/**
	 * Constructor
	 * @param m_x
	 * @param m_y
	 * @param m_w
	 * @param m_h
	 */
	public LayerScore(int m_x,int m_y,int m_w,int m_h)
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
		//TODO config
		g.drawImage(GRADES_IMG, this.x+8, this.y+30, null);
		g.drawImage(RMLINNE_IMG, this.x+12, this.y+80, null);
		this.DrawNum(130,32,this.gameDto.getNowScores(), g);
		this.DrawNum(130, 87,this.gameDto.getNowRemoveLine(), g);
	}
}
