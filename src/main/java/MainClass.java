import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MainClass {
    static int routed = 0;

    public static void main(String[] args) throws InterruptedException {
        //chip size assumed 100*100 and region 5*5
        int chipSizeX = 128;
        int chipSizeY = 128;
        int regionSizeX = 4;
        int regionSizeY = 4;
        int regionsNum = (chipSizeX * chipSizeY) / (regionSizeX * regionSizeY); //1024
        // Number of threads = no of regions
        Set<Net>[] globalRegions = new Set[regionsNum];
        for (int i = 0; i < regionsNum; i++) {
            globalRegions[i] = new HashSet<>();
        }
        int netsCount =0 ;
        //Net[] nets = new Net[netsCount];
        try {
            File myObj = new File("nets.txt");
            Scanner myReader = new Scanner(myObj);
            int Netiterator = 0;
            while (myReader.hasNextLine()) {
                String coordinates = myReader.nextLine();
                String[] Splitcoordinates = coordinates.split(" ");
                Net net = new Net(Netiterator, Integer.parseInt(Splitcoordinates[0]), Integer.parseInt(Splitcoordinates[1])
                        , Integer.parseInt(Splitcoordinates[2]), Integer.parseInt(Splitcoordinates[3]));
                //DONE :: fill set with nets data
                String regions = myReader.nextLine();
                String[] SplitRegions = regions.split(" ");
                for (int i = 0; i < SplitRegions.length; i++) {
                    globalRegions[i].add(net);
                }
                //nets[Netiterator] =net;
                netsCount++;
                Netiterator++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //DONE :: read nets from file

        RoutingThread[] Threads = new RoutingThread[regionsNum];
        //DONE :: start timer

        long startTime = System.currentTimeMillis();
        /***********************************all sequential****************************************************************/
/*
        //DONE :: connect unfinished nets sequentially
        for (int i = 0; i < regionsNum; i++) {
            for (Net net : globalRegions[i]) {
                if (!net.isDone()) {
                    dummyRouting(net);
                }
            }
        }
        //DONE :: end timer
        long endTime3 = System.currentTimeMillis();
        System.out.println("all-sequential method (milli seconds): " + (endTime3 - startTime));
*/
        /*****************************************for method 2,3*********************************************************/

        int iterator = 0;
        int iThreads = 0;
        int jThreads = 0;
        for (iThreads = 0; iThreads <= chipSizeX - regionSizeX; iThreads += regionSizeX) {
            for (jThreads = 0; jThreads <= chipSizeY - regionSizeY; jThreads += regionSizeY) {
                Threads[iterator] = new RoutingThread();
                Region region = new Region(globalRegions[iterator], iThreads, iThreads + regionSizeX, jThreads, jThreads + regionSizeY);
                Threads[iterator].setMyRegion(region);
                Threads[iterator].start();
                iterator++;
            }
        }
        for (int i = 0; i < regionsNum; i++)
            Threads[i].join();


        /*******************************************paper simulation********************************************************/

        //DONE :: connect unfinished nets sequentially
        System.out.println("routed: " + routed);
        for (int i = 0; i < regionsNum; i++) {
            for (Net net : globalRegions[i]) {
                if (!net.isDone()) {
                    dummyRouting(net);
                }
            }
        }
        System.out.println("routed: " + routed);
        //DONE :: end timer
        long endTime = System.currentTimeMillis();
        System.out.println("old method (milli seconds): " + (endTime - startTime));

        /*******************************************our modification**********************************************************/
/*
        for (int k = 4; k < regionsNum; k *= 16) { //k : hierarchy level =4,16,64...
            int iterator2 = 0;
            for (int i = 0; i <= chipSizeX - regionSizeX; ) {
                for (int j = 0; j <= chipSizeY - regionSizeY; ) {

                    Threads[iterator2] = new RoutingThread();
                    Set<Net> merged = new HashSet<>();
                    for (int z = 0; z < k / 2; z++) {
                        if (iterator2 + z >= regionsNum) break;
                        merged.addAll(globalRegions[iterator2 + z]);
                    }
                    for (int z = 0; z < k / 2; z++) {
                        if (iterator2 + z >= regionsNum) break;
                        merged.addAll(globalRegions[iterator2 + (chipSizeX / regionSizeX) + z]);
                    }
                    Region region = new Region(merged, i, i + regionSizeX * k/2, j, j + regionSizeY * k/2);
                    Threads[iterator2].setMyRegion(region);
                    Threads[iterator2].start();
                    for (int f = 0; f < k/2; f++) {
                        iterator2++;
                        i += regionSizeX;
                        j += regionSizeY;
                    }
                }
            }
            for (int t = 0; t < regionsNum; t += k)
                Threads[t].join();
            System.out.println("routed at level: " + k + " = " + routed);
            if(routed==netsCount) break;
        }

        //DONE :: end timer 2
        long endTime2 = System.currentTimeMillis();
        System.out.println("new method (milli seconds): " + (endTime2 - startTime));
        System.out.println("routed: " + routed);
*/
    }

    /***************************************************routing method***********************************************************************/
    public static void dummyRouting(Net net) {
        for (int i = 0; i < 10000000; i++) {
            i++;
            i--;
        } //dummy function to consume time instead of complicated detailed routing
        net.setDone(true);
        System.out.println("NET no. : " + net.getId() + " is now routed");
        routed++;
    }
}
