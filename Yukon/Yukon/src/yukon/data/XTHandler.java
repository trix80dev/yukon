package yukon.data;

import java.net.Socket;

import yukon.util.PacketUtils;

public class XTHandler {
	
	public static int penguinId;
	public static String penguinUsername;
	public static void handleXTPacket(String packet, Socket socket) throws Exception{
		
		if(packet.contains("j#js")) sendJoinServerPacket(socket);
		if(packet.contains("i#gi")) sendGetInventoryPacket(socket);
		if(packet.contains("b#gb")) sendGetBuddyPacket(socket);
		if(packet.contains("n#gn")) sendGetIgnorePacket(socket);
		if(packet.contains("l#mst")) sendMailStartTack(socket);
		if(packet.contains("l#mg")) sendMailGetPacket(socket);
		if(packet.contains("u#gp")) sendGetPlayerPacket(socket);
		
	}

	public static void sendLoginPacket(int penguinID, String loginKey, String friends, int population, String penguinName, Socket socket) throws Exception {
		penguinUsername = penguinName;
		PacketHandler.sendRawPacket(PacketUtils.buildXTPacket("l", penguinID, loginKey, friends, population), socket);
		penguinId = penguinID;
		
	}
	
	public static void sendJoinServerPacket(Socket socket) throws Exception {
		
		PacketHandler.sendRawPacket(PacketUtils.buildXTPacket("js", -1, 101, 0, 0, 0), socket);
		sendGetPlayerStampsPacket(socket);
		sendGetLastRevisionPacket(socket);
		sendLoadPenguinPacket(socket);
		sendJoinRoomPacket(1, socket);
	}
	
	public static void sendGetPlayerStampsPacket(Socket socket) throws Exception {
		
		PacketHandler.sendRawPacket(PacketUtils.buildXTPacket("gps", -1, 101), socket);
		
	}
	
	public static void sendGetLastRevisionPacket(Socket socket) throws Exception {
		
		PacketHandler.sendRawPacket(PacketUtils.buildXTPacket("glr", -1, 3555), socket);
		
	}
	
	public static void sendLoadPenguinPacket(Socket socket) throws Exception {
		
		PacketHandler.sendRawPacket(PacketUtils.buildXTPacket("lp", penguinUsername, penguinId, "", 1440, (System.currentTimeMillis()/1000L), 7, 0, 7), socket);
		
	}
	
	public static void sendJoinRoomPacket(int roomID, Socket socket) throws Exception{
		
		PacketHandler.sendRawPacket(PacketUtils.buildXTPacket("jx", -1, 100), socket);
		PacketHandler.sendRawPacket(PacketUtils.buildXTPacket("jr", -1, 100, penguinId + "|" +penguinUsername + "|45|35|407|0|0|0|756|0|8818|8812|0|0|1|1|921"), socket); //the 100| thing is the player string, it carries things like what the penguin is wearing.
		PacketHandler.sendRawPacket(PacketUtils.buildXTPacket("ap", -1, penguinId + "|" +penguinUsername + "|45|35|407|0|0|0|756|0|8818|8812|0|0|1|1|921"), socket);
		
	}
	
	public static void sendGetInventoryPacket(Socket socket) throws Exception{
		PacketHandler.sendRawPacket(PacketUtils.buildXTPacket("gi", -1, "", "413"), socket);
	}
	
	public static void sendGetBuddyPacket(Socket socket) throws Exception{
		PacketHandler.sendRawPacket(PacketUtils.buildXTPacket("gb", 55, ""), socket); //"" is empty b/c no friends :(
	}
	public static void sendGetIgnorePacket(Socket socket) throws Exception{
		PacketHandler.sendRawPacket(PacketUtils.buildXTPacket("gn", 55), socket); //55 is internal id, idk what that means but i think it's like a socket id?
	}
	public static void sendMailStartTack(Socket socket) throws Exception{ //tack actually means engine oof
		int unreadCount = 0;
		int postcardCount = 0; //keeping these 4 laters
		PacketHandler.sendRawPacket(PacketUtils.buildXTPacket("mst", -1, unreadCount, postcardCount), socket);
	}
	public static void sendMailGetPacket(Socket socket) throws Exception{ 
		PacketHandler.sendRawPacket(PacketUtils.buildXTPacket("mg", -1), socket); //there should be stuff after -1 but idk how to do itso
	}
	public static void sendGetPlayerPacket(Socket socket) throws Exception{  //this should be set to an actual penguinID later!!
		PacketHandler.sendRawPacket(PacketUtils.buildXTPacket("gp", 55, penguinId + "|" +penguinUsername + "|45|35|407|0|0|0|756|0|8818|8812|0|0|1|1|921"), socket); //there should be stuff after -1 but idk how to do itso
	}
	
}
