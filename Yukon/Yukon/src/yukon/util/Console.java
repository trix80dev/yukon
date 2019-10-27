package yukon.util;

import java.util.Scanner;

import yukon.Server;
import yukon.ServerSetting;
import yukon.ServerSetting.ServerType;

public class Console {

	private Thread thread;
	private boolean loggerEnabled;
	
	public Console(boolean loggerEnabled) {
		System.out.println("Console enabled");
		this.loggerEnabled = loggerEnabled;
		startConsole();
	}
	
	public void startConsole() {
		
		this.thread = new Thread() {
			
			public void run() {
				
				Scanner scanner = new Scanner(System.in);
				String input;
				
				while((input = scanner.nextLine()) != null) {
					
					String[] args = input.split(" ");
					
					switch(args[0]) {
					
					case "stop": 
						System.out.println("Stopping Server");
						scanner.close();
						System.exit(0);
						break;
						
					case "logger":
						
						if(args.length != 2) break;
						
						if(args[1].equalsIgnoreCase("start")) {
							System.out.println("Logger enabled");
							setLoggerEnabled(true);
						}
						else if(args[1].equalsIgnoreCase("stop")) {
							System.out.println("Logger disabled");
							setLoggerEnabled(false);
						}
						break;
						
					case "startserver":
						if(args.length != 5) return;
						
						String name = args[1];
						for(Server server : Server.servers) {
							
							if(server.getServerName().equalsIgnoreCase(name)) {
								
								System.out.println("Server Name already in use");
								return;
								
							}
						}
						
						ServerType serverType;
						if(args[2].equalsIgnoreCase("game")) serverType = ServerType.GAME;
						else if(args[2].equalsIgnoreCase("login")) serverType = ServerType.LOGIN;
						else if(args[2].equalsIgnoreCase("redemption")) serverType = ServerType.REDEMPTION;
						else {
							System.out.println("Invalid Server Type. Valid server types are Game, Login, and Redemption.");
							return;
						}
						
						int port = Integer.parseInt(args[3]);
						
						boolean safeChatMode = false;
						if(args[4].equalsIgnoreCase("true")) safeChatMode = true;
						else if(args[4].equalsIgnoreCase("false")) safeChatMode = false;
						else {
							System.out.println("True for safeChatMode, False for no safeChatMode");
							return;
						}
						
						try {
							new Server(name, new ServerSetting(serverType, port, 300, safeChatMode));
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
						
					case "stopserver":
						if(args.length != 2) break;
						
						Server.closeServer(args[1]);
						break;
						
					case "getservers":
						for(Server server : Server.servers) System.out.println(server.getServerName());
						break;
						
						
					default:
						System.out.println("unknown command");
					
					}
					
				}
				
			}
			
		};
		
		this.thread.start();
		
	}
	
	public boolean isLoggerEnabled() {
		return loggerEnabled;
	}
	
	public void setLoggerEnabled(boolean loggerEnabled) {
		this.loggerEnabled = loggerEnabled;
	}
	
}
