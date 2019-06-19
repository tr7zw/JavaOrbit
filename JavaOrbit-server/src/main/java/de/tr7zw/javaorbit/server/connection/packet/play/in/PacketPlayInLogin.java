package de.tr7zw.javaorbit.server.connection.packet.play.in;

import de.tr7zw.javaorbit.server.connection.packet.PacketPlayIn;
import de.tr7zw.javaorbit.server.player.Player;
import lombok.Getter;

@Getter
public class PacketPlayInLogin extends PacketPlayIn{

	private Integer userId;
	private String sessionToken;
	private String version;
	
	public PacketPlayInLogin(String data) {
		super(data);
		String[] ar = data.split("\\|");
		userId = Integer.parseInt(ar[1]);
		sessionToken = ar[2];
		if(ar.length >= 4)
			version = ar[3];
	}

	@Override
	public void onRecieve(Player player) {
		//Gets handeld in PlayerConnection
	}

}
