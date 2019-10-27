package yukon.data;

import java.net.Socket;

import yukon.util.PacketUtils;

public class XTHandler {
	
	public static void handleXTPacket(String packet, Socket socket) throws Exception{
		
		if(packet.contains("j#js")) sendJoinServerPacket(socket);
		
	}

	public static void sendLoginPacket(int penguinID, String loginKey, String friends, int population, Socket socket) throws Exception {
		
		PacketHandler.sendRawPacket(PacketUtils.buildXTPacket("l", penguinID, loginKey, friends, population), socket);
		
	}
	
	public static void sendJoinServerPacket(Socket socket) throws Exception {
		
		PacketHandler.sendRawPacket(PacketUtils.buildXTPacket("js", -1, 101, 0, 0, 0), socket);
		sendGetPlayerStampsPacket(socket);
		sendGetLastRevisionPacket(socket);
		sendLoadPenguinPacket(socket);
	}
	
	public static void sendGetPlayerStampsPacket(Socket socket) throws Exception {
		
		PacketHandler.sendRawPacket(PacketUtils.buildXTPacket("gps", -1, 101), socket);
		
	}
	
	public static void sendGetLastRevisionPacket(Socket socket) throws Exception {
		
		PacketHandler.sendRawPacket(PacketUtils.buildXTPacket("glr", -1, 3555), socket);
		
	}
	
	public static void sendLoadPenguinPacket(Socket socket) throws Exception {
		
		PacketHandler.sendRawPacket(PacketUtils.buildXTPacket("lp", "testing", 100, "", 1440, (System.currentTimeMillis()/1000L), 7, 0, 7), socket);
		
	}
	
	public static void sendJoinRoomPacket(int roomID, Socket socket) throws Exception{
		
		PacketHandler.sendRawPacket(PacketUtils.buildXTPacket("jx", -1, 100), socket);
		PacketHandler.sendRawPacket(PacketUtils.buildXTPacket("jr", -1, 100, "testing" + "town"), socket);
		PacketHandler.sendRawPacket(PacketUtils.buildXTPacket("ap", -1, "testing"), socket);
		
	}
	
}
