package de.tr7zw.javaorbit.server.connection.packet;

public abstract class PacketOut extends Packet{

	public abstract StringBuilder buildData();

	public static String toEnum(boolean isTrue)
	{
		return (isTrue) ? "1" : "0";
	}
	
}
