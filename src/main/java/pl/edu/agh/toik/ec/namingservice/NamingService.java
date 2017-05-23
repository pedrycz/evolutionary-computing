package pl.edu.agh.toik.ec.namingservice;

import java.util.List;

/**
 * Created by baran on 09.05.17.
 */
public interface NamingService {
    String getWorkerId(int index);
    String getAgentId(String workerName, int index);
    List<String> getAgentsIds();
    List<String> getWorkersIds();
}
