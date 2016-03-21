package cn.quicy.tetris.ui;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

import cn.quicy.tetris.config.ConfigFactory;
import cn.quicy.tetris.config.GameConfig;
import cn.quicy.tetris.ui.JPanelGame;
/**
 * Game frame
 * @author quicy
 */
public class JFrameGame extends JFrame
{
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor
	 * @param jPanelGame
	 */
	public JFrameGame(JPanelGame jPanelGame)
	{
		GameConfig gameConfig = ConfigFactory.getGameConfig();
		this.setVisible(true);
		this.setSize(gameConfig.getWidth(),gameConfig.getHeight());
		this.setTitle(gameConfig.getTitle());
		//When we close the window,then the program will be closed
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Get screen size
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		this.setLocation(screen.width-gameConfig.getWidth() >> 1, (screen.height-gameConfig.getHeight() >> 1));
		this.setContentPane(jPanelGame);
		//this.setResizable(false);
	}
}
