package app;

public class Main {

    public static void main(String[] args) {
        FileHandler f = new FileHandler();
        f.openFile(args[0]);
        Grid grid = new Grid();
        Job job = new Job();
        f.scanGrid(grid);
        grid.graphCreation();
        f.openFile(args[1]);
        f.scanJob(job);
        job.findTargets(grid);

        Result result = new Result();
        result.calculateShortestPath(grid, job);
        f.generateResult(result, args[2]);
    }
}
