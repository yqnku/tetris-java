package cn.quicy.tetris.dto;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import cn.quicy.tetris.entity.GameAct;
/**
 * Game data transfer object
 * @author quicy
 */
public class GameDto 
{
	/**
	 * Global Leader Boards Recode
	 */
	private List<LeaderBoardsDto> globalLeaderBoardsRecode;
	/**
	 * Personal Leader Boards Recode
	 */
	private List<LeaderBoardsDto> personalLeaderBoardsRecode;
	/**
	 * Game map
	 * "true" representative there is a diamond
	 * "false" representative there is not a diamond
	 */
	private boolean[][] gameMap;
	//TODO annotation
	private GameAct gameAct;
	/**
	 * Type of diamonds counts
	 */
	private final int TYPE_COUNT = 7;
	/**
	 * The next diamonds type
	 * Use Integer to show different diamonds
	 */
	private int next;
	/**
	 * Level
	 * Level is to determine the speed of diamonds down
	 */
	private int level;
	/**
	 * Now scores
	 */
	private int nowScores;
	/**
	 * Count of remove lines
	 */
	private int nowRemoveLine;
	/**
	 * Distinguish the suspended state
	 */
	private boolean pause;
	/**
	 * Constructor
	 */
	public GameDto() 
	{
		InitDto();
	}
	/**
	 * Initialize
	 */
	public void InitDto()
	{
		this.level = 1;
		this.nowScores = 0;
		this.nowRemoveLine = 0;
		this.next = new Random().nextInt(TYPE_COUNT);
		//TODO 这个地方到底应该是true还是false呢
		this.pause = false;
		this.gameMap = new boolean[10][18];
		//TODO 数据操作？ LeaderBoardsDTO
		this.globalLeaderBoardsRecode = new ArrayList<LeaderBoardsDto>();
		this.personalLeaderBoardsRecode = new ArrayList<LeaderBoardsDto>();
		for (int i = 0 ; i < 4 ; i++)
		{
			globalLeaderBoardsRecode.add(new LeaderBoardsDto());
			personalLeaderBoardsRecode.add(new LeaderBoardsDto());
		}
	}
	/**
	 * get game map
	 * @return
	 */
	public boolean[][] getGameMap() 
	{
		return gameMap;
	}
	/**
	 * set game map
	 * @param gameMap
	 */
	public void setGameMap(boolean[][] gameMap) 
	{
		this.gameMap = gameMap;
	}
	/**
	 * get game act
	 * @return
	 */
	public GameAct getGameAct() 
	{
		return gameAct;
	}
	/**
	 * set game act
	 * @param gameAct
	 */
	public void setGameAct(GameAct gameAct) 
	{
		this.gameAct = gameAct;
	}
	/**
	 * get next
	 * @return
	 */
	public int getNext() 
	{
		return next;
	}
	/**
	 * set next
	 * @param next
	 */
	public void setNext(int next) 
	{
		this.next = next;
	}
	/**
	 * get level
	 * @return
	 */
	public int getLevel() 
	{
		return level;
	}
	/**
	 * set level
	 * @param level
	 */
	public void setLevel(int level) 
	{
		this.level = level;
	}
	/**
	 * get now scores
	 * @return
	 */
	public int getNowScores() 
	{
		return nowScores;
	}
	/**
	 * set now scores
	 * @param nowScores
	 */
	public void setNowScores(int nowScores) 
	{
		this.nowScores = nowScores;
	}
	/**
	 * get now remove lines
	 * @return
	 */
	public int getNowRemoveLine() 
	{
		return nowRemoveLine;
	}
	/**
	 * set now remove lines
	 * @param nowRemoveLine
	 */
	public void setNowRemoveLine(int nowRemoveLine) 
	{
		this.nowRemoveLine = nowRemoveLine;
	}
	/**
	 * Distinguish the suspended state
	 * @return
	 */
	public boolean isPause() 
	{
		return pause;
	}
	/**
	 * Change suspended status
	 */
	public void ChangeStatus() 
	{
		this.pause = !this.pause;
	}
	/**
	 * Get Global Leader Boards Recode
	 * @return
	 */
	public List<LeaderBoardsDto> getGlobalLeaderBoardsRecode() 
	{
		return globalLeaderBoardsRecode;
	}
	/**
	 * Set Global Leader Boards Recode
	 * @param globalLeaderBoardsRecode
	 */
	public void setGlobalLeaderBoardsRecode(List<LeaderBoardsDto> globalLeaderBoardsRecode) 
	{
		this.globalLeaderBoardsRecode = globalLeaderBoardsRecode;
	}
	/**
	 * Get Personal Leader Boards Recode
	 * @return
	 */
	public List<LeaderBoardsDto> getPersonalLeaderBoardsRecode() 
	{
		return personalLeaderBoardsRecode;
	}
	/**
	 * Set personal Leader Boards Recode
	 * @param personalLeaderBoardsRecode
	 */
	public void setPersonalLeaderBoardsRecode(
			List<LeaderBoardsDto> personalLeaderBoardsRecode) 
	{
		this.personalLeaderBoardsRecode = personalLeaderBoardsRecode;
	}
}