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
	int min = 99999,p = -1;
	for(int i = 0;i<n;i++){
		if(b[i]<min && arr[i]<=t && c[i]!=1){
			min = b[i];
			p = i;
		}
	}
	return p;
}


int main(){

	int n,waitSum = 0,tatSum = 0,currTime = 0,noInExe,inExe;
	// printf("Enter the no of processes..... ");
	scanf("%d",&n);
	int burst[n],arrival[n],service[n],wait[n],tat[n],completed[n],pr[n];
	// printf("=========================\n");
	for(int i = 0;i<n;i++){
		pr[i] = i+1;
		// printf("Process %d\nBurst time = ",i+1);
		scanf("%d",&burst[i]);
		// printf("Arrival time = ");
		scanf("%d",&arrival[i]);
		// printf("................\n");
	}

	noInExe = n;
	sort(arrival,burst,pr,n);
	// currTime = arrival[0];
	// service[0] = currTime;
	// wait[0] = 0;
	// tat[0] = burst[0];
	// waitSum += wait[0];
	// tatSum += tat[0];
	// currTime = currTime + burst[0];
	// printf("\nThe schedulling done is as follows.......\nProcess No\tArrival Time\tBurst Time\tService Time\tTurn-Around-Time\tWaiting Time\n");
	// printf("%d\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\n",1,arrival[0],burst[0],service[0],tat[0],wait[0]);
	// for(int i = 1;i<n;i++){
	// 	while(currTime<arrival[i]){
	// 		currTime++;
	// 	}
	// 	// service[i] = service[i-1] + burst[i-1];
	// 	// wait[i] = service[i] - arrival[i];
	// 	service[i] = currTime;
	// 	wait[i] = currTime - arrival[i];
	// 	tat[i] = wait[i] + burst[i];
	// 	if(wait[i]<0)
	// 		wait[i] = 0;
	// 	waitSum += wait[i];
	// 	tatSum += tat[i];
	// 	currTime = currTime + burst[i];
	// 	printf("%d\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\n",i+1,arrival[i],burst[i],service[i],tat[i],wait[i]);
	// }

	// printf("\nAverage waiting time = %f\nAverage Turn-Around-Time = %f\n==============================\n",(float)waitSum/n,(float)tatSum/n);


	// printf("\nThe schedulling done is as follows.......\nProcess No\tArrival Time\tBurst Time\tService Time\tTurn-Around-Time\tWaiting Time\n");


	while(noInExe!=0){
		
		inExe = toBeExecuted(arrival,currTime,burst,n,completed);
		//printf("%d\n",inExe);
		if(inExe<0){
			// printf("asdfasd\n");
			currTime++;
		}
		else{
			// printf(" \n");
			service[inExe] = currTime;
			wait[inExe] = currTime - arrival[inExe];
			tat[inExe] = currTime + burst[inExe] - arrival[inExe];
			waitSum += wait[inExe];
			tatSum += tat[inExe];
			completed[inExe] = 1;
			currTime = currTime + burst[inExe];
			printf("%d %d %d\n",pr[inExe],service[inExe],burst[inExe]);
			noInExe--;
		}
	}

	printf("%f\n%f\n",(float)waitSum/n,(float)tatSum/n);


	return 0;
}