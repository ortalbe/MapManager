package com.via.mapmanager.datatype.implememtation;

import com.via.mapmanager.datatype.interfaces.IGraph;
import org.springframework.stereotype.Component;

@Component
public class Graph implements IGraph {

    private String name;
    private String version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}