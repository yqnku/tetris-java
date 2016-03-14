package cn.quicy.tetris.controller;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import cn.quicy.tetris.dto.GameDto;
/**
 * Play Controller
 * @author quicy
 */
public class PlayerController extends KeyAdapter
{
	/**
	 * Connect game controller
	 */
	private GameController gameController;
	/**
	 * Constructor
	 * @param gameController
	 */
	private GameDto gameDto;
	public PlayerController(GameController gameController)
	{
		this.gameController = gameController;
	}
	/**
	 * Key released
	 */
	@Override
	public void keyReleased(KeyEvent key) 
	{
		switch (key.getKeyCode()) {
		case KeyEvent.VK_UP:
			this.gameController.KeyUp();break;
		case KeyEvent.VK_DOWN:
			this.gameController.KeyDown();break;
		case KeyEvent.VK_LEFT:
			this.gameController.KeyLeft();break;
		case KeyEvent.VK_RIGHT:
			this.gameController.KeyRight();break;
		default:
			break;
		}
	}
	/**
	 * Start and Pause function
	 */
	public void Start(GameDto gameDto)
	{
		this.gameDto = gameDto;
		//TODO 要判断游戏是刚开始，还是游戏结束了以后再开始
		if(this.gameDto.isGameStart())
		{
			this.gameController.getGameService().reStart();
		}
		Thread thread = new MainThread();
		thread.start();
	}
	private class MainThread extends Thread
	{
		@Override
		public void run()
		{
			while (true)
			{
				try 
				{
					//TODO 结束游戏
					if(gameDto.isGameStart() && gameDto.isGameover())
					{
						gameController.changeStartButtonIcon();
						break;
					}	
					sleep(1000);
					if(gameDto.isPause())
					{
						gameController.rePaint();
						continue;
					}
					gameController.KeyDown();		
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
}