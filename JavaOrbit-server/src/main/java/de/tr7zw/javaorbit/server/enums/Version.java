package de.tr7zw.javaorbit.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Version {
RETRO,
REVOLUTION2D,

;
    
    public static Version getVersion(String version){
        if(version == null)return RETRO;
        return REVOLUTION2D;
    }

}
