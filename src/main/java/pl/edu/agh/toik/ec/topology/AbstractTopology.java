package pl.edu.agh.toik.ec.topology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class AbstractTopology {
    protected Map<String, List<String>> getEmptyTopology(List<String> agentList){
        Map<String, List<String>> topology = new HashMap<String, List<String>>();

        for (String agent : agentList) {
            topology.put(agent, new ArrayList<String>());
        }

        return topology;
    }
}
