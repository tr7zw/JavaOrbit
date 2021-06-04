package de.tr7zw.javaorbit.server.npc;

import com.badlogic.ashley.core.Component;

import de.tr7zw.javaorbit.server.enums.ShipType;

public class ShipComponent implements Component {

    public ShipType type = ShipType.PHOENIX;
    public int hp = 10000;
    public int maxHp = 10000;
    public int shield = 10000;
    public int maxShield = 10000;
    public int speed = 400;
    public float shieldStrength = 0.8f;

    public boolean isDead() {
        return hp <= 0;
    }

}
