package com.via.mapmanager.rest.datatype;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.via.mapmanager.datatype.implememtation.Link;
import com.via.mapmanager.datatype.interfaces.ILink;

public class SetWeightResponse {

    @JsonDeserialize(as = Link.class)
    private ILink link;

    public ILink getLink() {
        return link;
    }

    public void setLink(ILink link) {
        this.link = link;
    }
}
