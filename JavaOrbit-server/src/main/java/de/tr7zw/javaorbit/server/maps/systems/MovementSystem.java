package de.tr7zw.javaorbit.server.maps.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import de.tr7zw.javaorbit.server.components.MoveableComponent;
import de.tr7zw.javaorbit.server.components.PositionComponent;
import de.tr7zw.javaorbit.server.player.Player;

public class MovementSystem extends IteratingSystem {

    private static ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);
    private static ComponentMapper<MoveableComponent> moveable = ComponentMapper.getFor(MoveableComponent.class);

    public MovementSystem() {
        super(Family.all(MoveableComponent.class, PositionComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        MoveableComponent target = moveable.get(entity);
        if (target.moving) {
            PositionComponent pos = position.get(entity);
            if (System.currentTimeMillis() - target.movingStartTime > target.moveTime) {
                pos.x = target.x;
                pos.y = target.y;
                target.moving = false;
            } else {
                int offsetX = target.x - target.startX;
                int offsetY = target.y - target.startY;
                long timeSinceStart = System.currentTimeMillis() - target.movingStartTime;
                double progress = (double) timeSinceStart / (double) target.moveTime;
                offsetX *= progress;
                offsetY *= progress;
                pos.x = target.startX + offsetX;
                pos.y = target.startY + offsetY;
            }
        }
    }

    public void changeMoveTarget(Entity entity, int targetPosX, int targetPosY, int posX, int posY, int speed) {
        if (moveable.has(entity) && position.has(entity)) {
            MoveableComponent move = moveable.get(entity);
            PositionComponent pos = position.get(entity);
            pos.x = posX;
            pos.y = posY;
            move.x = targetPosX;
            move.y = targetPosY;
            move.moving = true;
            move.startX = pos.x;
            move.startY = pos.y;
            int distance = move.distance();
            int time = distance / speed * 1080; // At least while testing 1080 was fitting the clients speed
            if (time == 0)
                time = 1000;
            move.moveTime = time;
            move.movingStartTime = System.currentTimeMillis();
        }
    }

}
