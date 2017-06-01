package pl.edu.agh.toik.ec.configuration;

import pl.edu.agh.toik.ec.communication.CommunicationService;
import pl.edu.agh.toik.ec.namingservice.NamingService;
import pl.edu.agh.toik.ec.topology.Topology;

/**
 * Created by M on 2017-05-30.
 */
public class Configuration {

    private Topology topology;
    private NamingService namingService;
    private CommunicationService communicationService;
    private AgentConfiguration agentConfiguration;

    public Configuration(Topology topology, NamingService namingService, CommunicationService communicationService,
                         AgentConfiguration agentConfiguration) {
        this.topology = topology;
        this.namingService = namingService;
        this.communicationService = communicationService;
        this.agentConfiguration = agentConfiguration;
    }
}
