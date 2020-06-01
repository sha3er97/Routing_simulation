import java.util.Set;

public class Region {
    Set<Net> myNetSet;
    int Xstart, Xend, Ystart, Yend;

    public Region(Set<Net> myNetSet, int xstart, int xend, int ystart, int yend) {
        this.myNetSet = myNetSet;
        Xstart = xstart;
        Xend = xend;
        Ystart = ystart;
        Yend = yend;
    }

    public int getXstart() {
        return Xstart;
    }

    public void setXstart(int xstart) {
        Xstart = xstart;
    }

    public int getXend() {
        return Xend;
    }

    public void setXend(int xend) {
        Xend = xend;
    }

    public int getYstart() {
        return Ystart;
    }

    public void setYstart(int ystart) {
        Ystart = ystart;
    }

    public int getYend() {
        return Yend;
    }

    public void setYend(int yend) {
        Yend = yend;
    }
}
