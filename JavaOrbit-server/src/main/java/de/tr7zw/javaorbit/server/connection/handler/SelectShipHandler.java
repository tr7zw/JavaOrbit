package de.tr7zw.javaorbit.server.connection.handler;

import de.tr7zw.javaorbit.server.connection.PacketHandler;
import de.tr7zw.javaorbit.server.connection.PlayerConnection;
import de.tr7zw.javaorbit.server.connection.gamepackets.in.SelectShipRequest;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutShipData;
import de.tr7zw.javaorbit.server.maps.MapInstance;
import de.tr7zw.javaorbit.server.maps.entities.EntityLiving;
import de.tr7zw.javaorbit.server.player.Player;

public class SelectShipHandler implements PacketHandler<SelectShipRequest>{

    @Override
    public void process(PlayerConnection connection, SelectShipRequest packet) {
        Player player = connection.getPlayer();
        MapInstance instance = player.getPositionComponent().instance;
        EntityLiving target = instance.getEntity(packet.getId());
        if(target == null)return;
        if(target == player)return;
        //TODO: Check range
        
        player.getPlayerView().setSelectedId(target.getId());
        player.getPlayerView().setSelected(target);
        player.sendPacket(new PacketPlayOutShipData(target.getId(), target.getName(),  target.getShip().getHp(), target.getShip().getMaxHp(), target.getShip().getShield(), target.getShip().getMaxShield()));
        player.getPlayerView().setAttacking(false);
    }

}
