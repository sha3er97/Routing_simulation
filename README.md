# Routing_simulation
Simulation for our routing algorithm and comparing it with two others algorithms.

## Prerequisites
1. g++ compiler.
2. java IDE(eclipse, IntelliJ, ...).

## How To Run

1. Run netsRegions file with inputs chipSizex, ChipSizey, regionSizex, regionSizey and netsNum. for example:
```sh
$ g++ netsRegions.cpp -o netsRegions 
$ ./netsRegions 15 20 4 3 20
```
The output will be in file called "Nets.txt" and the output contains pairs of lines. each pair descripes a net, The first line in the pair is the start and end points for the net, the second line contains the regions that net paths by.

2. Run the java project with IDE(eclipse, IntelliJ, ...) and get the output (running time).

## Team members
[Ayman Azzam](https://github.com/AymanAzzam)        

[Mostafa Waleed](https://github.com/sha3er97)       

[Menna Fekry](https://github.com/MennaFekry)        

[Reham Ali](https://github.com/rehamaali)           
