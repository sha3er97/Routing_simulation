"""
VLSI-- team 11
"""
import random 

def generate_Nets(chipx,chipy,regionx,regiony):
    f=open("data.txt","a")
    #loop on regions::
    for i in range(0,chipx,regionx):
        for j in range(0,chipy,regiony):
            #nets in the region:
            region = int(j/regiony)*int(chipx/regionx)+int(i/regionx)
            end = random.randint(5,15)
            for n in range (0,end):
                xstart = random.randint(i,i+regionx-2)
                xend = random.randint(xstart+1,i+regionx)
                ystart = random.randint(j,j+regiony-2)
                yend = random.randint(ystart+1,j+regiony)
                f.write(str(xstart)+" "+str(xend)+" "+str(ystart)+" "+str(yend)+"\n")
                f.write(str(region)+"\n")
            
            #nets in & out the region::
            if i != chipx - regionx and j != chipy - regiony:
                end = random.randint(2,5)
                for n in range (0,end):
                    xstart = random.randint(i,i+regionx-1)
                    xend = random.randint(min(i+regionx,chipx-2),chipx-1)
                    ystart = random.randint(j,j+regiony-1)
                    yend = random.randint(min(j+regiony,chipy-2),chipy-1)
                    maxi = int(xend/regionx)
                    maxj = int(yend/regiony)
                    rani = random.randint(int(i/regionx)+1,maxi)
                    ranj = random.randint(int(j/regiony)+1,maxj)
                    rg = int(ranj)*int(chipx/regionx)+int(rani)
                    f.write(str(xstart)+" "+str(xend)+" "+str(ystart)+" "+str(yend)+"\n")
                    f.write(str(region)+" "+str(rg)+"\n")

    f.close()
    
    
generate_Nets(5000,4000,50,40)
    
#no = (j/regionSizey)*(chipSizex/regionSizex)+(i/regionSizex)