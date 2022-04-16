package app;

import org.jetbrains.annotations.NotNull;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.ArrayList;
import java.util.List;

public class Result {

    private DijkstraShortestPath<String, DefaultWeightedEdge> shortestPath;
    private List<String> resultList;
    private double resultTime;

    public void calculateShortestPath(@NotNull Grid grid, @NotNull Job job){
        List<String> targetPath;
        List<String> stationPath;
        double targetTime;
        double stationTime;
        Container target = job.getTarget(0);

        resultList = new ArrayList<>();
        shortestPath = new DijkstraShortestPath<>(grid.gridGraph);

        for(int i = 0; i < job.getTargetList().size(); i++){
            if((getPathTime(target, job, grid)) > getPathTime(job.getTarget(i), job, grid)){
                target = job.getTarget(i);
            }
        }

        targetPath = shortestPath.getPath(job.getBotPosition().getPosition(), target.getPosition()).getVertexList();
        targetTime = shortestPath.getPathWeight(job.getBotPosition().getPosition(), target.getPosition());

        targetTime += grid.warehouseGrid.get(target.getY()).get(target.getX()).extractionTime(target.getN());

        stationPath = shortestPath.getPath(target.getPosition(), job.getStationPosition().getPosition()).getVertexList();
        stationTime = shortestPath.getPathWeight(target.getPosition(), job.getStationPosition().getPosition());
        stationPath.remove(0);

        resultTime = targetTime + stationTime;
        resultList.addAll(targetPath);
        resultList.addAll(stationPath);
    }

    private double getPathTime(@NotNull Container target, @NotNull Job job, @NotNull Grid grid){
        return shortestPath.getPathWeight(job.getBotPosition().getPosition(), target.getPosition()) + grid.warehouseGrid.get(target.getY()).get(target.getX()).extractionTime(target.getN()) + shortestPath.getPathWeight(target.getPosition(), job.getStationPosition().getPosition());
    }

    public List<String> getResultList(){
        return resultList;
    }

    public double getResultTime(){
        return resultTime;
    }
}
