package com.via.mapmanager.services.implementation;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;
import com.via.mapmanager.datatype.implememtation.DefaultWeightedEdgeExtends;
import com.via.mapmanager.datatype.interfaces.ILink;
import com.via.mapmanager.datatype.interfaces.INode;
import com.via.mapmanager.services.interfaces.IMapGraphService;
import org.apache.logging.log4j.util.Strings;
import org.jgrapht.GraphPath;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class MapGraphService implements IMapGraphService {

    @Autowired
    private  BrainService brain;

    private DefaultUndirectedWeightedGraph<String, DefaultWeightedEdgeExtends> graph;

    private static final Logger LOG = LoggerFactory.getLogger(MapGraphService.class);

    @Override
    public boolean populate(List<INode> nodes, List<ILink> links) {
        LOG.info("populate graph.");
        if(CollectionUtils.isEmpty(nodes) || CollectionUtils.isEmpty(links)) {
            LOG.error("missing vertex and edges. in order to create graph both vertex and edges must contain value.");
            throw new IllegalArgumentException("missing vertex and edges. in order to create graph both vertex and edges must contain value.");

       }

        graph = new DefaultUndirectedWeightedGraph<>(DefaultWeightedEdgeExtends.class);
        nodes.forEach(v -> graph.addVertex(v.getId()));
        links.forEach(l -> {
            graph.addEdge(l.getSource(), l.getTarget());
            graph.setEdgeWeight(l.getSource(), l.getTarget(), l.getAttribute().getWeight());
        });
        brain.populateGraph(graph);
        return true;
    }

    public DefaultUndirectedWeightedGraph<String, DefaultWeightedEdgeExtends> getGraph() {
        return graph;
    }

    public void setGraph(DefaultUndirectedWeightedGraph<String, DefaultWeightedEdgeExtends> graph) {
        this.graph = graph;
    }


    public  GraphPath<String, DefaultWeightedEdgeExtends> getShortestPath(String source, String target)
    {
        LOG.info("calculate shortest path.");
        if(brain.isEmpty()) {
            LOG.error("Graph is empty - in order to search shortest path must contain nodes and edges.");
            throw new IllegalArgumentException("Graph is empty - in order to search shortest path must contain nodes and edges.");
        }

        GraphPath<String, DefaultWeightedEdgeExtends> pathResponseBrain = brain.getShortestPath(source, target);

        return pathResponseBrain;
    }

    public boolean setWeight(String source, String target,double weight) {

        if (Strings.isEmpty(source) || Strings.isEmpty(target)) {
            throw new IllegalArgumentException("came across exception while setting weight on an edge. please check graph is not null and the source and target are valid.");

        }
        try {
            graph.setEdgeWeight(source, target, weight);
        }
        catch(Exception exception)
        {
            LOG.error("came across exception while setting weight on an edge. please check graph is not null and the source and target are valid.");
            throw new IllegalArgumentException("came across exception while setting weight on an edge. please check graph is not null and the source and target are valid.");

        }
        return true;
    }

    @Override
    public double getEdgeWeight(String source, String target)
    {
        return graph.getEdgeWeight(graph.getEdge(source,target));
    }

    public void printGraph(String filePath)  {
        JGraphXAdapter<String, DefaultWeightedEdgeExtends> graphAdapter =
                new JGraphXAdapter<>(graph);
        mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        BufferedImage image =
                mxCellRenderer.createBufferedImage(graphAdapter, null, 2, Color.WHITE, true, null);
        File imgFile = new File(filePath);
        try {
            ImageIO.write(image, "PNG", imgFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
