package pl.edu.agh.toik.ec.namingservice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baran on 09.05.17.
 */
public class NamingServiceImpl implements NamingService {
    private final String WORKER_BASE = "worker:";
    private final String AGENT_BASE = ":agent:";
    private int workersCount;
    private int agentsPerWorkerCount;

    public NamingServiceImpl(int workersCount, int agentsPerWorkerCount) {
        this.workersCount = workersCount;
        this.agentsPerWorkerCount = agentsPerWorkerCount;
    }

    @Override
    public List<String> getWorkersIds() {
        List<String> workersIds = new ArrayList<>();
        for(int i = 0; i < workersCount; i++) {
            String workerName = getWorkerId(i);
            workersIds.add(workerName);
        }
        return workersIds;
    }

    @Override
    public List<String> getAgentsIds() {
        List<String> agentsIds = new ArrayList<>();
        for(int i = 0; i < workersCount; i++) {
            String workerName = getWorkerId(i);
            for(int j = 0; j < agentsPerWorkerCount; j++) {
                String agentName = getAgentId(workerName, j);
                agentsIds.add(agentName);
            }
        }
        return agentsIds;
    }

    @Override
    public String getWorkerId(int index) {
        return WORKER_BASE + Integer.toString(index);
    }

    @Override
    public String getAgentId(String workerName, int index) {
        return workerName + AGENT_BASE + Integer.toString(index);
    }
}
