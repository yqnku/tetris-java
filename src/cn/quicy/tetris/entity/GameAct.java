package cn.quicy.tetris.entity;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
/**
 * Game Act---entity
 * @author quicy
 */
//TODO 注释
public class GameAct
{
	private Point[] actPoints;
	private static List<Point[]> TYPE_CONFIG;
	private int typeCode;
	static
	{
		TYPE_CONFIG = new ArrayList<Point[]>(7);
		TYPE_CONFIG.add(new Point[]{new Point(4,0),new Point(3,0),new Point(5,0),new Point(6,0)});
		TYPE_CONFIG.add(new Point[]{new Point(4,0),new Point(3,0),new Point(5,0),new Point(4,1)});
		TYPE_CONFIG.add(new Point[]{new Point(4,0),new Point(3,0),new Point(5,0),new Point(3,1)});
		TYPE_CONFIG.add(new Point[]{new Point(4,0),new Point(5,0),new Point(3,1),new Point(4,1)});
		TYPE_CONFIG.add(new Point[]{new Point(4,0),new Point(5,0),new Point(4,1),new Point(5,1)});
		TYPE_CONFIG.add(new Point[]{new Point(4,0),new Point(3,0),new Point(5,0),new Point(5,1)});
		TYPE_CONFIG.add(new Point[]{new Point(4,0),new Point(3,0),new Point(4,1),new Point(5,1)});
	}
	public GameAct(int i)
	{
		this.Init(i);
	}
	public Point[] getActPoints() 
	{
		return actPoints;
	}
	/**
	 * 移动的方向
	 * @param x_Move
	 * @param y_Move
	 * @param gameMap
	 * @return boolean false表示不能移动
	 */
	public boolean Move(int x_Move,int y_Move,boolean[][] gameMap)
	{
		for (int i = 0; i < actPoints.length; i++) 
		{
			int newX = actPoints[i].x + x_Move;
			int newY = actPoints[i].y + y_Move;
			if (IsOverMap(newX, newY,gameMap))
				return false;
		}
		for (int i = 0; i < actPoints.length; i++) 
		{
			actPoints[i].x += x_Move;
			actPoints[i].y += y_Move;
		}
		return true;
	}
	public void Rotate(boolean[][] gameMap)
	{
		if (this.typeCode == 4)
			return;
		for (int i = 1; i < actPoints.length; i++) 
		{
			int newX = actPoints[0].y + actPoints[0].x - actPoints[i].y;
			int newY = actPoints[0].y - actPoints[0].x + actPoints[i].x;
			if(this.IsOverMap(newX, newY,gameMap))
				return;
		}
		for (int i = 1; i < actPoints.length; i++) 
		{
			int newX = actPoints[0].y + actPoints[0].x - actPoints[i].y;
			int newY = actPoints[0].y - actPoints[0].x + actPoints[i].x;
			actPoints[i].x = newX;
			actPoints[i].y = newY;
		}
	}

	private boolean IsOverMap(int p_x,int p_y,boolean[][] gameMap)
	{
		if ((p_x < 0) || (p_x > 9) || (p_y < 0) || (p_y > 17) || (gameMap[p_x][p_y]))
			return true;
		return false;
	}
	public void Init(int typeCode) 
	{
		this.typeCode = typeCode ;
		Point[] points = TYPE_CONFIG.get(typeCode);
		actPoints = new Point[points.length];
		for (int i = 0; i < points.length; i++) 
		{
			actPoints[i] = new Point(points[i].x,points[i].y);
		}
	}
	public int getTypeCode()
	{
		return typeCode;
	}
}
