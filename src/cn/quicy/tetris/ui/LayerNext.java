package cn.quicy.tetris.ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerNext extends Layer 
{
	private static final long serialVersionUID = 1L;
	private static final Image[] NEXT_IMGS;
	static
	{
		NEXT_IMGS = new Image[7];
		for(int i = 0 ; i < 7 ; i++)
		{
			NEXT_IMGS[i] = new ImageIcon("graphics/game/"+Integer.toString(i)+".png").getImage();
		}
	}
	public LayerNext(int m_x,int m_y,int m_w,int m_h)
	{
		super(m_x, m_y, m_w, m_h);
	}
	public void Paint(Graphics g)
	{
		this.CreateWindow(g);
		DrawImageAtCenter(g, NEXT_IMGS[this.gameDto.getNext()]);
	}
	private void DrawImageAtCenter(Graphics g,Image img)
	{
		int imgW = img.getWidth(null);
		int imgH = img.getHeight(null);
		int m_x = this.x + ((this.w-imgW)>>1);
		int m_y = this.y + ((this.h-imgH)>>1);
		g.drawImage(img, m_x, m_y, null);
	}
}
