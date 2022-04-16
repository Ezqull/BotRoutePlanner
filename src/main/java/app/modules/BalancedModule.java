package app.modules;

public class BalancedModule extends Module{

    public BalancedModule(int x, int y, int n) {
        super(x, y, n);
        type = 'B';
        transitionTime = 1;
    }

    @Override
    public int extractionTime(int n) {
        return 2*n +2;
    }
}
