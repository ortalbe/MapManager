package com.via.mapmanager.web.controllers.rest;

import com.via.mapmanager.datatype.implememtation.DefaultWeightedEdgeExtends;
import com.via.mapmanager.rest.datatype.*;
import com.via.mapmanager.services.interfaces.IMapGraphService;
import com.via.mapmanager.web.controllers.mapping.MapEdgeToSetWeightResponse;
import com.via.mapmanager.web.controllers.mapping.MapGraphPathToGetShortestPathResponse;
import org.jgrapht.GraphPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MapManager {

    @Autowired
    private IMapGraphService mapGraphService;

    @PostMapping("loadgraph")
    public ResponseEntity<LoadGraphResponse> loadGraph (@RequestBody LoadGraphRequest graphRequest)
    {
        mapGraphService.populate(graphRequest.getNodes(),graphRequest.getLinks());
        LoadGraphResponse loadGraphResponse = new LoadGraphResponse();
        loadGraphResponse.setLinks(graphRequest.getLinks());
        loadGraphResponse.setNodes(graphRequest.getNodes());
        return new ResponseEntity<LoadGraphResponse>(loadGraphResponse, HttpStatus.OK);
    }
    @GetMapping("getshortestpath")
    public  ResponseEntity<GetShortestPathResponse> getShortestPath (@RequestParam String source, @RequestParam String target)
    {
        GraphPath<String, DefaultWeightedEdgeExtends> path =  mapGraphService.getShortestPath(source,target);
        MapGraphPathToGetShortestPathResponse.map(path);
        return new ResponseEntity<GetShortestPathResponse>(MapGraphPathToGetShortestPathResponse.map(path), HttpStatus.OK);

    }

    @PutMapping("setweight")
    public ResponseEntity<SetWeightResponse> setWeight (@RequestBody SetWeightRequest request)
    {
        String source = request.getLink().getSource();

        String target = request.getLink().getTarget();
        boolean result = mapGraphService.setWeight(source, target,request.getLink().getAttribute().getWeight());
        if(result) {
            DefaultWeightedEdgeExtends edge = mapGraphService.getGraph().getEdge(source, target);
            return new ResponseEntity<SetWeightResponse>(MapEdgeToSetWeightResponse.map(edge), HttpStatus.OK);
        }
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
