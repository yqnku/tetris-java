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
	private Layer layers[] = null;
	/**
	 * Start and pause button
	 */
	private JButton jButtonStart = null;
	/**
	 * Configure button
	 */
	private JButton jButtonConfig = null;
	/**
	 * Constructor
	 * @param gameDto
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
		this.layers = new Layer[]
				{
					new LayerBG(0, 0, 1100, 700),
					new LayerData(32,32,300,275),
					new LayerDisk(32,347,300,275),
					new LayerGame(370, 32, 333, 590),
					new LayerButton(750, 32, 310, 85),		
					new LayerNext(750, 140, 185, 120),
					new LayerLevel(935, 140 , 125, 120),
					new LayerScore(750, 280, 310, 155),
					new LayerAbout(750, 467, 310, 155)
				};
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