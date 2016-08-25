package com.stalker.next.maleorfemale;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maslparu on 24.08.2016.
 */
public class childList  {
    List<child> list = new ArrayList<>();

    public void add(child newChild) {

        child childLast = null;
        if (list.size() != 0) {
            childLast = list.get(list.size() - 1);
        }

        if ((childLast != null) && (childLast.getEndDate().compareTo(newChild.getEndDate()) == 0)) {
            return;
        }

        list.add(newChild);
    }

    public List<child> getList() {
        return list;
    }
}
