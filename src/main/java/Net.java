
public class Net {
    int id;
    boolean done;
    int Xstart, Xend, Ystart, Yend;

    public Net(int id, int xstart, int xend, int ystart, int yend) {
        this.id = id;
        this.done = false;
        Xstart = xstart;
        Xend = xend;
        Ystart = ystart;
        Yend = yend;
    }

    public int getId() {
        return id;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isDone() {
        return done;
    }

    public int getXstart() {
        return Xstart;
    }

    public int getXend() {
        return Xend;
    }

    public int getYstart() {
        return Ystart;
    }

    public int getYend() {
        return Yend;
    }
}
