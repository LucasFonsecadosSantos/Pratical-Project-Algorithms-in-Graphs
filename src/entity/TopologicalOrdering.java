package entity;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TopologicalOrdering {

    public static String initTopologicalOrdering(List<TimeTuple> finishTime, GraphCharacteristicsEnum graphCycle) {
        if (graphCycle == GraphCharacteristicsEnum.HAS_NO_CYCLE) {
            finishTime = ordering(finishTime);
            StringBuilder builder = new StringBuilder();
            for (TimeTuple tuple : finishTime) {
                builder.append(tuple.getVertex() + "->");
            }
            builder.append("#");
            return builder.toString();
        } else {
            return GraphCharacteristicsEnum.HAS_CYCLE.toString();
        }
    }

    public static List<TimeTuple> ordering(List<TimeTuple> finishTime) {
        int limit = finishTime.size();
        boolean changed = true;

        do {
            changed = false;
            for (int i=0 ; i < limit-1 ; ++i) {
                if (finishTime.get(i).getTime() < finishTime.get(i+1).getTime()) {
                    Collections.swap(finishTime, i, i+1);
                    changed = true;
                }
            }
        } while (changed);
        return finishTime;
    }
}