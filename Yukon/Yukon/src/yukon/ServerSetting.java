package yukon;

public class ServerSetting {

	public enum ServerType {
		LOGIN,
		GAME,
		REDEMPTION
	}
	
	private ServerType serverType;
	private int port;
	private int maxPenguins;
	private boolean safeChatMode;
	
	public ServerSetting(ServerType serverType, int port, int maxPenguins, boolean safeChatMode) {
		this.serverType = serverType;
		this.port = port;
		this.maxPenguins = maxPenguins;
		this.safeChatMode = safeChatMode;
	}
	
	public ServerType getServerType() {
		return this.serverType;
	}
	
	public int getPort() {
		return this.port;
	}
	
	public int getMaxPenguins() {
		return this.maxPenguins;
	}
	
	public boolean isSafeChatMode() {
		return safeChatMode;
	}
	
}