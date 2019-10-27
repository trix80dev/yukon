package yukon.util;

public class PacketUtils {

	public static String[] parseXTPacket(String packet) {
		
		packet.substring(1);
		String[] packets = packet.split("%");
		
		return packets;
	}
	
	public static String buildXTPacket(Object... args) {
		String packet = "%xt%";
		for(Object obj : args) {
			packet += obj + "%";
		}
		return packet;
	}
	
}
