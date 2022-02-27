package com.via.mapmanager.rest.datatype;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.via.mapmanager.datatype.implememtation.Link;
import com.via.mapmanager.datatype.implememtation.Node;
import com.via.mapmanager.datatype.interfaces.ILink;
import com.via.mapmanager.datatype.interfaces.INode;

import java.util.List;

public class GetShortestPathResponse {

    @JsonDeserialize(contentAs = Link.class)
    private List<ILink> links;

    @JsonDeserialize(contentAs = Node.class)
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
