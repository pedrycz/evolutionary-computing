package pl.edu.agh.toik.ec.topology;

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
    public Topology getTopology() {
        return null;
    }
}
