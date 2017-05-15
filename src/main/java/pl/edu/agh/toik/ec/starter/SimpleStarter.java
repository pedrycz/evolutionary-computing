package pl.edu.agh.toik.ec.starter;

import pl.edu.agh.toik.ec.topology.Topology;
import pl.edu.agh.toik.ec.topology.TopologyService;
import pl.edu.agh.toik.ec.visualisation.Visualisation;
import pl.edu.agh.toik.ec.workers.SimpleWorker;
import pl.edu.agh.toik.ec.workers.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleStarter implements Starter {

    private final Visualisation visualisation;
    private final TopologyService topologyService;
    private final Map<String, Integer> workersDefinition;

    public SimpleStarter(Map<String, Integer> workersDefinition, Visualisation visualisation, TopologyService topologyService) {
        this.workersDefinition = workersDefinition;
        this.visualisation = visualisation;
        this.topologyService = topologyService;
    }

    @Override
    public void run() {
        Topology topology = topologyService.getTopology();

        List<Worker> workers = new ArrayList<>();
        for (Map.Entry<String, Integer> definition : workersDefinition.entrySet()) {
            workers.add(new SimpleWorker(definition.getKey(), definition.getValue(), topology));
        }

        for (Worker worker : workers) {
            worker.start();
            while (worker.isActive()) {
                worker.step();
            }
        }
    }

    @Override
    public void notify(String message) {
        visualisation.notify(message);
    }

}
