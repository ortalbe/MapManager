package com.via.mapmanager.services.interfaces;

import com.via.mapmanager.datatype.implememtation.DefaultWeightedEdgeExtends;
import com.via.mapmanager.datatype.interfaces.ILink;
import com.via.mapmanager.datatype.interfaces.INode;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;
import java.util.List;

public interface IMapGraphService {

    DefaultUndirectedWeightedGraph<String, DefaultWeightedEdgeExtends> getGraph();
    void setGraph(DefaultUndirectedWeightedGraph<String, DefaultWeightedEdgeExtends> graph);
    boolean setWeight(String source, String target,double weight);
    double getEdgeWeight(String source, String target);
    void printGraph(String filePath) ;
    boolean populate(List<INode> nodes, List<ILink> links);
    GraphPath<String, DefaultWeightedEdgeExtends> getShortestPath(String source,String target);


}
