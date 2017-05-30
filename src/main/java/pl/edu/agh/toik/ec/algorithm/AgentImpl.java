package pl.edu.agh.toik.ec.algorithm;

import pl.edu.agh.toik.ec.communication.Message;

import java.util.ArrayList;

public class AgentImpl implements Agent {


    //temporary stub for neighbours - maybe just an array of names?
    private ArrayList<String> neighbours;
    private String name;

    public AgentImpl() {
        this.neighbours = new ArrayList();
    }

    public  ArrayList getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ArrayList neighbours) {
        this.neighbours = neighbours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void makeStep() {
        System.out.println("doing some steps...");
    }


    public void receiveMessage(Message message) {

    }

    public void sendMessage(Message msg) {

    }


    @Override
    public void sendToStarter(Message message) {

    }
}
