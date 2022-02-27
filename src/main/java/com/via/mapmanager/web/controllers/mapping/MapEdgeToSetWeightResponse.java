package com.via.mapmanager.web.controllers.mapping;

import com.via.mapmanager.datatype.implememtation.DefaultWeightedEdgeExtends;
import com.via.mapmanager.datatype.implememtation.Link;
import com.via.mapmanager.rest.datatype.SetWeightResponse;

public class MapEdgeToSetWeightResponse {

    public static SetWeightResponse map(DefaultWeightedEdgeExtends edge)
    {
        SetWeightResponse setWeightResponse = new SetWeightResponse();

        if(edge!=null)
        {
            setWeightResponse.setLink(new Link(edge.getSource(),edge.getTarget(), edge.getWeight()));
        }

        return setWeightResponse;
    }
}
