package app;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Job {
    private Position botPosition;
    private Position stationPosition;
    private final List<Container> targetsList;

    private String targetId;

    public Job(){
        targetsList = new ArrayList<>();
    }

    public Position getBotPosition() {
        return botPosition;
    }

    public void setBotPosition(Position botPosition) {
        this.botPosition = botPosition;
    }

    public Position getStationPosition() {
        return stationPosition;
    }

    public void setStationPosition(Position stationPosition) {
        this.stationPosition = stationPosition;
    }

    public Container getTarget(int index) {
        return targetsList.get(index);
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public List<Container> getTargetList(){
        return targetsList;
    }

    public void findTargets(@NotNull Grid grid){
        int index = 0;
        for(int i = 0; i < grid.containers.size(); i++){
            if(grid.containers.get(i).getId().equals(targetId)){
                targetsList.add(index, new Container(
                                                targetId,
                                                grid.containers.get(i).getX(),
                                                grid.containers.get(i).getY(),
                                                grid.containers.get(i).getN()));
                index++;
            }
        }
    }

    @Override
    public String toString() {
        return "Job{" +
                "botPosition=" + botPosition +
                ", stationPosistion=" + stationPosition +
                ", target='" + targetsList + '\'' +
                '}';
    }
}
