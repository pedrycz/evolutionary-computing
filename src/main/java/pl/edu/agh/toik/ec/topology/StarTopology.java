package pl.edu.agh.toik.ec.topology;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StarTopology extends AbstractTopology implements Topology {

    private Map<String, List<String>> topology;

    public StarTopology(List<String> actorsNames){
        this.topology = getEmptyTopology(actorsNames);

        String root = actorsNames.get(0);
        List<String> neigthbours = topology.get(root);

        for(int idx = 1; idx < actorsNames.size(); idx++) {
            String neightbourName = actorsNames.get(idx);
            neigthbours.add(neightbourName);
            topology.get(neightbourName).add(root);
        }
        System.out.println("[INFO] Created STAR topology");
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
