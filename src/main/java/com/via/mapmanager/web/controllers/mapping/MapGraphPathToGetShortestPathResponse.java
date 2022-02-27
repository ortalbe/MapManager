package com.via.mapmanager.web.controllers.mapping;

import com.via.mapmanager.datatype.implememtation.DefaultWeightedEdgeExtends;
import com.via.mapmanager.datatype.implememtation.Link;
import com.via.mapmanager.datatype.implememtation.Node;
import com.via.mapmanager.datatype.interfaces.ILink;
import com.via.mapmanager.rest.datatype.GetShortestPathResponse;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MapGraphPathToGetShortestPathResponse {

    public static GetShortestPathResponse map(GraphPath<String, DefaultWeightedEdgeExtends> path)
    {
        GetShortestPathResponse getShortestPathResponse = new GetShortestPathResponse();

        if(path!=null)
        {
            List<ILink> links = new ArrayList<>();
            getShortestPathResponse.setNodes(path.getVertexList().stream().map(Node::new).collect(Collectors.toList()));

            for(DefaultWeightedEdgeExtends currentEdge : path.getEdgeList())
            {
                links.add(new Link(currentEdge.getSource(),currentEdge.getTarget(), currentEdge.getWeight()));
            }

            getShortestPathResponse.setLinks(links);
        }

        return getShortestPathResponse;
    }
}
