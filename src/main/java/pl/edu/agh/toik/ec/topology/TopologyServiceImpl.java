package pl.edu.agh.toik.ec.topology;

import java.util.List;

/**
 * Created by baran on 09.05.17.
 */
public class TopologyServiceImpl implements TopologyService {

    private TopologyType topologyType;
    private int agentAmount;

    public TopologyServiceImpl(TopologyType topologyType, int agentAmount) {
        this.topologyType = topologyType;
        this.agentAmount = agentAmount;
    }

    @Override
    public Topology getTopology(List<String> actorId) {
        switch (topologyType){
            case NET:
                return new GridTopology(actorId);
            case RING:
                return new RingTopology(actorId);
            case STAR:
                return new StarTopology(actorId);
        }

        return null;
    }
}
