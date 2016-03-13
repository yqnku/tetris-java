package cn.quicy.tetris.main;
import cn.quicy.tetris.controller.GameController;
import cn.quicy.tetris.controller.PlayerController;
import cn.quicy.tetris.dto.GameDto;
import cn.quicy.tetris.service.GameService;
import cn.quicy.tetris.ui.JFrameGame;
import cn.quicy.tetris.ui.JPanelGame;
public class Start 
{
	public static void main(String[] args)
	{
		//Create game data transfer object
		GameDto gameDto = new GameDto();
		//Create game panel
		JPanelGame jPanelGame = new JPanelGame(gameDto);
		//Create game service(Connect game date transfer object)
		GameService gameService = new GameService(gameDto);
		//Create game controller(Connect game panel and game service)
		GameController gameController = new GameController(jPanelGame, gameService);
		//Create player controller(Connect game controller)
		PlayerController playerController = new PlayerController(gameController);
		//Install player controller
		jPanelGame.getGameController(playerController);
		//Create game frame(Install game panel)
		new JFrameGame(jPanelGame);
		//TODO ！！！！要做的事情！！！！
		//暂停按钮--要搞定 --------还没有开始的时候不显示暂停图标，没有开始的时候不能动。
		//结束游戏时的提示  
		//DrawString比较慢怎么破
		//设置按钮，主题啥的   
		//硬编码问题，写配置文件   
		// 数据库
		//加个计时功能
	}
}