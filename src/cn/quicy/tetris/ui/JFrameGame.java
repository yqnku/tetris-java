package cn.quicy.tetris.ui;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import cn.quicy.tetris.ui.JPanelGame;
/**
 * Game frame
 * @author quicy
 */
public class JFrameGame extends JFrame
{
	private static final long serialVersionUID = 1L;
	/**
	 * Constant----window.width
	 */
	private static final int WINDOW_WIDTH = 1100;
	/**
	 * Constant----window.height
	 */
	private static final int WINDOW_HEIGHT = 700;
	/**
	 * Constructor
	 * @param jPanelGame
	 */
	public JFrameGame(JPanelGame jPanelGame)
	{
		this.setVisible(true);
		this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		this.setTitle("Tetris");
		//When we close the window,then the program will be closed
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Get screen size
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		this.setLocation(screen.width-WINDOW_WIDTH >> 1, (screen.height-WINDOW_HEIGHT >> 1));
		this.setContentPane(jPanelGame);
		//this.setResizable(false);
	}
}
