package de.tr7zw.javaorbit.server.connection.packet.chat.in;

import de.tr7zw.javaorbit.server.chat.ChatUser;
import de.tr7zw.javaorbit.server.connection.packet.PacketChatIn;

public class PacketChatInInit extends PacketChatIn{

	private String userName;
	private int id;
	private String authToken;
	private int idc;
	private String lang;
	private String clanTag;
	private String version;

	public PacketChatInInit(String data) { // bu%-1%Debug@1@tokenstuff@563@es@Admin@2.3.3@0@-1@
		super(data);
		String[] ar = clearString(data).split("\\%");
		String[] content = ar[2].split("\\@");
		userName = content[0];
		id = Integer.parseInt(content[1]);
		authToken = content[2];
		idc = Integer.parseInt(content[3]);
		lang = content[4];
		clanTag = content[5];
		version = content[6];
	}

	@Override
	public void onRecieve(ChatUser user) {
		//TODO: Check authToken
		user.setId(id);
		user.setUserName(userName);
		user.setConnected(true);
		user.setClanTag(clanTag);
		user.setClientLang(lang);
	}

}
