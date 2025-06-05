package com.marcoMario.model.DTO;

import java.util.ArrayList;
import java.util.List;

public class ObjectPage {
    private List<Object> objectList = new ArrayList<Object>();
    private long sizeList = 0;


    public List<Object> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<Object> objectList) {
        this.objectList = objectList;
    }

    public long getSizeList() {
        return sizeList;
    }

    public void setSizeList(long sizeList) {
        this.sizeList = sizeList;
    }

    public ObjectPage(List<Object> objectList, long sizeList) {
        this.objectList = objectList;
        this.sizeList = sizeList;
    }

    public ObjectPage() {

    }

    public void setObjectListGameDTO(List<GameDTO> listGameDTO){
        this.objectList = new ArrayList<Object>(listGameDTO);
    }
}
