#include <stdio.h>
#include <stdlib.h>

void separate(int* a, int length)
{
    for(int i = 0; i <= (length / 2); i++) {
        for(int j = (length / 2); j < length; j++) {
            if(a[i] < 0) break;

            if(a[i] >= 0 && a[j] < 0) {
                int temp = a[j];
                a[i] = a[j];
                a[j] = temp;
            }
        }
    }
}

void printArray(int* a, int length) 
{
    for(int i = 0; i < length; i++) {
        printf("[%d]", a[i]);
    }
    printf("\n");
}

int main(int argc, char const *argv[])
{
    int array[] = {-1, -1, 0, 1, -1, -1, 0, 1};
    int length = sizeof(array) / 4;
    
    printf("Length: %d\n", length);
    printf("Array: ");
    printArray(array, length);

    printf("Separate: ");
    separate(array, length);
    printArray(array, length);

    return 0;
}
