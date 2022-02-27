package com.via.mapmanager.datatype.implememtation;

import com.via.mapmanager.datatype.interfaces.IAttribute;
import org.springframework.stereotype.Component;

@Component
public class Attribute implements IAttribute {

    private double weight;

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeigt(double weight) {
        this.weight = weight;
    }

}
