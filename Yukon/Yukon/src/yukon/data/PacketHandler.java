package yukon.data;

import java.net.Socket;
import java.nio.charset.StandardCharsets;

import yukon.Server;
import yukon.Yukon;

public class PacketHandler {
	
	private Server server;
	
	public PacketHandler(Server server) {
		this.server = server;
	}

	public void onClientConnect(Socket socket) throws Exception {
		
		System.out.println("Client Connected to server " + this.server.getServerName() + " - " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
		
		while(true) {
			
			byte[] data = new byte[65536];
			
			int read = socket.getInputStream().read(data);
			
			if(read > 0) handleInput(socket, data);
			
		}
		
	}
	
	public void handleInput(Socket socket, byte[] data) throws Exception {
		
		for(String packet : new String(data, StandardCharsets.UTF_8).split("\0")) {
			
			if(Yukon.console.isLoggerEnabled()) System.out.println("IN: " + packet);
			
			if(packet.startsWith("<")) XMLHandler.handleXMLPacket(server, packet, socket);
			else if(packet.startsWith("%")) XTHandler.handleXTPacket(packet, socket);
		}
		
	}
	
	public static void sendRawPacket(String packet, Socket socket) throws Exception {
		
		packet += '\u0000';
		
		socket.getOutputStream().write(packet.getBytes(StandardCharsets.UTF_8));
		socket.getOutputStream().flush();
		
		if(Yukon.console.isLoggerEnabled())
		System.out.println("OUT: " + packet);
		
	}
	
}
