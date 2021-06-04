package de.tr7zw.javaorbit.server.npc;

import com.badlogic.ashley.core.Component;

import de.tr7zw.javaorbit.server.maps.MapInstance;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PositionComponent implements Component {

    public MapInstance instance;
    public int x;
    public int y;

    public boolean inDistance(PositionComponent location, int distance) {
        return Math.abs(Math.pow(x - location.x, 2) + Math.pow(y - location.y, 2)) <= Math.pow(distance, 2);
    }

    public int distance(PositionComponent location) {
        return (int) Math.sqrt(Math.abs(Math.pow(x - location.x, 2) + Math.pow(y - location.y, 2)));
    }

    @Override
    public PositionComponent clone() {
        return new PositionComponent(instance, x, y);
    }
    
}
