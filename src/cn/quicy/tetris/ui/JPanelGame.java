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
	private GameDto gameDto = null;
	private Layer layers[] = null;
	private JButton jButtonStart = null;
	private JButton jButtonConfig = null;
	public JPanelGame(GameDto gameDto)
	{
		this.gameDto = gameDto;
		this.setLayout(null);
		InitComponent();
	}
	public void getGameController(PlayerController playerControl)
	{
		this.addKeyListener(playerControl);
		this.Start(playerControl);
	}
	private void Start(final PlayerController playerControl)
	{
		this.jButtonStart = new JButton(new ImageIcon("graphics/string/start.png"));
		this.jButtonStart.setBounds(780, 50, 80, 48);
		this.jButtonStart.setMnemonic(KeyEvent.VK_ENTER);
		this.jButtonStart.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				playerControl.Start();
			}
		});
		this.add(jButtonStart);
	}
	private void InitComponent()
	{
		this.jButtonConfig = new JButton(new ImageIcon("graphics/string/config.png"));
		this.jButtonConfig.setBounds(890, 50, 80, 48);
		this.jButtonConfig.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				// TODO Auto-generated method stub
				System.out.println("config");
			}
		});
		this.add(jButtonConfig);
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		layers = new Layer[]
		{
			new LayerBG(0, 0, 1050, 700),
			new LayerData(32,32,300,275),
			new LayerDisk(32,347,300,275),
			new LayerGame(370, 32, 333, 590),
			new LayerButton(750, 32, 260, 85),		
			new LayerNext(750, 140, 160, 120),
			new LayerLevel(910, 140 , 100, 120),
			new LayerGrades(750, 280, 260, 155),
			new LayerAbout(750, 467, 260, 155)
		};
		for(Layer layer : layers)
		{
			layer.setGameDto(gameDto);
			layer.Paint(g);
		}
		this.requestFocus();
	}
}
