package pl.edu.agh.toik.ec.topology;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RingTopology extends AbstractTopology implements Topology {
    private Map<String, List<String>> topology;

    public RingTopology(List<String> actorsNames) {
        this.topology = getEmptyTopology(actorsNames);

        int topologySize = this.topology.size();
        for(int idx = 0; idx < topologySize; idx++){
            String agent = actorsNames.get(idx);
            String agentsNeightbour = actorsNames.get((idx + 1) % topologySize);
            List<String> agentList = topology.get(agent);
            List<String> agentNeithbourList = topology.get(agentsNeightbour);
            agentList.add(agentsNeightbour);
            agentNeithbourList.add(agent);
        }
        System.out.println("[INFO] Created RING topology");
    }

    void printTopology(){
        for (String agent : this.topology.keySet()) {
            System.out.println(agent + " -> " + Arrays.toString(this.topology.get(agent).toArray()));
        }
    }

    public List<String> getNeightbours(String name) {
        return this.topology.get(name);
    }
}
