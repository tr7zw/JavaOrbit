package de.tr7zw.javaorbit.server.maps.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;

import de.tr7zw.javaorbit.server.components.GateComponent;
import de.tr7zw.javaorbit.server.components.GateUserComponent;
import de.tr7zw.javaorbit.server.components.IdComponent;
import de.tr7zw.javaorbit.server.components.PositionComponent;

public class GateSystem extends IntervalIteratingSystem {

    private static ComponentMapper<IdComponent> id = ComponentMapper.getFor(IdComponent.class);
    private static ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);
    private static ComponentMapper<GateUserComponent> gateUser = ComponentMapper.getFor(GateUserComponent.class);
    private static ComponentMapper<GateComponent> gateInfo = ComponentMapper.getFor(GateComponent.class);
    private ImmutableArray<Entity> gates;
    
    public GateSystem() {
        super(Family.all(GateUserComponent.class, PositionComponent.class).get(), 100);
    }
    
    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        gates = engine.getEntitiesFor(Family.all(GateComponent.class, PositionComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity) {
        PositionComponent pos = position.get(entity);
        GateUserComponent userGate = gateUser.get(entity);
        if(userGate.usingGate != null){
            if(System.currentTimeMillis() - userGate.gateStartTime > 2000){
                userGate.warp.accept(userGate.usingGate);
                userGate.usingGate = null;
                userGate.gateInfo = null;
                userGate.gateId = null;
                return;
            }
        }
        for(Entity gate : gates){
            PositionComponent gatePos = position.get(gate);
            if(gatePos.inDistance(pos, 300)) {
                userGate.gateInfo = gateInfo.get(gate);
                userGate.gateId = id.get(gate);
                return;
            }
        }
        userGate.gateInfo = null;
        userGate.gateId = null;
    }

}
