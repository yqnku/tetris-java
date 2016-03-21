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

import cn.quicy.tetris.config.ConfigFactory;
import cn.quicy.tetris.config.GameConfig;
import cn.quicy.tetris.config.LayerConfig;
import cn.quicy.tetris.controller.PlayerController;
import cn.quicy.tetris.dto.GameDto;
import cn.quicy.tetris.ui.Layer;
/**
 * Game Panel
 * @author quicy
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
	 * Start and pause button
	 */
	private JButton jButtonStart = null;
	/**
	 * Configure button
	 */
	private JButton jButtonConfig = null;
	private GameConfig gameConfig = null;
	/**
	 * Constructor
	 * @param gameDto
	 */
	public JPanelGame(GameDto gameDto)
	{
		//set the game data transfer object
		this.gameDto = gameDto;
		//获得游戏配置
		this.gameConfig = ConfigFactory.getGameConfig();
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
		this.jButtonConfig = new JButton(new ImageIcon("graphics/string/options.png"));
		this.jButtonConfig.setBounds(920, 50, 110, 48);
		this.jButtonConfig.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("config");
			}
		});
		this.add(jButtonConfig);
	}
	/**
	 * Get game controller
	 * @param playerControl
	 */
	public void getGameController(PlayerController playerControl)
	{
		this.addKeyListener(playerControl);
		this.Start(playerControl);
	}
	/**
	 * Set start/pause button
	 * @param playerController
	 */
	private void Start(final PlayerController playerController)
	{
		this.jButtonStart = new JButton(new ImageIcon("graphics/string/start.png"));
		//TODO config
		this.jButtonStart.setBounds(790, 50,100, 48);
		//响应Enter键
		InputMap inputMap = jButtonStart.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = jButtonStart.getActionMap();
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"enter");
        actionMap.put("enter", new AbstractAction(){
        	private static final long serialVersionUID = 1L;
					public void actionPerformed(ActionEvent event) {
		                startPauseClick(playerController);
		            }
		});
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
	
	public JButton getjButtonStart()
	{
		return this.jButtonStart;
	}
	/**
	 * Set Layers
	 */
	private void setLayers()
	{
		try 
		{
			List<LayerConfig> layerConfigs = gameConfig.getLayerConfigs();
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
			layer.setGameDto(gameDto);
			layer.Paint(g);
		}
		//Key Event
		this.requestFocus();
	}
}