import java.util.*;

public class MainClass {

    public static void main(String[] args) throws InterruptedException {
        int netsCount = 1000;
        Net[] nets = new Net[netsCount];
        //TODO :: read nets from file
        //chip size assumed 100*100 and region 5*5
        int chipSizeX = 100;
        int chipSizeY = 100;
        int regionSizeX = 5;
        int regionSizeY = 5;
        int regionsNum = (chipSizeX * chipSizeY) / (regionSizeX * regionSizeY); //400
        // Number of threads = no of regions
        Set<Net>[] globalRegions = new Set[regionsNum];
        for (int i = 0; i < regionsNum; i++) {
            globalRegions[i] = new HashSet<>();
        }
        for (int i = 0; i < netsCount; i++) {
            //TODO :: fill set with nets data
        }
        RoutingThread[] Threads = new RoutingThread[regionsNum];
        //DONE :: start timer
        /*******************************************paper simulation********************************************************/
        long startTime = System.currentTimeMillis();
        int iterator = 0;
        for (int i = 0; i < chipSizeX - regionSizeX; i += regionSizeX) {
            for (int j = 0; j < chipSizeY - regionSizeY; j += regionSizeY) {
                Threads[iterator] = new RoutingThread();
                Region region = new Region(globalRegions[iterator], i, i + regionSizeX, j, j + regionSizeY);
                Threads[iterator].setMyRegion(region);
                Threads[iterator].start();
                iterator++;
            }
        }
        for (int i = 0; i < regionsNum; i++)
            Threads[i].join();
        //DONE :: connect unfinished nets
        for (int i = 0; i < regionsNum; i++) {
            for (Net net : globalRegions[i]) {
                if (!net.isDone())
                    dummyRouting(net);
            }
        }
        //DONE :: end timer
        long endTime = System.currentTimeMillis();
        System.out.println("old method (milli seconds): " + (endTime - startTime));
        /*******************************************our modification**********************************************************/
        //DONE :: start timer
        long startTime2 = System.currentTimeMillis();

        for (int k = 4; k < regionsNum; k *= 2) { //k : hierarchy level =4,8,16,32...
            int iterator2 = 0;
            for (int i = 0; i < chipSizeX - regionSizeX; ) {
                for (int j = 0; j < chipSizeY - regionSizeY; ) {

                    Threads[iterator2] = new RoutingThread();
                    Set<Net> merged = new HashSet<>();
                    for (int z = 0; z < k; z++) {
                        if (iterator2 + z >= regionsNum) break;
                        merged.addAll(globalRegions[iterator2 + z]);
                    }
                    Region region = new Region(merged, i, i + regionSizeX * k, j, j + regionSizeY * k);
                    Threads[iterator2].setMyRegion(region);
                    Threads[iterator2].start();
                    for (int f = 0; f < k; f++) {
                        iterator2++;
                        i += regionSizeX;
                        j += regionSizeY;
                    }
                }
            }
            for (int t = 0; t < regionsNum; t += k)
                Threads[t].join();
        }

        //DONE :: end timer 2
        long endTime2 = System.currentTimeMillis();
        System.out.println("old method (milli seconds): " + (endTime2 - startTime2));
    }

    public static void dummyRouting(Net net) {
        for (int i = 0; i < 100000; i++) {
            i++;
            i--;
        } //dummy function to consume time instead of complicated detailed routing
        net.setDone(true);
        System.out.println("NET no. : "+net.getId()+" is now routed");
    }
}
