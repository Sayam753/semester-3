#include <stdio.h> 

void heapify(int arr[], int n, int i) 
{ 
	int largest = i; // Initialize largest as root 
	int l = 2*i + 1; // left = 2*i + 1 
	int r = 2*i + 2; // right = 2*i + 2 

	if (l < n && arr[l] > arr[largest]) 
		largest = l; 

	if (r < n && arr[r] > arr[largest]) 
		largest = r; 

	if (largest != i) 
	{ 
        int t = arr[i];
        arr[i] = arr[largest];
        arr[largest] = t;
		heapify(arr, n, largest); 
	} 
} 

// main function to do heap sort 
void heapSort(int arr[], int n) 
{ 
	// Build heap (rearrange array) 
	for (int i = n / 2 - 1; i >= 0; i--) 
		heapify(arr, n, i); 

	// One by one extract an element from heap 
	for (int i=n-1; i>=0; i--) 
	{ 
		// Move current root to end 
        int t = arr[0];
        arr[0] = arr[i];
        arr[i] = t;

		// call max heapify on the reduced heap 
		heapify(arr, i, 0); 
	} 
} 

/* A utility function to print array of size n */
void printArray(int arr[], int n) 
{ 
	for (int i=0; i<n; ++i) 
		printf("%d ", arr[i]);
	printf("\n");
} 

// Driver program 
int main() 
{ 
	int arr[] = {5,4,3,2,1}; 
	int n = sizeof(arr)/sizeof(arr[0]); 

	heapSort(arr, n); 
	printArray(arr, n); 
} 
