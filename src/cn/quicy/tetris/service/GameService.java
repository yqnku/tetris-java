package cn.quicy.tetris.service;
import java.awt.Point;
import java.util.Random;
import cn.quicy.tetris.dto.GameDto;
import cn.quicy.tetris.entity.GameAct;
//TODO 注释
public class GameService 
{
	private GameDto gameDto;
	private Random random = new Random();
	private final int TYPE_COUNT = 7;
	public GameService(GameDto gameDto)
	{
		this.gameDto = gameDto;
		GameAct gameAct = new GameAct(random.nextInt(TYPE_COUNT));
		gameDto.setGameAct(gameAct);
	}
	public void KeyUp() 
	{
		if(this.gameDto.isPause())
			return;
		this.gameDto.getGameAct().Rotate(this.gameDto.getGameMap());
	}
	public void KeyDown() 
	{
		if(this.gameDto.isPause())
			return;
		//TODO 这个地方的逻辑
		if(this.gameDto.getGameAct().Move(0, 1,this.gameDto.getGameMap()))
		{
			return;
		}
		boolean[][] map = this.gameDto.getGameMap();
		Point[] points = this.gameDto.getGameAct().getActPoints();
		for (int i = 0; i < points.length; i++) 
		{
			map[points[i].x][points[i].y] = true;
		}
		int removeLine = this.RemoveLines(map);
		int addScore = this.AddScore(removeLine);
		this.gameDto.setNowRemoveLine(this.gameDto.getNowRemoveLine()+removeLine);
		this.gameDto.setNowScores(this.gameDto.getNowScores()+addScore);
		this.gameDto.getGameAct().Init(this.gameDto.getNext());
		this.gameDto.setNext(new Random().nextInt(7));
	}
	
	public void KeyLeft() 
	{
		if(this.gameDto.isPause())
			return;
		this.gameDto.getGameAct().Move(-1, 0,this.gameDto.getGameMap());
	}
	public void KeyRight() 
	{
		if(this.gameDto.isPause())
			return;
		this.gameDto.getGameAct().Move(1, 0,this.gameDto.getGameMap());
	}
	private boolean CanRemoveLine(int y,boolean[][] map) 
	{
		for (int x = 0; x < 10; x++) 
		{
			if(!map[x][y])
				return false;
		}
		return true;
	}
	private void RemoveLine(int y, boolean[][] map) 
	{
		for (int x = 0; x < 10; x++) 
		{
			for(int row = y ; row > 0 ; row--)
			{
				map[x][row] = map[x][row-1];
			}
		}
		for (int x = 0; x < 10; x++) 
		{
			map[x][0] = false;
		}
	}
	/**
	 * 可消行
	 * @param map
	 * @return
	 */
	private int RemoveLines(boolean[][] map) 
	{
		int removeLine = 0;
		for (int y = 0; y < 18; y++) 
		{
			if(this.CanRemoveLine(y, map))
			{
				removeLine += 1;
				this.RemoveLine(y,map);
			}
		}
		return removeLine;
	}
	private int AddScore(int removeLine) 
	{
		int[] scores  = {0,10,12,18,30};
		return scores[removeLine];
	}
}
