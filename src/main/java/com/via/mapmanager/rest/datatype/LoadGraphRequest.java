package com.via.mapmanager.rest.datatype;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.via.mapmanager.datatype.implememtation.Graph;
import com.via.mapmanager.datatype.implememtation.Link;
import com.via.mapmanager.datatype.implememtation.Node;
import com.via.mapmanager.datatype.interfaces.IGraph;
import com.via.mapmanager.datatype.interfaces.ILink;
import com.via.mapmanager.datatype.interfaces.INode;
import java.util.List;

public class LoadGraphRequest {

    private String directed;
    @JsonDeserialize(as = Graph.class)
    private IGraph graph;
    @JsonDeserialize(contentAs = Link.class)
    private List<ILink> links;
    private Boolean multigraph;
    @JsonDeserialize(contentAs = Node.class)
    private List<INode> nodes;

    public String getDirected() {
        return directed;
    }

    public void setDirected(String directed) {
        this.directed = directed;
    }

    public IGraph getGraph() {
        return graph;
    }

    public void setGraph(IGraph graph) {
        this.graph = graph;
    }

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

    public Boolean getMultigraph() {
        return multigraph;
    }

    public void setMultigraph(Boolean multigraph) {
        this.multigraph = multigraph;
    }
}
