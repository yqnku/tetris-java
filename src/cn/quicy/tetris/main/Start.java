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
	}
}