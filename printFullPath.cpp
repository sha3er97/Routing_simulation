#include <iostream>
using namespace std;

struct Point{
  int i;
  int j;
};

void printArray(Point* arr, int n)
{
  for(int i=0; i<n; i++)
  {
    cout<<" ("<<arr[i].i<<", "<<arr[i].j<<")";
  }
}

int printPaths(int n, int m, Point *arr, int N, int curCell)
{
  arr[curCell].i = n;
  arr[curCell].j = m;
  
  if(n == 0 && m == 0)
  {
    printArray(arr, N);
    cout<<endl; // PRINT NEXT PATH IN NEW LINE
    return 0;
  }
  
  if(n == 0)
  {
    printPaths(n, m-1, arr, N, curCell-1);
    return 1;
  }
  if(m==0)
  {
    printPaths(n-1, m, arr, N, curCell-1);
    return 1;
  }
  return printPaths(n, m-1, arr, N, curCell-1) + printPaths(n-1, m, arr, N, curCell-1);
}

int main()
{
  // IF MATRIX IS 3*3
  Point arr[5];
  printPaths(2, 2, arr, 5, 4);
  
  cout<<endl;
  return 0;
}
