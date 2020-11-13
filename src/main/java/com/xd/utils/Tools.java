package com.xd.utils;

import java.util.ArrayList;
import java.util.List;

public class Tools {

    public static List<Long> receiveCollectionList(List<Long> first, List<Long> second) {
        List<Long> result = new ArrayList<>();

        for (Long one : first)
            for (Long other : second)
                if (one == other) {
                    result.add(one);
                    break;
                }

        return result;
    }

    public static List<Long> receiveDefectList(List<Long> universalSet, List<Long> subSet) {
        List<Long> result = new ArrayList<>();

        for (Long one : universalSet) {
            boolean flag = true;
            for (Long other : subSet) {
                if (other == one) {
                    flag = false;
                    break;
                }
            }

            if (flag)
                result.add(one);
        }

        return result;
    }
}
