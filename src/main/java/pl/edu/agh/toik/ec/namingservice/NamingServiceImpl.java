package pl.edu.agh.toik.ec.namingservice;

import java.util.Date;

/**
 * Created by baran on 09.05.17.
 */
public class NamingServiceImpl implements NamingService {

    private int workerAmount;
    private int agentPerWorkerAmount;

    public NamingServiceImpl(int workerAmount, int agentPerWorkerAmount) {
        this.workerAmount = workerAmount;
        this.agentPerWorkerAmount = agentPerWorkerAmount;
    }

    @Override
    public String nextAgentId() {
        return null;
    }

    @Override
    public String nextWorkerId() {
        return "worker " + new Date();
    }
}
