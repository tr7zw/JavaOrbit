package de.tr7zw.javaorbit.server.npc;

import com.badlogic.ashley.core.Component;

import de.tr7zw.javaorbit.server.enums.Station;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StationComponent implements Component{

    public Station station;
    
}
