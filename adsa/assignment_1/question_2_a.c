#include<stdio.h>
int main()
{
    int no_of_users;
    printf("Enter the no of users: ");
    scanf("%d", &no_of_users);
    printf("Enter the arrival and departure time of %d users:\n", no_of_users);
    int users[no_of_users][2];
    for(int i = 0; i < no_of_users; i++)
    {
        scanf("%d %d", &users[i][0], &users[i][1]);
    }
    printf("The index of users at the same time are:\n");
    for(int i = 0; i < no_of_users; i++)
    {
        for(int j = i + 1; j < no_of_users; j++)
        {
            // checking each and every pair
            if (users[i][1] >= users[j][0])
            {
                printf("%d %d\n", i + 1, j + 1);
            }
        }
    }
}
