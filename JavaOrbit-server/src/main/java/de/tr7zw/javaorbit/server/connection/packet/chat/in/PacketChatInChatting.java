package de.tr7zw.javaorbit.server.connection.packet.chat.in;

import de.tr7zw.javaorbit.server.Server;
import de.tr7zw.javaorbit.server.chat.ChatUser;
import de.tr7zw.javaorbit.server.connection.packet.PacketChatIn;
import lombok.Getter;

@Getter
public class PacketChatInChatting extends PacketChatIn{

	private int channel;
	private String text;
	
	public PacketChatInChatting(String data) {
		super(data);
		String[] ar = clearString(data).split("@")[0].split("\\%");
		channel = Integer.parseInt(ar[1]);
		text = ar[2];
	}

	@Override
	public void onRecieve(ChatUser user) {
		Server.getInstance().getChatManager().sendMessage(user, channel, text);
	}

}
