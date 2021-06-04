package de.tr7zw.javaorbit.server.components;

import com.badlogic.ashley.core.Component;

public class MoveableComponent implements Component {

    public long movingStartTime = 0;
    public int moveTime = 0;
    public boolean moving;
    public int x,y,startX, startY;
    
    public int distance() {
        return (int) Math.sqrt(Math.abs(Math.pow(x - startX, 2) + Math.pow(y - startY, 2)));
    }
    
}   
