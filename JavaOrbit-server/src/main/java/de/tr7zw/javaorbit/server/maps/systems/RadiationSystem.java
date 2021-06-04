package de.tr7zw.javaorbit.server.maps.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;

import de.tr7zw.javaorbit.server.components.PositionComponent;
import de.tr7zw.javaorbit.server.components.RadiationComponent;
import de.tr7zw.javaorbit.server.maps.MapInstance;

public class RadiationSystem extends IntervalIteratingSystem {

    private static ComponentMapper<RadiationComponent> radiation = ComponentMapper.getFor(RadiationComponent.class);
    private static ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);
    
    public RadiationSystem() {
        super(Family.all(RadiationComponent.class, PositionComponent.class).get(), 200);
    }

    @Override
    protected void processEntity(Entity entity) {
        RadiationComponent rad = radiation.get(entity);
        PositionComponent pos = position.get(entity);
        MapInstance instance = pos.instance;
        rad.inRadiationZone = (pos.x < 0 || pos.y < 0 || pos.x > instance.getMapWidth() || pos.y > instance.getMapHeight());
    }

}
