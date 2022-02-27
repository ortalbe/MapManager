package com.via.mapmanager.datatype.interfaces;

public interface ILink {

    IAttribute getAttribute();
    void setAttribute (IAttribute attribute);
    String getSource();
    void setSource (String source);
    String getTarget ();
    void setTarget(String target);
}


