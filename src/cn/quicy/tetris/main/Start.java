package cn.quicy.tetris.main;
import cn.quicy.tetris.controller.GameController;
import cn.quicy.tetris.controller.PlayerController;
import cn.quicy.tetris.dto.GameDto;
import cn.quicy.tetris.service.GameService;
import cn.quicy.tetris.ui.JFrameGame;
import cn.quicy.tetris.ui.JPanelGame;
//Builder Pattern
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
		jPanelGame.setPlayerController(playerController);
		//Create game frame(Install game panel)
		new JFrameGame(jPanelGame);
		
		//TODO ！！！！要做的事情！！！！
		//把现在的思路理一理
		//在做-----硬编码问题，写配置文件
		//在做-----设置按钮，主题啥的
		//本地记录
		//在做-----数据库
		//加个计时功能
		//美观，背景音乐
		//注释还是要好好补的
		//游戏结束后保存记录
		//网络？
	}
}