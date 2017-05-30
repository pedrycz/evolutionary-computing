package pl.edu.agh.toik.ec.starter;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.toik.ec.communication.CommunicationService;
import pl.edu.agh.toik.ec.communication.Message;
import pl.edu.agh.toik.ec.namingservice.NamingService;
import pl.edu.agh.toik.ec.topology.Topology;
import pl.edu.agh.toik.ec.topology.TopologyService;
import pl.edu.agh.toik.ec.visualization.Visualization;
import pl.edu.agh.toik.ec.workers.SimpleWorker;
import pl.edu.agh.toik.ec.workers.StopCondition;
import pl.edu.agh.toik.ec.workers.Worker;

public class SimpleStarter implements Starter {

    private final Visualization visualisation;
    private final TopologyService topologyService;
    private final NamingService namingService;
    private CommunicationService communicationService;
    private final List<StopCondition> stopConditionList;

    public SimpleStarter(List<StopCondition> stopConditionList,
                         Visualization visualisation,
                         TopologyService topologyService,
                         NamingService namingService,
                         CommunicationService communicationService) {
        this.stopConditionList = stopConditionList;
        this.visualisation = visualisation;
        this.topologyService = topologyService;
        this.namingService = namingService;
        this.communicationService = communicationService;
    }

    @Override
    public void run() {
        communicationService.setStarter(this);

        Topology topology = topologyService.getTopology();

        List<Worker> workers = new ArrayList<>();
        for(int i = 0; i < stopConditionList.size(); i++) {
            StopCondition stopCondition = stopConditionList.get(i);
            workers.add(new SimpleWorker(namingService.getWorkerId(i), stopCondition, topology, communicationService));
        }
        for (Worker worker : workers) {
            worker.start();
            while (worker.isActive()) {
                worker.step();
            }
        }
    }

    @Override
    public void notify(Message message) {
        visualisation.notify(message);
    }

}
