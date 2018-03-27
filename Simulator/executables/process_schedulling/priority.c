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



int queue[1000][2];
int top = -1;
int rear = -1;
int n;

void enqueue(int x,int pr){
	int i = -1;
	for(i = rear;i>=0;i--){
		if(queue[i][1]<=pr){
			queue[i+1][0] = queue[i][0];
			queue[i+1][1] = queue[i][1];
		}
		else 
			break;
	}
	queue[i+1][0] = x;
	queue[i+1][1] = pr;
	rear++;
}

int dequeue(){
	if(rear<0)
		return -1;
	int x = queue[rear][0];
	rear--;
	return x;
}

void age(){
	for(int i = 0;i<=rear;i++){
		queue[i][1]--;
	}

}

void empty(){
	while(dequeue()!=-1);
}

int main(){

	int waitSum = 0,tatSum = 0,currTime = 0,noInExe,inExe;
	// printf("Enter the no of processes..... ");
	scanf("%d",&n);
	int burst[n],arrival[n],service[n],wait[n],tat[n],priority[n],completed[n],pr[n];
	// printf("=========================\n");
	for(int i = 0;i<n;i++){
		// printf("Process %d\nBurst time = ",i+1);
		pr[i] = i+1;
		scanf("%d",&burst[i]);
		// printf("Arrival time = ");
		scanf("%d",&arrival[i]);
		// printf("Priority = ");
		scanf("%d",&priority[i]);
		//enqueue(i,priority[i]);
		// printf("................\n");
	}

	// sort(arrival,burst,priority,pr,n);
	noInExe = n;

	// printf("\nThe schedulling done is as follows.......\nProcess No\tArrival Time\tBurst Time\tService Time\tTurn-Around-Time\tPriority\n");
	while(noInExe!=0){
		//printf("$$\n");
		for(int i = 0;i<n;i++){
			// printf("^^\n");
			if((arrival[i]<=currTime) && completed[i]!=1)
				enqueue(i,priority[i]);
		}

		
		inExe = dequeue();
		//printf("%d\n",inExe);

		if(inExe<0){
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
			if(currTime%5==0)
				age();
			empty();
		}
	}

	printf("%f\n%f\n",(float)waitSum/n,(float)tatSum/n);
}

// 4 5 2 5 2 4 2 10 6 1 4 15 
