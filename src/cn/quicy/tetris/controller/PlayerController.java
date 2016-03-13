package cn.quicy.tetris.controller;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
	public void Start()
	{
		//TODO 很多要改的地方
		Thread thread = new Thread()
		{
			@Override
			public void run() 
			{
				while (true)
				{
					try 
					{
						gameController.KeyDown();
						sleep(1000);
					} 
					catch (InterruptedException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
	}
}
