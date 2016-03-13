package cn.quicy.tetris.controller;
import cn.quicy.tetris.service.GameService;
import cn.quicy.tetris.ui.JPanelGame;
/**
 * Game Controller
 * @author quicy
 */
public class GameController 
{
	/**
	 * Connect game panel
	 */
	private JPanelGame jPanelGame;
	/**
	 * Connect game service
	 */
	private GameService gameService;
	/**
	 * Constructor
	 * @param jPanelGame
	 * @param gameService
	 */
	public GameController(JPanelGame jPanelGame,GameService gameService)
	{
		this.jPanelGame = jPanelGame;
		this.gameService = gameService;
	}
	/**
	 * Key up
	 */
	public void KeyUp() 
	{
		this.gameService.KeyUp();
		this.rePaint();
	}
	/**
	 * Key Down
	 */
	public void KeyDown() 
	{
		this.gameService.KeyDown();
		this.rePaint();
	}
	/**
	 * Key Left
	 */
	public void KeyLeft() 
	{
		this.gameService.KeyLeft();
		this.rePaint();
	}
	/**
	 * Key Right
	 */
	public void KeyRight() 
	{
		this.gameService.KeyRight();
		this.rePaint();
	}
	public void rePaint()
	{
		this.jPanelGame.repaint();
	}
}
