package pl.edu.agh.toik.ec.topology;

import java.util.List;

/**
 * Created by baran on 09.05.17.
 */
public interface TopologyService {
    Topology getTopology(List<String> actorId);
}
