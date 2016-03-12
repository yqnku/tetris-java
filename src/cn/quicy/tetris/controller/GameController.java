package cn.quicy.tetris.controller;
import cn.quicy.tetris.service.GameService;
import cn.quicy.tetris.ui.JPanelGame;
public class GameController 
{
	private JPanelGame jPanelGame;
	private GameService gameService;
	public GameController(JPanelGame jPanelGame,GameService gameService)
	{
		this.jPanelGame = jPanelGame;
		this.gameService = gameService;
	}
	public void KeyUp() 
	{
		this.gameService.KeyUp();
		this.jPanelGame.repaint();
	}
	public void KeyDown() {
		this.gameService.KeyDown();
		this.jPanelGame.repaint();
	}
	public void KeyLeft() {
		this.gameService.KeyLeft();
		this.jPanelGame.repaint();
	}
	public void KeyRight() {
		this.gameService.KeyRight();
		this.jPanelGame.repaint();
	}
}
