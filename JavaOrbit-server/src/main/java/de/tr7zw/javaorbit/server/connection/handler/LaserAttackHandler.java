package de.tr7zw.javaorbit.server.connection.handler;

import de.tr7zw.javaorbit.server.connection.PacketHandler;
import de.tr7zw.javaorbit.server.connection.PlayerConnection;
import de.tr7zw.javaorbit.server.connection.gamepackets.in.LaserAttackRequest;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutLaserStop;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutShootLaser;
import de.tr7zw.javaorbit.server.player.Player;

public class LaserAttackHandler implements PacketHandler<LaserAttackRequest> {

    @Override
    public void process(PlayerConnection connection, LaserAttackRequest packet) {
        Player player = connection.getPlayer();
        if(packet.getTargetId() == player.getPlayerView().getSelectedId() && player.getPlayerView().getSelected() != null) {
            if(player.getPlayerView().getSelected() instanceof Player && ((Player)player.getPlayerView().getSelected()).inDemilitarizedZone()){
                player.getConnection().send("0|P");
                player.sendPacket(new PacketPlayOutLaserStop(player.getId(), player.getPlayerView().getSelected().getId()));
            }else{
                player.getPositionComponent().instance.sendContextPacket(player, new PacketPlayOutShootLaser(player.getId(), packet.getTargetId(), player.getAmmo(), true, true));
                player.getPlayerView().setAttacking(true);
            }
        }
    }

}
