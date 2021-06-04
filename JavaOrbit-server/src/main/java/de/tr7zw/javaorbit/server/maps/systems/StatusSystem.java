package de.tr7zw.javaorbit.server.maps.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;

import de.tr7zw.javaorbit.server.components.BaseUserComponent;
import de.tr7zw.javaorbit.server.components.DemilitarizedComponent;
import de.tr7zw.javaorbit.server.components.GateUserComponent;
import de.tr7zw.javaorbit.server.components.PacketConsumerComponent;
import de.tr7zw.javaorbit.server.components.PositionComponent;
import de.tr7zw.javaorbit.server.components.RadiationComponent;
import de.tr7zw.javaorbit.server.components.RepairComponent;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutStatus;

public class StatusSystem extends IntervalIteratingSystem {

    private static ComponentMapper<DemilitarizedComponent> demilitarized = ComponentMapper.getFor(DemilitarizedComponent.class);
    private static ComponentMapper<BaseUserComponent> base = ComponentMapper.getFor(BaseUserComponent.class);
    private static ComponentMapper<RadiationComponent> radiation = ComponentMapper.getFor(RadiationComponent.class);
    private static ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);
    private static ComponentMapper<GateUserComponent> gateUser = ComponentMapper.getFor(GateUserComponent.class);
    private static ComponentMapper<RepairComponent> repair = ComponentMapper.getFor(RepairComponent.class);
    private static ComponentMapper<PacketConsumerComponent> packetUser = ComponentMapper
            .getFor(PacketConsumerComponent.class);

    public StatusSystem() {
        super(Family.all(GateUserComponent.class, PacketConsumerComponent.class, PositionComponent.class,
                DemilitarizedComponent.class, BaseUserComponent.class, RadiationComponent.class, RepairComponent.class).get(), 100);
    }

    @Override
    protected void processEntity(Entity entity) {
        PacketConsumerComponent packetHandler = packetUser.get(entity);
        GateUserComponent gateStatus = gateUser.get(entity);
        PositionComponent pos = position.get(entity);
        packetHandler.packetHandler.accept(new PacketPlayOutStatus(pos.x, pos.y, demilitarized.get(entity).inDemilitarizedZone,
                repair.get(entity).isRepairing, base.get(entity).inStation != null, radiation.get(entity).inRadiationZone, gateStatus.gateInfo != null));
    }

}
