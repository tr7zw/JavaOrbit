package de.tr7zw.javaorbit.server.connection.packet.in;

import de.tr7zw.javaorbit.server.connection.packet.PacketIn;
import de.tr7zw.javaorbit.server.player.Player;
import lombok.Getter;

@Getter
public class PacketInLogin extends PacketIn{

	private Integer userId;
	private String sessionToken;
	private String version;
	
	public PacketInLogin(String data) {
		super(data);
		String[] ar = data.split("\\|");
		userId = Integer.parseInt(ar[1]);
		sessionToken = ar[2];
		version = ar[3];
	}

	@Override
	public void onRecieve(Player player) {
		//Gets handeld in PlayerConnection
	}

}
