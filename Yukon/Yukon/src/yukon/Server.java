package yukon;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import yukon.data.PacketHandler;

public class Server {

	private String serverName;
	private ServerSetting serverSetting;
	private ServerSocket serverSocket;
	private ExecutorService threads;
	private PacketHandler inputHandler;
	private Thread thread;
	public static ArrayList<Server> servers = new ArrayList<Server>();
	
	public Server(String serverName, ServerSetting serverSetting) throws Exception {
		this.serverName = serverName;
		this.serverSetting = serverSetting;
		servers.add(this);
		startServer();
	}
	
	public void startServer() throws Exception {
		
		this.serverSocket = new ServerSocket(serverSetting.getPort());
		
		this.threads = Executors.newCachedThreadPool();
		
		this.inputHandler = new PacketHandler(this);
		
		this.thread = new Thread() {
			
			public void run() {
				
				while(true) {
					
					try {
						
						Socket socket = serverSocket.accept();
						
						threads.submit(new Runnable() {
							
							@Override
							public void run() {
								
								try {
									inputHandler.onClientConnect(socket);
								} catch (Exception e) {
									e.printStackTrace();
								}
								
							}
							
						});
						
					} catch (IOException e) {
						
						e.printStackTrace();
						
					}
					
				}
				
			}
			
		};
		thread.start();
		
		System.out.println("New " + this.getServerSetting().getServerType() + 
				" server called " + this.getServerName() + " opened on port " + 
				this.getServerSetting().getPort());
	}
	
	
	public static void closeServer(String name) {
		
		for(Server server : servers) {
			
			if(server.getServerName().equalsIgnoreCase(name)) {
				
				server.thread.interrupt();
				
				System.out.println("Stopped server " + name);
				
				servers.remove(server);
				
			}
			
		}
		
	}
	
	public ServerSetting getServerSetting() {
		return this.serverSetting;
	}
	
	public String getServerName() {
		return this.serverName;
	}
}
