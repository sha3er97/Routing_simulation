# Routing_simulation
Simulation for our routing algorithm and comparing it with two others algorithms.

## Prerequisites
1. g++ compiler.

## How To Run

1. Run netsRegions with inputs chipSizex, ChipSizey, regionSizex, regionSizey, netsNum for example:
```sh
$ g++ netsRegions.cpp -o netsRegions 
$ ./netsRegions 15 20 4 3 20
```
the output will be in file called "Nets.txt" and the output is containing pairs of lines. The first line in the pair is the start and end points for the net, the second line contains the regions that net paths by.