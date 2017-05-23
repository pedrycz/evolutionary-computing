package pl.edu.agh.toik.ec.namingservice;

import java.util.HashMap;

/**
 * Created by baran on 09.05.17.
 */
public class NamingServiceImpl implements NamingService {
    private HashMap<String, Integer> agents = new HashMap<>();
    private int currWorker = 0;
    private final String WORKER_BASE = "worker:";
    private final String AGENT_BASE = "agent:";

    @Override
    public String nextWorkerId() {
        String worker_name = WORKER_BASE + Integer.toString(currWorker++);
        agents.put(worker_name, 0);
        return worker_name;
    }

    @Override
    public String nextAgentId(String workerName) {
        int curr_number = agents.get(workerName);
        agents.put(workerName, curr_number + 1);
        return AGENT_BASE + Integer.toString(curr_number);
    }
}
