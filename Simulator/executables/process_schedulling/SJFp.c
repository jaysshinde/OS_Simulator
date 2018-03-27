#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void sort(int a1[],int a2[],int a3[],int n){
	for(int i = 0;i<n;i++){
		for(int j = 0;j<n-i-1;j++){
			if(a1[j]>a1[j+1]){
				int temp = a1[j+1];
				a1[j+1] = a1[j];
				a1[j] = temp;

				temp = a2[j+1];
				a2[j+1] = a2[j];
				a2[j] = temp;

				temp = a3[j+1];
				a3[j+1] = a3[j];
				a3[j] = temp;
			}

		}
	}
}

int toBeExecuted(int arr[], int t, int b[], int n, int c[]){
	int min = 99999,p = -1, arrival = 99999;
	for(int i = 0;i<n;i++){
		if(b[i]<=min && arr[i]<=t && c[i]!=1){
			if((b[i] == min && arr[i]<arrival)||(b[i]<min)){

				min = b[i];
				p = i;
				arrival = arr[i]; //ensures FCFS
			}
		}
	}
	return p;
}


int main(){

	int n,waitSum = 0,tatSum = 0,currTime = 0,noInExe,inExe;
	// printf("Enter the no of processes..... ");
	scanf("%d",&n);
	int burst[n],arrival[n],service[n],wait[n],tat[n],completed[n],burstDum[n],compTime[n],pr[n];
	// printf("=========================\n");
	for(int i = 0;i<n;i++){
		// printf("Process %d\nBurst time = ",i+1);
		scanf("%d",&burst[i]);
		burstDum[i] = burst[i];
		// printf("Arrival time = ");
		scanf("%d",&arrival[i]);
		// printf("................\n");
	}

	noInExe = n;
	

	// printf("\nThe schedulling done is as follows.......\nProcess No\tArrival Time\tBurst Time\tTurn-Around-Time\tWaiting Time\n");


	while(noInExe!=0){
		
		inExe = toBeExecuted(arrival,currTime,burstDum,n,completed);

		if(inExe<0){
			currTime++;
		}
		else{

			printf("%d %d %d\n",inExe+1,currTime,1);
			
			if(burstDum[inExe] == burst[inExe])
				wait[inExe] = currTime - arrival[inExe];
			else
				wait[inExe] += currTime - service[inExe] - 1;

			service[inExe] = currTime;

			burstDum[inExe]--;

			if(burstDum[inExe] == 0){
				completed[inExe] = 1;
				noInExe--;
				compTime[inExe] = currTime + 1;
			}
			currTime = currTime + 1;

		}
	}

	for(int i = 0;i<n;i++){
		tat[i] = compTime[i] - arrival[i];
		tatSum += tat[i];
		waitSum += wait[i];
		// printf("%d\t\t%d\t\t%d\t\t%d\t\t\t%d\n",i+1,arrival[i],burst[i],tat[i],wait[i]);

	}

	printf("\n%f\n%f\n",(float)waitSum/n,(float)tatSum/n);


	return 0;
}


//4 2 1 4 1 5 0 3 2

/*
	Process 1:
		Burst = 2
		Arrival = 1
	Process 2:
		Burst = 4
		Arrival = 1
	Process 3:
		Burst = 5
		Arrival = 0
	Process 4:
		Burst = 3
		Arrival = 2
*/