package app;

public class Container extends Position{

    private final String id;
    private final int n;
    public Container(String id, int x, int y, int n) {
        super(x, y);
        this.n = n;
        this.id  = id;
    }

    public int getN() {
        return n;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Container{" +
                "id='" + id + '\'' +
                ", Position{" +
                "x=" + getX() +
                ", y=" + getY() +
                ", n=" + n +
                '}';
    }
}
