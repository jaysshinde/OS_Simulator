#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void sort(int a1[],int a2[],int a3[],int a4[],int n){
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

				temp = a4[j+1];
				a4[j+1] = a4[j];
				a4[j] = temp;
			}

		}
	}
}

int noInQueue(int arr[],int t,int n){
	int  count = 0;
	for(int i = 0;i<n;i++){
		if(arr[i]<=t)
			count++;
	}
	return count;

}

int leftOut(int a1[],int t, int a2[], int n){
	int count = 0;
	for(int i = 0;i<n;i++){
		if(a1[i]<=t&&a2[i]!=1){
			count++;
		}
	}
	return count;
}


int main(){

	int n,waitSum = 0,tatSum = 0,q,noInExe,currTime = 0,inExe;
	// printf("Enter the no of processes..... ");
	scanf("%d",&n);
	noInExe = n;
	// printf("Enter the duration of each Time Quantum...... ");
	scanf("%d",&q);
	int burst[n],arrival[n],service[n],wait[n],tat[n],burstDum[n],completed[n],pr[n];
	// printf("=========================\n");
	for(int i = 0;i<n;i++){
		// printf("Process %d\nBurst time = ",i+1);
		scanf("%d",&burst[i]);
		pr[i] = i+1; 
		burstDum[i] = burst[i];
		// printf("Arrival time = ");
		scanf("%d",&arrival[i]);
		// printf("................\n");
	}

	sort(arrival,burst,burstDum,pr,n);

	// printf("The process are schedulled in the following order..........\n");

	currTime = arrival[0];
	inExe = 0;
	service[inExe] = 0;
	wait[inExe] = 0;
	// inExe = (inExe + 1)%n;
	printf("%d %d",pr[inExe],currTime);
	burstDum[inExe] = burstDum[inExe] - q;
	int excess = 0 - burstDum[inExe];
	if(excess>=0){
		currTime = currTime + q - excess;
		printf(" %d\n",q-excess);
		noInExe--;
		burstDum[inExe] = 0;
		completed[inExe] = 1;
		tat[inExe] = currTime - arrival[inExe];
	}
	else{
		currTime = currTime + q;
		printf(" %d\n",q);
	}

	int k = noInQueue(arrival,currTime,n);
	inExe = (inExe + 1)%k;

	int iteration = 0;
	while(completed[inExe] == 1){
		int k = noInQueue(arrival,currTime,n);
		while(leftOut(arrival,currTime,completed,n)==0){
			k = noInQueue(arrival,currTime,n);
			currTime++;
		}
		inExe = (inExe + 1)%k;	
		// iteration++;
		// if(iteration==){
		// 	currTime++;
		// 	continue;
		// }
	}


	while(noInExe>1){
		// if(currTime<arrival[inExe]){
		// 	currTime++;
		// 	continue;
		// }
		printf("%d %d",pr[inExe],currTime);
		if(burstDum[inExe] == burst[inExe]){
			wait[inExe] = currTime - arrival[inExe];
		}
		else
			wait[inExe] = wait[inExe] + currTime - (service[inExe] + q);

		service[inExe] = currTime;
		burstDum[inExe] = burstDum[inExe] - q;
		int excess = 0 - burstDum[inExe];
		// printf("****\n");
		if(excess>=0){
			iteration = 0;
			currTime = currTime + q - excess;
			printf(" %d\n",q-excess);
			completed[inExe] = 1;
			tat[inExe] = currTime - arrival[inExe];
			burstDum[inExe] = 0;
			noInExe--;
			while(completed[inExe] == 1){
				int k = noInQueue(arrival,currTime,n);
				while(leftOut(arrival,currTime,completed,n)==0){
					// printf("#####\n");
					k = noInQueue(arrival,currTime,n);
					currTime++;
				}
				inExe = (inExe + 1)%k;	
				// iteration++;
				// if(iteration==noInQueue(arrival,currTime,n)){
				// 	currTime++;
				// 	continue;
				// }
						
			}
		}
		else{
			iteration = 0;
			currTime = currTime + q;
			printf(" %d\n",q);
			int k = noInQueue(arrival,currTime,n);
			inExe = (inExe + 1)%k;
			while(completed[inExe] == 1){
				int k = noInQueue(arrival,currTime,n);
				while(leftOut(arrival,currTime,completed,n)==0){
					// printf("$$$$\n");
					k = noInQueue(arrival,currTime,n);
					currTime++;
				}
				inExe = (inExe + 1)%k;	
				// iteration++;
				// if(iteration==noInQueue(arrival,currTime,n)){
				// 	currTime++;
				// 	continue;
				// }
			}
		}

	}

	k = noInQueue(arrival,currTime,n);
	inExe = (inExe + 1)%k;
	while(completed[inExe] == 1){
		int k = noInQueue(arrival,currTime,n);
		while(leftOut(arrival,currTime,completed,n)==0){
			k = noInQueue(arrival,currTime,n);
			currTime++;
		}
		inExe = (inExe + 1)%k;	
		// iteration++;
		// if(iteration==noInQueue(arrival,currTime,n)){
		// 	currTime++;
		// 	continue;
		// }
	}
	printf("%d %d %d\n",pr[inExe],currTime,burstDum[inExe]);

	if(burstDum[inExe] == burst[inExe]){
		wait[inExe] = currTime - arrival[inExe];
	}
	else
		wait[inExe] = wait[inExe] + currTime - (service[inExe] + q);

	service[inExe] = currTime;
	tat[inExe] = service[inExe] + burstDum[inExe] - arrival[inExe];
	
	for(int i = 0;i<n;i++){
		waitSum += wait[i];
		tatSum += tat[i];
	}

	// printf("\nThe scheduling done is as follows.......\nProcess No\tArrival Time\tBurst Time\tTurn-Around-Time\n");

	// for(int i = 0;i<n;i++){
	// 	printf("%d\t\t%d\t\t%d\t\t%d\n",i+1,arrival[i],burst[i],tat[i]);

	// }

	printf("\n%f\n%f\n",(float)waitSum/n,(float)tatSum/n);

	return 0;
}