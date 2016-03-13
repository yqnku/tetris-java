package cn.quicy.tetris.ui;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
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
		this.gameDto = gameDto;
		//So that JButton. setBounds can work
		this.setLayout(null);
		//Initialize components
		//TODO 按钮的美观
		InitComponent();
	}
	/**
	 * Set configure button
	 */
	private void InitComponent()
	{
		this.jButtonConfig = new JButton(new ImageIcon("graphics/string/options.png"));
		this.jButtonConfig.setBounds(920, 50, 110, 48);
		this.jButtonConfig.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				// TODO options
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
	 * @param playerControl
	 */
	//TODO 暂停按钮
	private void Start(final PlayerController playerControl)
	{
		this.jButtonStart = new JButton(new ImageIcon("graphics/string/start.png"));
		this.jButtonStart.setBounds(790, 50,100, 48);
		//TODO key event does not work
		this.jButtonStart.setMnemonic(KeyEvent.VK_ENTER);
		this.jButtonStart.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//TODO 开始暂停---这块也很麻烦呢---理不清的头绪
				if (gameDto.isPause())
				{
					jButtonStart.setIcon(new ImageIcon("graphics/string/start.png"));
					//gameDto.ChangeStatus();
				}
				else 
				{
					jButtonStart.setIcon(new ImageIcon("graphics/string/pause_button.png"));
					playerControl.Start();
					//gameDto.ChangeStatus();
				}
			}
		});
		this.add(jButtonStart);
	}
	/**
	 * Set Layers
	 */
	private void setLayers()
	{
		layers = new Layer[]
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