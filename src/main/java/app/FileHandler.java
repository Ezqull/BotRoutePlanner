package app;

import app.modules.BalancedModule;
import app.modules.HighSpeedModule;
import app.modules.Module;
import app.modules.StorageAccessModule;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    private Scanner scanner;

    public FileHandler(){}

    public void openFile(String fileName){
        File file = new File(fileName);
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void scanGrid(@NotNull Grid grid){
        grid.setxSize(scanner.nextInt());
        grid.setySize(scanner.nextInt());
        grid.setnSize(scanner.nextInt());
        if(grid.getxSize() > 100 || grid.getySize() > 100 || grid.getnSize() > 10){
            System.exit(-1);
        }
        ArrayList<Module> gridLine;

        for (int i = 0; i < grid.getySize(); i++){
            gridLine = new ArrayList<>();
            char[] s = scanner.next().toCharArray();
            for(int j = 0; j < grid.getxSize(); j++){
                if(s[j] == 'H') {
                    gridLine.add(j, new HighSpeedModule(j, i, grid.getnSize()));
                }else if(s[j] == 'B'){
                    gridLine.add(j, new BalancedModule(j, i, grid.getnSize()));
                }else if(s[j] == 'S'){
                    gridLine.add(j, new StorageAccessModule(j, i, grid.getnSize()));
                }else if(s[j] == 'O'){
                    gridLine.add(j, null);
                }
            }
            grid.warehouseGrid.add(i, gridLine);
        }

        scanner.nextLine();

        int i=0;
        while(scanner.hasNext()){
            grid.containers.add(i, new Container(scanner.next(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
            if(grid.containers.get(i).getX() > grid.getxSize() || grid.containers.get(i).getY() > grid.getySize()){
                System.exit(-1);
            }
            i++;
        }
    }

    public void scanJob(@NotNull Job job){
        job.setBotPosition(new Position(scanner.nextInt(), scanner.nextInt()));
        job.setStationPosition(new Position(scanner.nextInt(), scanner.nextInt()));
        job.setTargetId(scanner.next());
    }

    public void generateResult(@NotNull Result result, String fileName){
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(
                    result.getResultList().size()-1 +
                    "\n" +
                    result.getResultTime() +
                    "\n");
            for(int i = 0; i < result.getResultList().size(); i++){
                fileWriter.write(result.getResultList().get(i) + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
