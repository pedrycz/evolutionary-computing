package pl.edu.agh.toik.ec.topology;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;

public class GridTopology extends AbstractTopology implements Topology {
    private final Map<String, List<String>> topology;
    private final int topologySize;
    private int sizeA;
    private int sizeB;

    public GridTopology(List<String> actorsNames) {
        this.topology = getEmptyTopology(actorsNames);

        this.topologySize = actorsNames.size();
        setTopologySize(topologySize);
        String[][] gridForTopology = createGridForTopology(actorsNames);
        mapGridIntoTopology(gridForTopology);
        System.out.println("[INFO] Created GRID topology");
    }

    private void mapGridIntoTopology(String[][] gridForTopology) {
        for(int i=0; i<sizeA; i++)
            for(int j=0; j<sizeB; j++){
                String name = gridForTopology[i][j];
                if(!name.equals("")){
                    List<String> neightbours = topology.get(name);
                    neightbours.add(findIndexForVertical(gridForTopology, i, j, 1));
                    neightbours.add(findIndexForHorizontal(gridForTopology, i, j, 1));
                    neightbours.add(findIndexForVertical(gridForTopology, i, j, -1));
                    neightbours.add(findIndexForHorizontal(gridForTopology, i, j, -1));
                }
            }
    }

    private String findIndexForVertical(String[][] grid, int i, int j, int direction) {
        String next = "";
        int offset = direction;
        do {
            next = grid[(((i+offset)%sizeA)+sizeA)%sizeA][j];
            offset = offset + direction;
        } while(next.equals(""));

        return next;
    }

    private String findIndexForHorizontal(String[][] grid, int i, int j, int direction) {
        String next = "";
        int offset = direction;
        do {
            next = grid[i][(((j+offset)%sizeB)+sizeB)%sizeB];
            offset = offset + direction;
        } while(next.equals(""));

        return next;
    }



    private void setTopologySize(int topologySize) {
        if(isRegularTopology(topologySize)){
            sizeA = getTopologySize(topologySize, true);
            sizeB = topologySize/sizeA;

        } else if(isRegularTopology(topologySize + 1)){
            sizeA = getTopologySize(topologySize + 1, true);
            sizeB = (topologySize + 1)/sizeA;
        } else if(isRegularTopology(topologySize + 2)){
            sizeA = getTopologySize(topologySize + 2, true);
            sizeB = (topologySize + 2)/sizeA;
        } else {
            if(topologySize > 8){
                sizeA = getTopologySize(topologySize + 3, false);
                sizeB = (topologySize + 3)/sizeA;
            } else {
                sizeA = getTopologySize(topologySize, false);
                sizeB = (int)(ceil((topologySize + 0.0)/sizeA));
            }
        }
    }

    void printTopology(){
        for (String agent : topology.keySet()) {
            System.out.println(agent + " -> " + Arrays.toString(this.topology.get(agent).toArray()));
        }
    }

    private String[][] createGridForTopology(List<String> actorsNames) {
        String[][] grid = new String[sizeA][sizeB];

        for(int i=0; i<sizeA; i++)
            for(int j=0; j<sizeB; j++)
                grid[i][j] = "";

        int idx = 0;
        for(int i=0; i<sizeA; i++)
            for(int j=0; j<sizeB; j++){
                String actror = actorsNames.get(idx);
                grid[i][j] = actror;
                idx++;
                if(idx >= actorsNames.size())
                    return grid;
            }

        return grid;
    }

    private int getTopologySize(int topologySize, boolean strict) {
        for(int i = (int) ceil(sqrt(topologySize)); i>2; i--){
            if(topologySize % i == 0){
                if(strict && topologySize/i > 2)
                    return i;
                else
                    return i;
            }
        }
        return 2;
    }

    private boolean isRegularTopology(int topologySize) {
        for(int i = (int) ceil(sqrt(topologySize)); i>2; i--){
            if(topologySize % i == 0 && topologySize/i > 2)
                return true;
        }
        return false;
    }

    @Override
    public List<String> getNeightbours(String name) {
        return topology.get(name);
    }
}
