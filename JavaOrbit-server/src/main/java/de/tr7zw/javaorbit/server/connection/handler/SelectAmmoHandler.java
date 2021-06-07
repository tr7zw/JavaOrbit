package de.tr7zw.javaorbit.server.connection.handler;

import de.tr7zw.javaorbit.server.connection.PacketHandler;
import de.tr7zw.javaorbit.server.connection.PlayerConnection;
import de.tr7zw.javaorbit.server.connection.gamepackets.in.SelectAmmoRequest;
import de.tr7zw.javaorbit.server.enums.Ammo;

public class SelectAmmoHandler implements PacketHandler<SelectAmmoRequest> {

    @Override
    public void process(PlayerConnection connection, SelectAmmoRequest packet) {
        connection.getPlayer().setAmmo(Ammo.fromId(packet.getAmmoType()-1));
    }

}
