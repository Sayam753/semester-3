#include<stdio.h>
void merge(int a[][2], int left_index, int middle_index, int right_index)
{
	int n1 = middle_index - left_index + 1;
	int n2 = right_index - middle_index;
	int left_part[n1][2], right_part[n2][2];
	// copy the left part
	for(int i=0;i<n1;i++)
	{
		left_part[i][0] = a[i + left_index][0];
		left_part[i][1] = a[i + left_index][1];
	}
	// copy the right part
	for(int i=0;i<n2;i++)
	{
		right_part[i][0] = a[middle_index + 1 + i][0];
		right_part[i][1] = a[middle_index + 1 + i][1];
    }
	// compare values in i, j and fill in final array
	int i=0, j=0, k=left_index;
	while(i<n1 && j<n2)
	{
		if (left_part[i][0] < right_part[j][0])
		{
			a[k][0] = left_part[i][0];
			a[k][1] = left_part[i][1];
			i++;
		}
		else
		{
			a[k][0] = right_part[j][0];
			a[k][1] = right_part[j][1];
			j++;
		}	
		k++;
	}
	// if some elements are left
	while(i<n1)
	{
		a[k][0] = left_part[i][0];
		a[k][1] = left_part[i][1];
		i++;
		k++;
	}
	while(j<n2)
	{
		a[k][0] = right_part[j][0];
		a[k][1] = right_part[j][1];
		j++;
		k++;
	}	
    
}

void merge_sort(int a[][2], int left_index, int right_index)
{
    if (left_index < right_index)
    {
        int middle_index = left_index + (right_index - left_index)/2;
        merge_sort(a, left_index, middle_index); // left half 
        merge_sort(a, middle_index + 1, right_index); //right half
        merge(a, left_index, middle_index, right_index); // merge
    }
}

int main()
{
    int no_of_users;
    printf("Enter the no of users: ");
    scanf("%d", &no_of_users);
    printf("Enter entry & leaving time of %d users:\n",no_of_users);
    int users[no_of_users][2];
    for(int i = 0; i < no_of_users; i++)
    {
        scanf("%d %d", &users[i][0], &users[i][1]);
    }
    merge_sort(users, 0, no_of_users-1);
    int count = 0;
    for(int i=0;i<no_of_users;i++)
    {
        int leaving_time = users[i][1];
        int low = 0, high = no_of_users-1, mid;
        while (low < high)
        {
            mid = (low + high)/2;
            if (users[mid][0] == leaving_time)
            {
                break;
            }
            else if (users[mid][0] > leaving_time)
            {
                high = mid;
            }
            else
            {
                low = mid + 1;
            }
        }
		int position = ( high + low )/2;
        if (leaving_time > users[position][0]) 
		{
			//to insert at the last position
			position += 1;
		}
		count += (position - i -1);
    }
	printf("No of pairs of users web surfing at the same time are %d.\n", count);
}