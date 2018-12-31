package de.tr7zw.javaorbit.server.connection.packet.play.out;

import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import de.tr7zw.javaorbit.server.enums.Title;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketPlayOutPermanentTitle extends PacketOut{

	private int ship;
	private Title title;
	
	@Override
	public StringBuilder buildData() {
		return new StringBuilder("0|n|pt|").append(ship).append("|").append(title.getId());
	}

}
