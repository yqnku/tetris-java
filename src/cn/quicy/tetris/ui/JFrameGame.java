package cn.quicy.tetris.ui;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import cn.quicy.tetris.config.GameConfig;
import cn.quicy.tetris.ui.JPanelGame;
/**
 * 游戏窗口
 * 用来配置窗口的高度以及宽度
 * 配置窗口的标题
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
		//设置可见状态
		this.setVisible(true);
		//设置不可改变大小
		this.setResizable(false);
		//设置大小
		this.setSize(GameConfig.getFrameConfig().getWidth(),GameConfig.getFrameConfig().getHeight());
		//设置标题
		this.setTitle(GameConfig.getFrameConfig().getTitle());
		//When we close the window,then the program will be closed
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Get screen size
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		//居中
		this.setLocation(screen.width-GameConfig.getFrameConfig().getWidth() >> 1, (screen.height-GameConfig.getFrameConfig().getHeight() >> 1));
		//设置panel
		this.setContentPane(jPanelGame);
	}
}
