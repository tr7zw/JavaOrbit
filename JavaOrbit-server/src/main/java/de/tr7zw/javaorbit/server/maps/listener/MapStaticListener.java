package de.tr7zw.javaorbit.server.maps.listener;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.utils.ImmutableArray;

import de.tr7zw.javaorbit.server.components.GateComponent;
import de.tr7zw.javaorbit.server.components.IdComponent;
import de.tr7zw.javaorbit.server.components.PositionComponent;
import de.tr7zw.javaorbit.server.components.StationComponent;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutSpawnGate;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutSpawnStation;
import de.tr7zw.javaorbit.server.maps.MapInstance;
import de.tr7zw.javaorbit.server.player.Player;

public class MapStaticListener implements Listener<Player>{

    private ImmutableArray<Entity> stations;
    private ImmutableArray<Entity> gates;

    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<StationComponent> sc = ComponentMapper.getFor(StationComponent.class);
    private ComponentMapper<IdComponent> idc = ComponentMapper.getFor(IdComponent.class);
    private ComponentMapper<GateComponent> gatec = ComponentMapper.getFor(GateComponent.class);
    
    public MapStaticListener(MapInstance instance) {
        stations = instance.getEntityEngine().getEntitiesFor(Family.all(StationComponent.class, PositionComponent.class).get());
        gates = instance.getEntityEngine().getEntitiesFor(Family.all(GateComponent.class, PositionComponent.class).get());
    }
    
    @Override
    public void receive(Signal<Player> signal, Player player) {
        for(Entity station : stations) {
            StationComponent type = sc.get(station);
            PositionComponent pos = pm.get(station);
            player.sendPacket(new PacketPlayOutSpawnStation(type.station.name(), type.station, pos.x, pos.y));
        }
        for(Entity gate : gates) {
            PositionComponent pos = pm.get(gate);
            IdComponent id = idc.get(gate);
            GateComponent gateData = gatec.get(gate);
            player.sendPacket(new PacketPlayOutSpawnGate(id.id, gateData.gateGraphics, pos.x, pos.y));
        }
    }

}
