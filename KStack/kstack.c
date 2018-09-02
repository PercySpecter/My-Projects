/*
*Program to efficiently implement multiple stacks in a single dimensional array with constant time push and pop operations
*@author Kinjal Ray
*Date : 02.09.18
*/

#include<stdio.h>
#include<stdlib.h>

/**
*Stack numbers are zero-indexed
*/

typedef struct KStack
{
	int *data;	//stores the data of the stack
	
	/**
	*nextIndex : 
	*Size = totalSize 
	*This serves two purposes - 
	*When the index is empty, it stores the index of the next empty index
	*When the index holds a value, it holds the index of the previous value in that stack
	*/
	int *nextIndex;
	
	int *top;	//stores the index of the top elements of the stacks
	int noOfStacks;	//stores the number of stacks being maintained
	int nextAvailable;	//stores the index of the next empty space. Holds -1 when space is full
}kstack;

/*function to create a the array with maximum capacity totalSize in noOfStacks stacks*/
kstack createKStack(int totalSize , int noOfStacks)
{
	kstack stack;
	int i;
	stack.data = (int *)malloc(totalSize * sizeof(int));
	stack.nextIndex = (int *)malloc(totalSize * sizeof(int));
	stack.top = (int *)malloc(noOfStacks * sizeof(int));
	
	stack.noOfStacks = noOfStacks;
	
	/*Since array is empty, next available space is the first index*/
	stack.nextAvailable = 0;
	
	/**
	*Intially the array is empty. So nextIndex array contains the indices of the next vacant index of each index
	*The next vacant index for each index is the index after that
	*/
	for(i = 0; i < totalSize - 1; i++)
		stack.nextIndex[i] = i+1;
	stack.nextIndex[totalSize - 1] = -1;	//For the last index, there is no vacant index after it, so it is -1
	
	/*Initialising stack tops to -1*/
	for(i = 0; i < noOfStacks; i++)
		stack.top[i] = -1;
	
	return stack;
}

/*function to check if the total capacity is exhausted*/
int isFull(kstack stack)
{
	return (stack.nextAvailable == -1);
}

/*function to check if there is no element stored in stack stackNo*/
int isEmpty(kstack stack , int stackNo)
{
	return (stack.top[stackNo] == -1);
}

/*function to push element in stack stackNo*/
int push(kstack *stack , int stackNo , int value)
{
	if(isFull(*stack))
		return 0;
	
	int i = (*stack).nextAvailable;	//fetching the next vacant index in the array

	/**
	*Since index i is vacant, nextIntdex[i] contains the index of the vacant cell after it
	*Hence it is assigned to nextAvailable for fututre
	*/
	(*stack).nextAvailable = (*stack).nextIndex[i];
	
	(*stack).data[i] = value;	//storing data in the vacant index

	/**
	*Since index i is no longer vancant, nextIndex[i] will contain the index to the next element in the stack 
	*i.e. the top before the push
	*/
	(*stack).nextIndex[i] = (*stack).top[stackNo];
	
	(*stack).top[stackNo] = i;	//udating top of stack
	
	return 1;
}

/*function to pop element from stack stackNo*/
int pop(kstack *stack , int stackNo , int *value)
{
	if(isEmpty(*stack , stackNo))
		return 0;
	
	int i = (*stack).top[stackNo];	//fetching the index of the top element in the stack
	*value = (*stack).data[i];	//fetching the value to be popped
	(*stack).top[stackNo] = (*stack).nextIndex[i];	//top is reset to the next element in the stack
	
	/**
	*Since index i is now vacant, nextIntdex[i] holds the index of the next vacant cell
	*/
	(*stack).nextIndex[i] = (*stack).nextAvailable;
	
	(*stack).nextAvailable = i;	//index i has now become vacant and hence available
	return 1;
}


/*main function to demonstrate the implementatiom*/
int main()
{
	kstack s;
	int size , k , sno , val , status;
	printf("Enter total size to allocate(total number of elements in all stacks):");
	scanf("%d" , &size);
	printf("Enter number of stacks:");
	scanf("%d" , &k);
	s = createKStack(size , k);
	int ch;
	do
	{
		printf("\n1.push\n2.pop\n0.Exit\nEnter choice:");
		scanf("%d" , &ch);
		switch(ch)
		{
			case 1:
				printf("Enter stack number(zero-indexed):");
				scanf("%d" , &sno);
				printf("Enter data to push:");
				scanf("%d" , &val);
				status = push(&s , sno , val);
				if(status)
					printf("Pushed successfully.\n");
				else
					printf("Stack Overflow!!\n");
			break;
			case 2:
				printf("Enter stack number(zero-indexed):");
				scanf("%d" , &sno);
				status = pop(&s , sno , &val);
				if(status)
					printf("Popped value = %d\n" , val);
				else
					printf("Stack Underflow!!\n");
			break;
			case 0:
			break;
			default:
				printf("Invalid choice. Please follow the instructions!!\n");
		}
	}while(ch != 0);
	return 0;
}























