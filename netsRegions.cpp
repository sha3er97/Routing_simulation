#include <bits/stdc++.h>
using namespace std;

//takes one net points and return the regions that the net go throw && the vector net is the diminions of the net
set <int> get_regions(vector < pair<int, int> > points,int chipSizex,int chipSizey,int regionSizex,int regionSizey,vector<int> & net);
vector<pair<int,int>> randomNetPath(int x1, int y1, int x2, int y2);
vector<pair<pair<int,int>,pair<int,int>>> generateNets(int netsNum,int chipSizex,int chipSizey);

int main(int argc, char* argv[]){
    vector<pair<pair<int,int>,pair<int,int>>> nets;
    vector<pair<int,int>> path;
    set <int> regions;
    vector <int> net_diminions;
    int chipSizex, ChipSizey, regionSizex, regionSizey, netsNum;
    ofstream myfile;
    
    if(argc<6)
    {
        cout<<"Invalid number of parameters. Number of parameters should be 5.";
        return 0;
    }
    else
    {
        chipSizex = strtol(argv[1],nullptr,0);    ChipSizey = strtol(argv[2],nullptr,0);
        regionSizex = strtol(argv[3],nullptr,0);  regionSizey = strtol(argv[4],nullptr,0);
        netsNum = strtol(argv[5],nullptr,0);
    }
    myfile.open ("Nets.txt");
    
    
    nets = generateNets(netsNum,chipSizex,ChipSizey);
    /*
    for(int i=0;i< nets.size();i++)
        cout<<"("<<nets[i].first.first<<","<<nets[i].first.second<<")"<<"   "<<"("<<nets[i].second.first<<","<<nets[i].second.second<<")"<<endl;
    */
    for(int i=0;i<nets.size();i++)
    {
        path = randomNetPath(nets[i].first.first,nets[i].first.second,nets[i].second.first,nets[i].second.second);
        regions = get_regions(path, chipSizex,ChipSizey,regionSizex,regionSizey,net_diminions);
        /*
        for(int i=0;i< path.size();i++)
            cout<<"("<<path[i].first<<","<<path[i].second<<"), ";
        cout<<endl;
        for (int region:regions)
            cout<<region<<' ';
        cout<<endl;
        */
        myfile <<nets[i].first.first<<" "<<nets[i].second.first<<" "<<nets[i].first.second<<" "<<nets[i].second.second<<endl;
        for(int region: regions)    myfile<<region<<" ";
        myfile<<endl;
        path.clear();   regions.clear();    net_diminions.clear();
    }
    nets.clear();   myfile.close();
}

set <int> get_regions(vector < pair<int, int> > points,int chipSizex,int chipSizey,int regionSizex,int regionSizey,vector<int> & net){
    set <int> regions;
    int max_x=0,min_x = numeric_limits<int>::max(),min_y =numeric_limits<int>::max(),max_y = 0;
    int x,y,region;
    for(int i =0;i<points.size();i++){
        x=points[i].first;
        y=points[i].second;
        region = (y/regionSizey)*(chipSizex/regionSizex)+(x/regionSizex);
        max_x = max(max_x,x);
        min_x = min(min_x,x);
        max_y = max(max_y,y);
        min_y = min(min_y,y);
        regions.insert(region);
    }
    net.push_back(min_x);
    net.push_back(max_x);
    net.push_back(min_y);
    net.push_back(max_y);

    return regions;
}

vector<pair<int,int>> randomNetPath(int x1, int y1, int x2, int y2)
{
    vector<pair<int,int>> diagonal;  int start,endi;     pair<int,int>mini,maxi;
    
    while(x1 <= x2 && y1 <= y2)                             //Move Diagonal
    {
        diagonal.push_back(make_pair(x1++,y1));             //Move Right
        diagonal.push_back(make_pair(x1,y1++));             //Move Down
    }
    
    if(x1 <= x2 && y1>y2)
    {
        y1--;
        while(x1 <= x2)  diagonal.push_back(make_pair(++x1,y1)); //Move Right only
    }
    else if(x1>x2 && y1 <= y2)
    {
        x1--;
        while(y1 <= y2)  diagonal.push_back(make_pair(x1,++y1)); //Move Down only
    }
    
    srand(time(0));                                         //To make each run gives differenr rand() 
    
    start = rand() % diagonal.size();
    endi = start + (rand() % (diagonal.size()-start));
    
    //cout<<start<<endl<<endi<<endl;
    
    /****** replace the range [start,end] to right sequence then down sequence ********/
    mini = diagonal[start];
    maxi = diagonal[endi];
    diagonal.erase(diagonal.begin()+start, diagonal.begin()+endi+1);
    
    if(mini.first <= maxi.first)
    {
        while(mini.first <= maxi.first)
            diagonal.insert(diagonal.begin()+start++,make_pair(mini.first++,mini.second));
        mini.first--, mini.second++;
    }
    while(mini.second <= maxi.second)
        diagonal.insert(diagonal.begin()+start++,make_pair(mini.first,mini.second++));
        
    return diagonal;
}

vector<pair<pair<int,int>,pair<int,int>>> generateNets(int netsNum,int chipSizex,int chipSizey)
{
    vector<pair<pair<int,int>,pair<int,int>>> nets;     int x1,x2,y1,y2;
    
    srand(time(0));                                         //To make each run gives differenr rand() 
    
    for(int i=0; i<netsNum; i++)
    {
        x1 = rand() % chipSizex;
        x2 = x1 + (rand() % (chipSizex-x1));
        y1 = rand() % chipSizey;
        y2 = y1 + (rand() % (chipSizey-y1));
        nets.push_back(make_pair(make_pair(x1,y1),make_pair(x2,y2)));
    }
    
    return nets;
}
