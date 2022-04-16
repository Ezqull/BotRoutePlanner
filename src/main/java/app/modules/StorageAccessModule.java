package app.modules;

public class StorageAccessModule extends Module{

    public StorageAccessModule(int x, int y, int n) {
        super(x, y, n);
        type = 'S';
        transitionTime = 2;
    }

    @Override
    public int extractionTime(int n) {
        return n + 1;
    }
}
