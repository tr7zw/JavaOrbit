package de.tr7zw.javaorbit.server.connection.packet.play.out;

import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PacketPlayOutStatus extends PacketOut{

	private int x;
    private int y;
    private boolean demilitarized;
    private boolean repairAnimation;
    private boolean inBase;
    private boolean inRadiationZone;
    private boolean atJumpgate;
	
	@Override //0|D|x|y|indmz(bool)|bool|intz(bool)|inrz(bool)|inja(bool)|repairvouchers
	public StringBuilder buildData() {
		return new StringBuilder("0|D|")
                .append(x).append("|")
                .append(y).append("|")
                .append(toEnum(demilitarized)).append("|")
                .append(toEnum(repairAnimation)).append("|")
                .append(toEnum(inBase)).append("|")
                .append(toEnum(inRadiationZone)).append("|")//radiationZone
				.append(toEnum(atJumpgate)).append("|");
	}

}
