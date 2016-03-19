package cn.quicy.tetris.service;
import java.awt.Point;
import java.util.Random;
import cn.quicy.tetris.dto.GameDto;
import cn.quicy.tetris.entity.GameAct;
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
		if(this.gameDto.isGameover() || this.gameDto.isPause())
			return;
		this.gameDto.getGameAct().Rotate(this.gameDto.getGameMap());
	}
	public void KeyDown() 
	{
		//如果游戏结束，点击开始时，清空map
		if(this.gameDto.isGameover() || this.gameDto.isPause())
		{
			return;	
		}
		//如果不能移动的话，则直接返回
		if(this.gameDto.getGameAct().Move(0, 1,this.gameDto.getGameMap()))
		{
			return;
		}
		//向下
		boolean[][] map = this.gameDto.getGameMap();
		Point[] points = this.gameDto.getGameAct().getActPoints();
		//如果此时不能再移动，则game over，并返回
		if(this.IsGameOver(map, points))
		{
			return;
		}
		for (int i = 0; i < points.length; i++) 
		{
			map[points[i].x][points[i].y] = true;
		}	
		int removeLine = this.RemoveLines(map);
		int addScore = this.AddScore(removeLine);
		this.gameDto.setNowRemoveLine(this.gameDto.getNowRemoveLine()+removeLine);
		this.gameDto.setNowScores(this.gameDto.getNowScores()+addScore);
		this.gameDto.getGameAct().Init(this.gameDto.getNext());
		this.gameDto.setNext(new Random().nextInt(TYPE_COUNT));
		updateLevel();
	}
	private void updateLevel() 
	{
		int[] levelScore = {-1,200,400,600,800,1000,1500,2000,2500,3000};
		int score = this.gameDto.getNowScores();
		for (int i = 0; i < levelScore.length; i++) 
		{
			if (score < levelScore[i]) 
			{
				this.gameDto.setLevel(i);
				return;
			}
		}
	}
	public void KeyLeft() 
	{
		if(this.gameDto.isGameover() || this.gameDto.isPause())
			return;
		this.gameDto.getGameAct().Move(-1, 0,this.gameDto.getGameMap());
	}
	public void KeyRight() 
	{
		if(this.gameDto.isGameover() || this.gameDto.isPause())
			return;
		this.gameDto.getGameAct().Move(1, 0,this.gameDto.getGameMap());
	}
	private boolean IsGameOver(boolean[][] map,Point[] points)
	{
		for (int i = 0; i < points.length; i++) 
		{
			if(!map[points[i].x][points[i].y])
			{
				continue;
			}
			this.gameDto.changeGameOverStatue();
			return true;
		}
		return false;
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
		int level = this.gameDto.getLevel();
		int[] scores  = {0,6+4*level,7+5*level,12+6*level,23+7*level};
		return scores[removeLine];
	}
	public void reStart()
	{
		this.gameDto.NewGame();
	}
}
