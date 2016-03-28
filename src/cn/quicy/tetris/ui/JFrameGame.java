package cn.quicy.tetris.ui;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
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
		this.setVisible(true);
		this.setSize(GameConfig.getFrameConfig().getWidth(),GameConfig.getFrameConfig().getHeight());
		this.setTitle(GameConfig.getFrameConfig().getTitle());
		//When we close the window,then the program will be closed
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Get screen size
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		this.setLocation(screen.width-GameConfig.getFrameConfig().getWidth() >> 1, (screen.height-GameConfig.getFrameConfig().getHeight() >> 1));
		this.setContentPane(jPanelGame);
		//this.setResizable(false);
	}
}
