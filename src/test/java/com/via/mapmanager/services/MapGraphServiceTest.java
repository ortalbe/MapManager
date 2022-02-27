package com.via.mapmanager.services;

import com.via.mapmanager.datatype.implememtation.DefaultWeightedEdgeExtends;
import com.via.mapmanager.datatype.implememtation.Link;
import com.via.mapmanager.datatype.implememtation.Node;
import com.via.mapmanager.datatype.interfaces.ILink;
import com.via.mapmanager.datatype.interfaces.INode;
import com.via.mapmanager.services.interfaces.IMapGraphService;
import org.jgrapht.GraphPath;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootTest
public class MapGraphServiceTest {

    @Autowired
    private IMapGraphService mapGraphService;

    private static final String PRINT_GRAPH_BASE_PATH = "src/test/resources/";

    public void setMock (List<INode> nodes,List<ILink> links)
    {
        nodes.add(new Node("A"));
        nodes.add(new Node("B"));
        nodes.add(new Node("C"));
        nodes.add(new Node("D"));
        nodes.add(new Node("E"));
        nodes.add(new Node("F"));

        links.add(new Link("A","B",2));
        links.add(new Link("A","C",0.25));
        links.add(new Link("A","D",1));
        links.add(new Link("B","E",4));
        links.add(new Link("B","F",0.5));
        links.add(new Link("B","C",1));
        links.add(new Link("C","E",3));
        links.add(new Link("D","F",10));

        mapGraphService.populate(nodes,links);
    }

    @Test
    public void populateGraphNullEntriesTest ()
    {
        List<INode> nodes=null;
        List<ILink> links = null;
        Assertions.assertThrows(IllegalArgumentException.class,() -> mapGraphService.populate(nodes,links));
    }

    @Test
    public void populateGraphTest ()
    {
        List<INode> nodes =new ArrayList<>();
        List<ILink> links = new ArrayList<>() ;

        setMock(nodes,links);
        List<String> sourceExpected = links.stream().map(c -> c.getSource()).collect(Collectors.toList());
        List<String> targetExpected = links.stream().map(c -> c.getTarget()).collect(Collectors.toList());
        List<Double> weightExpected = links.stream().map(c -> c.getAttribute().getWeight()).collect(Collectors.toList());
        List<String> nodesExpected = nodes.stream().map(c -> c.getId()).collect(Collectors.toList());

        List<String> sourceActual = mapGraphService.getGraph().edgeSet().stream().map(c -> c.getSource()).collect(Collectors.toList());
        List<String> targetActual =  mapGraphService.getGraph().edgeSet().stream().map(c -> c.getTarget()).collect(Collectors.toList());
        List<Double> weightActual = mapGraphService.getGraph().edgeSet().stream().map(c -> c.getWeight()).collect(Collectors.toList());
        List<String> nodesActual = mapGraphService.getGraph().vertexSet().stream().collect(Collectors.toList());


        Assert.assertArrayEquals(sourceExpected.stream().sorted().toArray(),sourceActual.stream().sorted().toArray());
        Assert.assertArrayEquals(targetExpected.stream().sorted().toArray(),targetActual.stream().sorted().toArray());
        Assert.assertArrayEquals(weightExpected.stream().sorted().toArray(),weightActual.stream().sorted().toArray());
        Assert.assertArrayEquals(nodesExpected.stream().sorted().toArray(),nodesActual.stream().sorted().toArray());

    }


    @Test
    public void printGraphTest ()
    {
        List<INode> nodes =new ArrayList<>();
        List<ILink> links = new ArrayList<>() ;

        setMock(nodes,links);
        mapGraphService.printGraph(PRINT_GRAPH_BASE_PATH  + "populateGraphhTest");

        File file = new File(PRINT_GRAPH_BASE_PATH  + "populateGraphhTest");

        Assert.assertTrue(file.exists());

    }

    @Test
    public void setWeightNullTest ()
    {

        Assertions.assertThrows(IllegalArgumentException.class,() ->mapGraphService.setWeight(null,null,0));

    }

    @Test
    public void setWeightSourceNotExistTest ()
    {
        List<INode> nodes =new ArrayList<>();
        List<ILink> links = new ArrayList<>() ;

        setMock(nodes,links);
        Assertions.assertThrows(IllegalArgumentException.class,() ->mapGraphService.setWeight(null,null,0));
    }

    @Test
    public void setWeightSourceTest ()
    {
        List<INode> nodes =new ArrayList<>();
        List<ILink> links = new ArrayList<>() ;

        setMock(nodes,links);
        Assert.assertTrue(mapGraphService.setWeight("A","B",0));
        Assert.assertTrue(mapGraphService.getEdgeWeight("A","B")==0);
        Assert.assertTrue(mapGraphService.setWeight("A","B",43.12232));
        Assert.assertTrue(mapGraphService.getEdgeWeight("A","B")==43.12232);
    }

    @Test
    public void getShortestPathTest ()
    {
        List<INode> nodes =new ArrayList<>();
        List<ILink> links = new ArrayList<>() ;

        setMock(nodes,links);
        GraphPath<String, DefaultWeightedEdgeExtends> graphPath =  mapGraphService.getShortestPath("A","B");

        List<String> sourceActual = graphPath.getEdgeList().stream().map(c -> c.getSource()).collect(Collectors.toList());
        List<String> targetActual =  graphPath.getEdgeList().stream().map(c -> c.getTarget()).collect(Collectors.toList());
        List<Double> weightActual = graphPath.getEdgeList().stream().map(c -> c.getWeight()).collect(Collectors.toList());
        List<String> nodesActual = graphPath.getVertexList().stream().collect(Collectors.toList());

    }

    @Test
    public void getShortestPathTestErrorNode ()
    {
        List<INode> nodes =new ArrayList<>();
        List<ILink> links = new ArrayList<>() ;

        setMock(nodes,links);
        Assertions.assertThrows(IllegalArgumentException.class,() -> mapGraphService.getShortestPath("a","B"));

    }

    @Test
    public void getShortestPathTestErrorEmptyGraph ()
    {
        List<INode> nodes =new ArrayList<>();
        List<ILink> links = new ArrayList<>() ;

        Assertions.assertThrows(IllegalArgumentException.class,() -> mapGraphService.getShortestPath("A","B"));

    }

    @Test
    public void getShortestPathTestNode ()
    {
        List<INode> nodes =new ArrayList<>();
        List<ILink> links = new ArrayList<>() ;

        setMock(nodes,links);
        GraphPath<String, DefaultWeightedEdgeExtends> result = mapGraphService.getShortestPath("A","B");
        Assert.assertFalse(result.getEndVertex().isEmpty());
        Assert.assertFalse(result.getEdgeList().isEmpty());
    }
}
