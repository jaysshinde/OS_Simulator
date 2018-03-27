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

#define N 1000
int pq[N][2];
int top = -1;

void enqueue(int data,int pr){
	int i = -1;
	for(i = top;i>=0;i--){
		if(pq[i][1]<=pr){
			pq[i+1][0] = pq[i][0];
			pq[i+1][1] = pq[i][1];
		}
		else 
			break;
	}

	pq[i+1][0] = data;
	pq[i+1][1] = pr;

	top++;

}

int dequeue(){
	if(top<0)
		return -1;
	int x = pq[top][0];
	top--;
	return x;
}






void empty(){
	while(dequeue()!=-1);
}

void age(){
	for(int i = 0;i<=top;i++)
		pq[i][1]--;

}

int main(){

	int n,waitSum = 0,tatSum = 0,currTime = 0,noInExe,inExe;
	// printf("Enter the no of processes..... ");
	scanf("%d",&n);
	int burst[n],arrival[n],service[n],wait[n],tat[n],priority[n],completed[n],burstDum[n],compTime[n];
	// printf("=========================\n");
	for(int i = 0;i<n;i++){
		// printf("Process %d\nBurst time = ",i+1);
		scanf("%d",&burst[i]);
		burstDum[i] = burst[i];
		// printf("Arrival time = ");
		scanf("%d",&arrival[i]);
		// printf("Priority = ");
		scanf("%d",&priority[i]);
		//enqueue(i,priority[i]);
		// printf("................\n");
	}
	noInExe = n;

	// for(int i = 0;i<n;i++)
	// 	printf("%d ", burstDum[i]);
	// printf("\n");

	// printf("\nThe schedulling done is as follows.......\nProcess No\tArrival Time\tBurst Time\tTurn-Around-Time\tWaiting Time\tPriority\n");
	while(noInExe!=0){
		//printf("$$\n");
		for(int i = 0;i<n;i++){
			if((arrival[i]<=currTime) && completed[i]!=1){
				enqueue(i,priority[i]);
			}
		}

		
		inExe = dequeue();
		if(inExe<0){
			currTime++;
		}
		else{
			if(burstDum[inExe] == burst[inExe])
				wait[inExe] = currTime - arrival[inExe];
			else
				wait[inExe] += currTime - service[inExe] - 1;
			
			service[inExe] = currTime;
			burstDum[inExe]--;


			if(burstDum[inExe]==0){
				completed[inExe] = 1;
				noInExe--;
				compTime[inExe] = currTime + 1;
			}

			currTime = currTime + 1;
			printf("%d %d %d\n",inExe+1,service[inExe],1);
			if(currTime!=0 && currTime%5==0)
				age(); //age every 5 seconds.

			empty();
		}
	}

	for(int i = 0;i<n;i++){
		tat[i] = compTime[i] - arrival[i];
		tatSum += tat[i];
		waitSum += wait[i];
		// printf("%d\t\t%d\t\t%d\t\t%d\t\t\t%d\t\t%d\n",i+1,arrival[i],burst[i],tat[i],wait[i],priority[i]);

	}

	printf("\n%f\n%f\n",(float)waitSum/n,(float)tatSum/n);
}

// 4 5 2 5 2 4 2 10 6 1 4 15 7

/*
	Process 1:
		Burst = 5
		Arrival = 2
		Priority = 5
	Process 2:
		Burst = 2
		Arrival = 4
		Priority = 2
	Process 3:
		Burst = 10
		Arrival = 6
		Priority = 1
	Process 4:
		Burst = 4
		Arrival = 15
		Priority = 7
*/