package yukon;

import yukon.ServerSetting.ServerType;
import yukon.util.Console;

public class Yukon {

	public static Console console;
	
	public static void main(String args[]) throws Exception {
		
		System.out.println("Yukon started");
		
		console = new Console(true);
		
		new Server("Login", new ServerSetting(ServerType.LOGIN, 6112, 300, false));
		new Server("Game", new ServerSetting(ServerType.GAME, 6113, 300, false));
		
		
	}
	
}
