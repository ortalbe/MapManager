package com.via.mapmanager.services.implementation;

import com.via.mapmanager.datatype.implememtation.DefaultWeightedEdgeExtends;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class BrainService {

    private DefaultUndirectedWeightedGraph<String, DefaultWeightedEdgeExtends> graph;
    private DijkstraShortestPath<String, DefaultWeightedEdgeExtends> dijkstraAlg;
    private static final Logger LOG = LoggerFactory.getLogger(BrainService.class);

    public void populateGraph (DefaultUndirectedWeightedGraph<String, DefaultWeightedEdgeExtends> graph)
    {
        LOG.info("populate cache memory.");
        this.graph =graph;
    }

    public GraphPath<String, DefaultWeightedEdgeExtends> getShortestPath(String source, String target) {
        LOG.info("calculate shortest path.");

        GraphPath<String, DefaultWeightedEdgeExtends> path = null;
        if (graph != null && !graph.edgeSet().isEmpty())
        {
            this.dijkstraAlg = new DijkstraShortestPath<String, DefaultWeightedEdgeExtends>(graph);
            path = this.dijkstraAlg.getPath(source, target);
        }
        return path;
    }


    public DefaultUndirectedWeightedGraph<String, DefaultWeightedEdgeExtends> getGraph() {
        return graph;
    }

    public void setGraph(DefaultUndirectedWeightedGraph<String, DefaultWeightedEdgeExtends> graph) {
        this.graph = graph;
    }

    public boolean isEmpty() {
        if(graph==null || graph.edgeSet().isEmpty() || graph.vertexSet().isEmpty())
            return true;

        return false;
    }
}
