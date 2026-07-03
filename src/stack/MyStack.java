package stack;

import java.util.Stack;
@SuppressWarnings("unchecked")

public class MyStack<T> {
    Object[]data;
    int top;

    //creating a stack
    public MyStack()
    {
        data = new Object[10];
        top = -1;
    }

    //method to push element to the stack
    public void push(T element)
    {
        if(top == data.length-1) {
            grow();
        }
        data[++top] = element;
    }

    //method to check if stack is empty
    public boolean isEmpty()
    {
        return top == -1;
    }


    //method to delete the element from the stack
    public T pop()
    {
        if(isEmpty())
        {
            return null;
        }
        T element = (T)data[top];
        data[top--] =  null;
        return element;
    }

    //method to look at the top element
    public T peek(){
        if(isEmpty())
        {
            return null;
        }
        return (T)data[top];
    }

    //method to return the size of the stack
    public int size()
    {
        return top+1;
    }

    //method to clear the stack
    public void clear(){
        data = new Object[10];
        top = -1;
    }

    //method to increase the size of the stack
    private void grow() {
        Object[] newArray = new Object[data.length*2];
        System.arraycopy(data, 0, newArray, 0, data.length);
        data = newArray;
    }
}