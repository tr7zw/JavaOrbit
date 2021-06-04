package de.tr7zw.javaorbit.server.maps.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import de.tr7zw.javaorbit.server.components.MoveableComponent;
import de.tr7zw.javaorbit.server.components.PositionComponent;

public class MovingSystem extends IteratingSystem{

    private static ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);
    private static ComponentMapper<MoveableComponent> moveable = ComponentMapper.getFor(MoveableComponent.class);
    
    public MovingSystem() {
        super(Family.all(MoveableComponent.class, PositionComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        MoveableComponent target = moveable.get(entity);
        if(target.moving) {
            PositionComponent pos = position.get(entity);
            if(System.currentTimeMillis() - target.movingStartTime > target.moveTime) {
                pos.x = target.x;
                pos.y = target.y;
                target.moving = false;
            }else {
                int offsetX = target.x - target.startX;
                int offsetY = target.y - target.startY;
                long timeSinceStart = System.currentTimeMillis() - target.movingStartTime;
                double progress = (double)timeSinceStart / (double)target.moveTime;
                offsetX *= progress;
                offsetY *= progress;
                pos.x = target.startX + offsetX;
                pos.y = target.startY + offsetY;
            }
        }
    }
    
}
