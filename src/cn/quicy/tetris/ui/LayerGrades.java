package cn.quicy.tetris.ui;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
public class LayerGrades extends Layer 
{
	private static final long serialVersionUID = 1L;
	private final Image GRADES_IMG = new ImageIcon("graphics/string/point.png").getImage();
	private final Image RMLINNE_IMG = new ImageIcon("graphics/string/rmline.png").getImage();
	private final Image NUM_IMG = new ImageIcon("graphics/string/num.png").getImage();
	public LayerGrades(int m_x,int m_y,int m_w,int m_h)
	{
		super(m_x, m_y, m_w, m_h);
	}
	public void Paint(Graphics g)
	{
		this.CreateWindow(g);
		g.drawImage(GRADES_IMG, this.x+16, this.y+35, null);
		g.drawImage(RMLINNE_IMG, this.x+16, this.y+90, null);
		DrawNum(100,32,this.gameDto.getNowScores(), g);
		DrawNum(100, 87,this.gameDto.getNowRemoveLine(), g);
	}
	public void DrawNum(int p_x,int p_y,int grades,Graphics g)
	{
		//StringBuilder grade g= new StringBuilder();
		String grade = Integer.toString(grades);
		for (int i =   0 ; i < 5-grade.length() ; i++)
		{
			g.drawImage(NUM_IMG, this.x+p_x+30*i,this.y+p_y,this.x+p_x+26+30*i,this.y+p_y+36,0,0,26, 36, null);
		}
		for (int i = 5-grade.length() ; i < 5 ; i++)
		{
			int num = Integer.parseInt(String.valueOf(grade.charAt(i-5+grade.length())));
			g.drawImage(NUM_IMG, this.x+p_x+30*i,this.y+p_y,this.x+p_x+26+30*i,this.y+p_y+36,26*num,0,26*num+26, 36, null);
		}
	}
}
