package de.tr7zw.javaorbit.server.maps.listener;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.utils.ImmutableArray;

import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutSpawnStation;
import de.tr7zw.javaorbit.server.maps.MapInstance;
import de.tr7zw.javaorbit.server.npc.PositionComponent;
import de.tr7zw.javaorbit.server.npc.StationComponent;
import de.tr7zw.javaorbit.server.player.Player;

public class MapStaticListener implements Listener<Player>{

    private ImmutableArray<Entity> stations;

    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<StationComponent> sc = ComponentMapper.getFor(StationComponent.class);
    
    public MapStaticListener(MapInstance instance) {
        stations = instance.getEntityEngine().getEntitiesFor(Family.all(StationComponent.class, PositionComponent.class).get());
    }
    
    @Override
    public void receive(Signal<Player> signal, Player player) {
        for(Entity station : stations) {
            StationComponent type = sc.get(station);
            PositionComponent pos = pm.get(station);
            player.sendPacket(new PacketPlayOutSpawnStation(type.station.name(), type.station, pos.x, pos.y));
        }
    }

}
