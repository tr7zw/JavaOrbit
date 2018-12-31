package de.tr7zw.javaorbit.server.connection.packet.play.out;

import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketPlayOutShowMessage extends PacketOut{

	private String message;
	
	@Override
	public StringBuilder buildData() {
		return new StringBuilder("0|A|STD|").append(message);
	}

}
