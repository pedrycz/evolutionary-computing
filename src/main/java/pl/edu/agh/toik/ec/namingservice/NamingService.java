package pl.edu.agh.toik.ec.namingservice;

/**
 * Created by baran on 09.05.17.
 */
public interface NamingService {

    public String nextAgentId(String workerName);
    public String nextWorkerId();
}
