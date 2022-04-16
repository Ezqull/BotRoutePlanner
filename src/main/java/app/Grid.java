package app;

import app.modules.Module;
import org.jetbrains.annotations.NotNull;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import java.util.ArrayList;

public class Grid{
    Graph<String, DefaultWeightedEdge> gridGraph;
    ArrayList<ArrayList<Module>> warehouseGrid;
    ArrayList<Container> containers;
    private int xSize;
    private int ySize;
    private int nSize;

    public Grid(){
        warehouseGrid = new ArrayList<>();
        containers = new ArrayList<>();
        gridGraph = new DefaultUndirectedWeightedGraph<>(DefaultWeightedEdge.class);
    }

    public int getxSize() {
        return xSize;
    }

    public void setxSize(int xSize) {
        this.xSize = xSize;
    }

    public int getySize() {
        return ySize;
    }

    public void setySize(int ySize) {
        this.ySize = ySize;
    }

    public int getnSize() {
        return nSize;
    }

    public void setnSize(int nSize) {
        this.nSize = nSize;
    }

    public double checkWeight(@NotNull Module m1, @NotNull Module m2){
        return Math.max(m1.getTransitionTime(), m2.getTransitionTime());
    }

    public void graphCreation() {
        DefaultWeightedEdge edge;
        for (int i = 0; i < ySize; i++){
            for(int j = 0; j < xSize; j++) {
                if(warehouseGrid.get(i).get(j) != null){
                    gridGraph.addVertex(warehouseGrid.get(i).get(j).getPosition());
                }
            }
        }
        for (int i = 0; i < ySize; i++){
            for (int j = 0; j < xSize; j++) {
                if (j != xSize - 1 && i != ySize - 1) {
                    if (warehouseGrid.get(i).get(j) != null && warehouseGrid.get(i).get(j + 1) != null) {
                            edge = gridGraph.addEdge(warehouseGrid.get(i).get(j).getPosition(), warehouseGrid.get(i).get(j + 1).getPosition());
                            gridGraph.setEdgeWeight(edge, checkWeight(warehouseGrid.get(i).get(j), warehouseGrid.get(i).get(j + 1)));
                    }
                    if (warehouseGrid.get(i).get(j) != null && warehouseGrid.get(i + 1).get(j) != null) {
                        edge = gridGraph.addEdge(warehouseGrid.get(i).get(j).getPosition(), warehouseGrid.get(i + 1).get(j).getPosition());
                        gridGraph.setEdgeWeight(edge, checkWeight(warehouseGrid.get(i).get(j), warehouseGrid.get(i + 1).get(j)));
                    }

                }else if (j == xSize - 1 && i != ySize - 1 && (warehouseGrid.get(i).get(j) != null && warehouseGrid.get(i + 1).get(j) != null)) {
                    edge = gridGraph.addEdge(warehouseGrid.get(i).get(j).getPosition(), warehouseGrid.get(i + 1).get(j).getPosition());
                    gridGraph.setEdgeWeight(edge, checkWeight(warehouseGrid.get(i).get(j), warehouseGrid.get(i + 1).get(j)));
                } else if (i == ySize - 1 && j != xSize - 1 && (warehouseGrid.get(i).get(j) != null && warehouseGrid.get(i).get(j + 1) != null)) {
                    edge = gridGraph.addEdge(warehouseGrid.get(i).get(j).getPosition(), warehouseGrid.get(i).get(j + 1).getPosition());
                    gridGraph.setEdgeWeight(edge, checkWeight(warehouseGrid.get(i).get(j), warehouseGrid.get(i).get(j + 1)));
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Grid{" +
                "xSize=" + xSize +
                ", ySize=" + ySize +
                ", nSize=" + nSize +
                ", grid=" + warehouseGrid +
                ", containers=" + containers +
                '}';
    }
}
