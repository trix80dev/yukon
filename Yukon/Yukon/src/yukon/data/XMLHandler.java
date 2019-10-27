package yukon.data;

import java.net.Socket;

import yukon.Server;
import yukon.util.Crypto;

public class XMLHandler {

	public static void handleXMLPacket(Server server, String packet, Socket socket) throws Exception {
		
		if(packet.contains("policy-file-request")) PacketHandler.sendRawPacket("<cross-domain-policy><allow-access-from domain='*' to-ports='" + server.getServerSetting().getPort() + "'/></cross-domain-policy>", socket);
		else if(packet.contains("body action='verChk'")) PacketHandler.sendRawPacket(versionCheck(packet) ? "<msg t='sys'><body action='apiOK' r='0'></body></msg>" : "<msg t='sys'><body action='apiKO' r='0'></body></msg>", socket);
		else if(packet.contains("body action='rndK'")) PacketHandler.sendRawPacket("<msg t='sys'><body action='rndK' r='-1'><k>" + Crypto.generateRandomKey() + "</k></body></msg>", socket);
		else if(packet.contains("body action='login'")) XTHandler.sendLoginPacket(101, Crypto.generateRandomKey(), "", 50, packet.substring(packet.indexOf("<nick><![CDATA[") + 15, packet.indexOf("]]></nick>")), socket);
		else System.out.println("Failed to read XML Packet");
		
		
	}
	
	public static boolean versionCheck(String packet) {
		packet = packet.replaceAll("\\D+","");

		if(packet.contains("153"))
		return true;
		return false;
	}
	
}
