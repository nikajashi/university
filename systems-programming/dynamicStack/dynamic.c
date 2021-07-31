
#include <stdio.h>
#include <stdlib.h>

struct node
{
    int data;
    struct node *next;
};

struct node *top = NULL;

void display(); //display stack elememts
void push(int); //add elememt onto stack
void pop();     //remove element from stack

int main()
{
    int n, ch;
    do
    {
        printf("1. Push \n2. Pop\n3. Display\n0. Exit");
        printf("\nEnter Choice 0-3 : ");
        scanf("%d", &ch);
        switch (ch)
        {
            case 1:
                printf("\nEnter number ");
                scanf("%d", &n);
                push(n);
                break;
            case 2:
                pop();
                break;
            case 3:
                display();
                break;
        }
    //while input is not null    
    }while (ch != 0);
}

void push(int item)
{
    struct node *nptr = malloc(sizeof(struct node)); //allocate memory for node
    nptr->data = item;
    nptr->next = top;
    top = nptr;
}

void display()
{
    struct node *temp;
    temp = top;
    while (temp != NULL)
    {
        printf("\n%d", temp->data);
        temp = temp->next;
    }
}

void pop()
{
    if (top == NULL)
    {
        printf("\n\nStack is empty ");
    }
    else
    {
        struct node *temp;
        temp = top;
        top = top->next;
        printf("\n\n%d deleted", temp->data);
        free(temp);
    }
}
