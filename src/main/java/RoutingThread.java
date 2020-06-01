
public class RoutingThread extends Thread {
    Region myRegion;

    public void setMyRegion(Region myRegion) {
        this.myRegion = myRegion;
    }

    public void run() {
        try {
            for (Net net : myRegion.myNetSet) {
                if (!net.isDone()) {
                        if (net.getXstart() >= myRegion.getXstart() && net.getXend() <= myRegion.getXend()
                        && net.getYstart() >= myRegion.getYstart() && net.getYend() <= myRegion.getYend()) {
                            //  in my region
                        MainClass.dummyRouting(net);
                    }
                }
            }
        } catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}
