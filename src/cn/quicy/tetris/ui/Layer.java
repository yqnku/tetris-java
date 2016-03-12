package cn.quicy.tetris.ui;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import cn.quicy.tetris.dto.GameDto;
public abstract class Layer extends JPanel
{
	private static final long serialVersionUID = 1L;
	protected int x;
	protected int y;
	protected int w;
	protected int h;
	private final int SIZE = 7;
	public int getSIZE() 
	{
		return SIZE;
	}
	private final int WIN = 64;
	private final Image WINDOW_IMG = new ImageIcon("graphics/window/Window.png").getImage();
	protected GameDto gameDto = null;
	public void setGameDto(GameDto gameDto) 
	{
		this.gameDto = gameDto;
	}
	public Layer(int m_x,int m_y,int m_w,int m_h)
	{
		x = m_x;
		y = m_y;
		w = m_w;
		h = m_h;
	}
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
	public abstract void Paint(Graphics g);
}
