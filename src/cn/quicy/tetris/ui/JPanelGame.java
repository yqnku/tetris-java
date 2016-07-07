package cn.quicy.tetris.ui;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import cn.quicy.tetris.config.GameConfig;
import cn.quicy.tetris.config.LayerConfig;
import cn.quicy.tetris.controller.PlayerController;
import cn.quicy.tetris.dto.GameDto;
import cn.quicy.tetris.ui.Layer;
/**
 * Game Panel
 * 游戏界面
 * 包括各个界面层的创建
 * @author quicy
 * @version 1.0
 */
public class JPanelGame extends JPanel
{
	private static final long serialVersionUID = 1L;
	/**
	 * Game data transfer object
	 */
	private GameDto gameDto = null;
	/**
	 * Layer
	 * contains each component
	 */
	private List<Layer> layers = null;
	/**
	 * Start，pause and restart button
	 */
	private JButton jButtonStart = null;
	/**
	 * Options button
	 */
	private JButton jButtonOptions = null;
	private PlayerController playerController = null;
	/**
	 * Constructor
	 * Initialize components
	 * @param gameDto Game data transfer object
	 */
	public JPanelGame(GameDto gameDto)
	{
		//set the game data transfer object
		this.gameDto = gameDto;
		//So that JButton. setBounds can work
		this.setLayout(null);
		//Initialize components
		addConfigButton();
		
	}
	/**
	 * Set configure button
	 */
	private void addConfigButton()
	{
		//TODO 写在配置文件里呗
		this.jButtonOptions = new JButton(new ImageIcon("graphics/string/options.png"));
		//设置位置大小
		this.jButtonOptions.setBounds(GameConfig.getFrameConfig().getOptionsButton().getX(), GameConfig.getFrameConfig().getOptionsButton().getY(),GameConfig.getFrameConfig().getOptionsButton().getW(),GameConfig.getFrameConfig().getOptionsButton().getH());
		//TODO 设置按钮功能
		this.jButtonOptions.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("config");
			}
		});
		this.add(jButtonOptions);
	}
	/**
	 * Get game controller
	 * @param playerController 玩家控制器
	 */
	public void setPlayerController(PlayerController playerController)
	{
		this.playerController = playerController;
		this.addKeyListener(this.playerController);
		this.Start();
	}
	/**
	 * Set start/pause/restart button
	 * @param playerController 玩家控制器
	 */
	private void Start()
	{
		//TODO 配置文件
		this.jButtonStart = new JButton(new ImageIcon("graphics/string/start.png"));
		//设置大小位置
		this.jButtonStart.setBounds(GameConfig.getFrameConfig().getStartButton().getX(), GameConfig.getFrameConfig().getStartButton().getY(),GameConfig.getFrameConfig().getStartButton().getW(), GameConfig.getFrameConfig().getStartButton().getH());
		//响应Enter键
		InputMap inputMap = jButtonStart.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = jButtonStart.getActionMap();
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"enter");
        actionMap.put("enter", new AbstractAction()
        {
        	private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent event) 
			{
                startPauseClick(playerController);
		    }
		});
        //按钮按下的反应
		this.jButtonStart.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				startPauseClick(playerController);
			}
		});
		this.add(jButtonStart);
	}
	private void startPauseClick(final PlayerController playerController)
	{
		//Statue:isGameStart,isGamePause,isGameOver
		//Open the frame:start = false;pause = true;over = true;
		//Game start:start = true;pause=false;over=false;
		//Game pause:start=true;pause=true;over=false;
		//Game over:start=true;pause=true;over=true;
		boolean[] nowStatue = {this.gameDto.isGameStart(),this.gameDto.isPause(),this.gameDto.isGameover()};
		boolean[] openFrame = {false,true,true};
		boolean[] gameStart = {true,false,false};
		boolean[] gamePause = {true,true,false};
		//boolean[] gameOver = {true,true,true};
		//TODO 是不是有个数组映射的方法来着？
		if(isEqualBool(nowStatue, openFrame))
		{
			this.gameDto.changePauseStatus();
			this.gameDto.setGameStart(true);
			this.gameDto.changeGameOverStatue();
			playerController.Start(gameDto);
			this.jButtonStart.setIcon(new ImageIcon("graphics/string/pause_button.png"));
		}
		else if(isEqualBool(nowStatue, gameStart))
		{
			this.gameDto.changePauseStatus();
			this.jButtonStart.setIcon(new ImageIcon("graphics/string/start.png"));		
			playerController.callRePaint();
		}
		else if(isEqualBool(nowStatue, gamePause))
		{
			this.gameDto.changePauseStatus();
			this.jButtonStart.setIcon(new ImageIcon("graphics/string/pause_button.png"));
		}
		else 
		{
			this.gameDto.changeGameOverStatue();
			playerController.Start(gameDto);
			this.jButtonStart.setIcon(new ImageIcon("graphics/string/pause_button.png"));
		}
	}
	/**
	 * 判断两个boolean数组是否完全一样
	 * 从而判断当前游戏的状态
	 * @param bool1 第一个boolean数组
	 * @param bool2 第二个boolean数组
	 * @return bool1.isEqual(bool2)
	 */
	private boolean isEqualBool(boolean[] bool1,boolean[] bool2)
	{
		for (int i = 0; i < bool1.length; i++) 
		{
			if (bool1[i] != bool2[i]) 
			{
				return false;
			}
		}
		return true;
	}
	/**
	 * 获得开始/暂停/重新开始按钮
	 * @return jbuttonstart
	 */
	public JButton getjButtonStart()
	{
		return this.jButtonStart;
	}
	/**
	 * Set Layers
	 * 利用反射创建对象
	 */
	private void setLayers()
	{
		try 
		{
			//获得配置文件
			List<LayerConfig> layerConfigs = GameConfig.getFrameConfig().getLayerConfigs();
			//创建游戏层数组
			layers = new ArrayList<Layer>(layerConfigs.size());
			//循环创建所有层对象
			for (LayerConfig layerConfig : layerConfigs) 
			{
				//用字符串来创建对象
				//反射----用反射来创建对象		
				//获得类对象
				Class<?> clsClass = Class.forName(layerConfig.getClassName());
				//获得构造函数
				Constructor<?> ctrConstructor = clsClass.getConstructor(int.class,int.class,int.class,int.class);
				//调用构造函数创建对象
				Layer layer  = (Layer)ctrConstructor.newInstance(layerConfig.getX(),layerConfig.getY(),layerConfig.getW(),layerConfig.getH());
				//配置游戏数据
				layer.setGameDto(gameDto);
				layers.add(layer);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 * Paint Components
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.setLayers();
		for(Layer layer : layers)
		{
			layer.Paint(g);
		}
		//Key Event will work
		this.requestFocus();
	}
}