package com.via.mapmanager.datatype.implememtation;

import com.via.mapmanager.datatype.interfaces.INode;
import org.springframework.stereotype.Component;

@Component
public class Node implements INode {

    private String id;


    public Node() {
    }

    public Node(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
