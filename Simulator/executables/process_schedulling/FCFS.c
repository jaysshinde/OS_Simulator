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


int main(){

	int n,waitSum = 0,tatSum = 0,currTime = 0;
	// printf("Enter the no of processes..... ");
	scanf("%d",&n);
	int burst[n],arrival[n],service[n],wait[n],tat[n],pr[n];
	// printf("=========================\n");
	for(int i = 0;i<n;i++){
		// printf("Process %d\nBurst time = ",i+1);
		pr[i] = i+1;
		scanf("%d",&burst[i]);
		// printf("%d",burst[i] );
		// printf("Arrival time = ");
		scanf("%d",&arrival[i]);
		// printf("%d",arrival[i] );
		// printf("................\n");
	}
	// for(int i = 0;i<n;i++){
	// 	printf("%d %d %d\n",pr[i],arrival[i],burst[i] );
	// }

	// printf("\n");
	sort(arrival,burst,pr,n);

	currTime = arrival[0];
	service[0] = currTime;
	wait[0] = 0;
	tat[0] = burst[0];
	waitSum += wait[0];
	tatSum += tat[0];
	currTime = currTime + burst[0];
	// for(int i = 0;i<n;i++){
	// 	printf("%d %d %d\n",pr[i],arrival[i],burst[i] );
	// }

	// printf("\n");

	// printf("\nThe schedulling done is as follows.......\nProcess No\tArrival Time\tBurst Time\tService Time\tTurn-Around-Time\tWaiting Time\n");
	printf("%d %d %d\n",pr[0],service[0],burst[0]);
	for(int j = 1;j<n;j++){
		while(currTime<arrival[j]){
			currTime++;
		}
		// service[i] = service[i-1] + burst[i-1];
		// wait[i] = service[i] - arrival[i];

		// printf("i--- %d, pr---- %d\n",j,pr[j]);
		service[j] = currTime;
		wait[j] = currTime - arrival[j];
		tat[j] = wait[j] + burst[j];
		if(wait[j]<0)
			wait[j] = 0;
		waitSum += wait[j];
		tatSum += tat[j];
		currTime = currTime + burst[j];
		printf("%d %d %d\n",pr[j],service[j],burst[j]);
	}

	printf("%f\n%f\n",(float)waitSum/n,(float)tatSum/n);


	return 0;
}