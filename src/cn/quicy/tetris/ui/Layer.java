package cn.quicy.tetris.ui;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import cn.quicy.tetris.config.ConfigFactory;
import cn.quicy.tetris.dto.GameDto;
import cn.quicy.tetris.dto.LeaderBoardsDto;
/**
 * Layers abstract
 * @author quicy
 */
//工厂方法模式
public abstract class Layer extends JPanel
{
	private static final long serialVersionUID = 1L;
	/**
	 * x----position-start
	 */
	protected int x;
	/**
	 * y----position-start
	 */
	protected int y;
	/**
	 * w----width
	 */
	protected int w;
	/**
	 * h----height
	 */
	protected int h;
	/**
	 * Border-size
	 */
	private final int SIZE = ConfigFactory.getGameConfig().getSize();
	private final Image NUM_IMG = new ImageIcon("graphics/string/num.png").getImage();
	private final Image SHADOW_IMG = new ImageIcon("graphics/window/shadow.png").getImage();
	/**
	 * Get Border size
	 * @return
	 */
	public int getSIZE() 
	{
		return SIZE;
	}
	/**
	 * border image
	 */
	private final Image WINDOW_IMG = new ImageIcon("graphics/window/Window.png").getImage();
	/**
	 * the height of window_image
	 */
	private final int WIN = WINDOW_IMG.getHeight(null);
	/**
	 * Game data transfer object
	 */
	protected GameDto gameDto = null;
	/**
	 * Set Game data transfer object
	 * @param gameDto
	 */
	public void setGameDto(GameDto gameDto) 
	{
		this.gameDto = gameDto;
	}
	/**
	 * Constructor
	 * @param m_x
	 * @param m_y
	 * @param m_w
	 * @param m_h
	 */
	public Layer(int m_x,int m_y,int m_w,int m_h)
	{
		x = m_x;
		y = m_y;
		w = m_w;
		h = m_h;
	}
	/**
	 * paint create window
	 * @param g
	 */
	protected void CreateWindow(Graphics g)
	{
		g.drawImage(WINDOW_IMG, x, y, x+SIZE, y+SIZE, 0, 0, SIZE, SIZE, null);
		g.drawImage(WINDOW_IMG, x+SIZE, y, x+w-SIZE, y+SIZE, SIZE, 0 , WIN-SIZE, SIZE , null);
		g.drawImage(WINDOW_IMG, x+w-SIZE, y, x+w, y+SIZE, WIN-SIZE, 0, WIN, SIZE, null);
		g.drawImage(WINDOW_IMG, x, y+SIZE, x+SIZE, y+h-SIZE, 0, SIZE, SIZE, WIN-SIZE, null);
		g.drawImage(WINDOW_IMG, x+SIZE, y+SIZE, x+w-SIZE, y+h-SIZE, SIZE, SIZE, WIN-SIZE, WIN-SIZE, null);
		g.drawImage(WINDOW_IMG, x+w-SIZE, y+SIZE, x+w, y+h-SIZE, WIN-SIZE, SIZE, WIN, WIN-SIZE, null);
		g.drawImage(WINDOW_IMG, x, y+h-SIZE, x+SIZE, y+h , 0, WIN-SIZE, SIZE, WIN, null);
		g.drawImage(WINDOW_IMG, x+SIZE, y +h-SIZE, x+w-SIZE, y+h, SIZE, WIN-SIZE, WIN-SIZE, WIN, null);
		g.drawImage(WINDOW_IMG, x+w-SIZE, y+h-SIZE, x+w, y+h, WIN-SIZE, WIN-SIZE, WIN, WIN, null);
	}
	/**
	 * paint inside of component
	 * @param g
	 */
	public abstract void Paint(Graphics g);
	/**
	 * Draw Image at Center
	 * @param g
	 * @param img
	 */
	protected void DrawImageAtCenter(Graphics g,Image img)
	{
		int imgW = img.getWidth(null);
		int imgH = img.getHeight(null);
		int m_x = this.x + ((this.w-imgW)>>1);
		int m_y = this.y + ((this.h-imgH)>>1);
		g.drawImage(img, m_x, m_y, null);
	}
	/**
	 * Draw number
	 * @param p_x relative position
	 * @param p_y relative position
	 * @param number
	 * @param g
	 */
	//TODO config
	protected void DrawNum(int p_x,int p_y,int number,Graphics g)
	{
		String numberString = Integer.toString(number);
		for (int i =   0 ; i < 5-numberString.length() ; i++)
		{
			g.drawImage(NUM_IMG, this.x+p_x+30*i,this.y+p_y,this.x+p_x+26+30*i,this.y+p_y+36,0,0,26, 36, null);
		}
		for (int i = 5-numberString.length() ; i < 5 ; i++)
		{
			int num = Integer.parseInt(String.valueOf(numberString.charAt(i-5+numberString.length())));
			g.drawImage(NUM_IMG, this.x+p_x+30*i,this.y+p_y,this.x+p_x+26+30*i,this.y+p_y+36,26*num,0,26*num+26, 36, null);
		}
	}
	protected void drawShadowAndNum(Graphics g,List<LeaderBoardsDto> recode) 
	{
		for (int i = 0 ; i < 4 ; i++)
		{
			g.drawImage(SHADOW_IMG, this.x+7, this.y+60+50*i, this.x+293, this.y+100+50*i, 0, 0, 300, 40, null);
			g.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 40));
			g.drawString(recode.get(i).getName(), 50, this.y+90+50*i);
			this.DrawNum(140, 62+50*i,recode.get(i).getScore() , g);
		}
	}
}
