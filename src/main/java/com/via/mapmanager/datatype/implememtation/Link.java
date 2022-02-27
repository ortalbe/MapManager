package com.via.mapmanager.datatype.implememtation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.via.mapmanager.datatype.interfaces.IAttribute;
import com.via.mapmanager.datatype.interfaces.ILink;
import org.springframework.stereotype.Component;

@Component
public class Link implements ILink {

    private String target;
    private String source;
    @JsonDeserialize(as = Attribute.class)
    private IAttribute attributes;

    public Link() {
    }

    public Link(String target, String source, IAttribute attributes) {
        this.target = target;
        this.source = source;
        this.attributes = attributes;
    }


    public Link(String target, String source, double weight) {
        this.target = target;
        this.source = source;
        this.attributes = new Attribute();
        attributes.setWeigt(weight);
    }

    @Override
    public IAttribute getAttribute() {
        return attributes;
    }

    @Override
    public void setAttribute(IAttribute attribute) {
        this.attributes=attribute;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public void setSource(String source) {
        this.source=source;
    }

    @Override
    public String getTarget() {
        return target;
    }

    @Override
    public void setTarget(String target) {
        this.target = target;
    }

    protected double getWeight() {
        return this.getAttribute().getWeight();
    }

   }
