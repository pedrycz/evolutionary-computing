package pl.edu.agh.toik.ec.configuration;

import pl.edu.agh.toik.ec.communication.CommunicationService;
import pl.edu.agh.toik.ec.migration.EmigrateStrategy;
import pl.edu.agh.toik.ec.namingservice.NamingService;
import pl.edu.agh.toik.ec.topology.Topology;

/**
 * Created by M on 2017-05-30.
 */
public class Configuration {

    private final Topology topology;
    private final NamingService namingService;
    private final CommunicationService communicationService;
    private final AgentConfiguration agentConfiguration;
    private final EmigrateStrategy emigrateStrategy;

    public Configuration(NamingService namingService, CommunicationService communicationService, AgentConfiguration agentConfiguration) {
        this(null, null, namingService, communicationService, agentConfiguration);
    }

    public Configuration(Topology topology, NamingService namingService, CommunicationService communicationService, AgentConfiguration agentConfiguration) {
        this.namingService = namingService;
        this.communicationService = communicationService;
        this.agentConfiguration = agentConfiguration;
        this.topology = topology;
        this.emigrateStrategy = null;
    }

    public Configuration(EmigrateStrategy emigrateStrategy, Topology topology, NamingService namingService, CommunicationService communicationService, AgentConfiguration agentConfiguration) {
        this.namingService = namingService;
        this.communicationService = communicationService;
        this.agentConfiguration = agentConfiguration;
        this.topology = topology;
        this.emigrateStrategy = emigrateStrategy;
    }

    public Configuration withTopology(Topology topology) {
        return new Configuration(topology, namingService, communicationService, agentConfiguration);
    }

    public Configuration withEmigrateStrategy(EmigrateStrategy emigrateStrategy) {
        return new Configuration(emigrateStrategy, topology, namingService, communicationService, agentConfiguration);
    }

    public Topology getTopology() {
        return topology;
    }

    public EmigrateStrategy getEmigrateStrategy() {
        return emigrateStrategy;
    }

    public NamingService getNamingService() {
        return namingService;
    }

    public CommunicationService getCommunicationService() {
        return communicationService;
    }

    public AgentConfiguration getAgentConfiguration() {
        return agentConfiguration;
    }
}
