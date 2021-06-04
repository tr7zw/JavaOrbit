package de.tr7zw.javaorbit.server.components;

import java.util.function.Consumer;

import com.badlogic.ashley.core.Component;

public class GateUserComponent implements Component {

    public GateUserComponent(Consumer<GateComponent> warp) {
        this.warp = warp;
    }
    
    public IdComponent gateId = null;
    public GateComponent gateInfo = null;
    public GateComponent usingGate = null;
    public long gateStartTime = 0;
    public final Consumer<GateComponent> warp;
    
}
