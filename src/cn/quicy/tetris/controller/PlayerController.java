package cn.quicy.tetris.controller;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class PlayerController extends KeyAdapter
{
	private GameController gameControl;
	public PlayerController(GameController gameControl)
	{
		this.gameControl = gameControl;
	}
	@Override
	public void keyReleased(KeyEvent key) 
	{
		switch (key.getKeyCode()) {
		case KeyEvent.VK_UP:
			this.gameControl.KeyUp();break;
		case KeyEvent.VK_DOWN:
			this.gameControl.KeyDown();break;
		case KeyEvent.VK_LEFT:
			this.gameControl.KeyLeft();break;
		case KeyEvent.VK_RIGHT:
			this.gameControl.KeyRight();break;
		default:
			break;
		}
	}
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
						gameControl.KeyDown();
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
