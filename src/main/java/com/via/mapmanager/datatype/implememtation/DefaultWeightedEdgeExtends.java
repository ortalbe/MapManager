package com.via.mapmanager.datatype.implememtation;

import org.jgrapht.graph.DefaultWeightedEdge;

public class DefaultWeightedEdgeExtends extends DefaultWeightedEdge {

    @Override
    public String getSource() {
        return super.getSource().toString();
    }

    @Override
    public String getTarget() {
        return super.getTarget().toString();
    }

    @Override
    public double getWeight() {
        return super.getWeight();
    }

    @Override
    public String toString() {
        return Double.toString(getWeight());
    }
}
