package de.tr7zw.javaorbit.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Version {
RETRO,
REVOLUTION2D,
MODERN8_3_2
;
    
    public static Version getVersion(String version){
        if(version == null)return RETRO;
        if(version.equals("8.3.2"))return MODERN8_3_2;
        return REVOLUTION2D;
    }

}
