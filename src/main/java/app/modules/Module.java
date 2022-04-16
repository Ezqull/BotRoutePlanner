package app.modules;

import app.Position;

public abstract class Module extends Position {

    char type;
    int n;
    double transitionTime;
    public Module(int x, int y, int n) {
        super(x, y);
        this.n = n;
    }

    public double getTransitionTime(){
        return transitionTime;
    }

    abstract public int extractionTime(int n);

    @Override
    public String toString() {
        return "Module{" +
                "type=" + type +
                ", n=" + n +
                '}';
    }
}
