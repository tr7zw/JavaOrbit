package de.tr7zw.javaorbit.server.connection.packet.play.in;

import de.tr7zw.javaorbit.server.Location;
import de.tr7zw.javaorbit.server.connection.packet.PacketPlayIn;
import de.tr7zw.javaorbit.server.player.Player;

public class PacketPlayInMove extends PacketPlayIn{

	int targetPosX;
	int targetPosY;
	int posX;
	int posY;

	public PacketPlayInMove(String data) {
		super(data);
		//1|1984|2115|1978|1947|485
		//1|982|1105|1000|1000  2009 Client
		String[] ar = data.split("\\|");
		targetPosX = Integer.parseInt(ar[1]);
		targetPosY = Integer.parseInt(ar[2]);
		posX = Integer.parseInt(ar[3]);
		posY = Integer.parseInt(ar[4]);
	}

	@Override
	public void onRecieve(Player player) {
		player.getLocation().setX(posX);
		player.getLocation().setY(posY);
		player.setTargetLocation(new Location(player.getLocation().getInstance(), targetPosX, targetPosY));
		player.setMoving(true);
		player.setStartLocation(player.getLocation().clone());
		int distance = player.getLocation().distance(player.getTargetLocation());
		int time = distance / player.getShip().getSpeed() * 1080; //At least while testing 1080 was fitting the clients speed
		if(time == 0)time = 1000;
		player.setMoveTime(time);
		player.setMovingStartTime(System.currentTimeMillis());
	}

}
