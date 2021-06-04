package de.tr7zw.javaorbit.server.components;

import java.util.concurrent.atomic.AtomicInteger;

import com.badlogic.ashley.core.Component;

public class IdComponent implements Component {

    private static AtomicInteger COUNTER = new AtomicInteger(-1);
    
    public final int id;
    
    public IdComponent() {
        id = COUNTER.decrementAndGet();
    }
    
    public IdComponent(int id) {
        this.id = id;
    }
    
}
