package de.tr7zw.javaorbit.server.components;

import com.badlogic.ashley.core.Component;

import de.tr7zw.javaorbit.server.enums.Gate;
import de.tr7zw.javaorbit.server.enums.Maps;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GateComponent implements Component {

    public final Gate gateGraphics;
    public final Maps targetMap;
    public final int x,y;
    
}
