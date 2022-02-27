package com.via.mapmanager.rest.datatype;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.via.mapmanager.datatype.implememtation.Graph;
import com.via.mapmanager.datatype.implememtation.Link;
import com.via.mapmanager.datatype.implememtation.Node;
import com.via.mapmanager.datatype.interfaces.IGraph;
import com.via.mapmanager.datatype.interfaces.ILink;
import com.via.mapmanager.datatype.interfaces.INode;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class LoadGraphResponse {

    private List<ILink> links;
    private List<INode> nodes;

    public List<ILink> getLinks() {
        return links;
    }

    public void setLinks(List<ILink> links) {
        this.links = links;
    }

    public List<INode> getNodes() {
        return nodes;
    }

    public void setNodes(List<INode> nodes) {
        this.nodes = nodes;
    }

}
