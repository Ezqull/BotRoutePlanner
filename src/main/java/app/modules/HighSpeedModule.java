package app.modules;

public class HighSpeedModule extends Module{

    public HighSpeedModule(int x, int y, int n) {
        super(x, y, n);
        type = 'H';
        transitionTime = 0.5;
    }

    @Override
    public int extractionTime(int n){
        return 3*n + 4;
    }
}
