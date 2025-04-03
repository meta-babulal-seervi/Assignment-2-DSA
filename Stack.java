public class Stack<T> {

    T[] list;
    int size;
    int peek = -1;

    /**
     * Constructor to initialize program details.
     * 
     * @param size size of the Stack.
     */
    public Stack(int size) {
        this.size = size;
        this.list = (T[]) new Object[size];
    }

    /*
     * Check that Stack is full or not.
     */
    public boolean isFull() {
        return peek == size - 1 ? true : false;
    }

    /*
     * Check that Stack is empty or not.
     */
    public boolean isEmpty() {
        return peek == -1 ? true : false;
    }

    /*
     * add the element in the Stack
     */
    public void push(T data) {
        if (!isFull()) {
            list[++peek] = data;
        } else {
            System.out.println("List is Already full");
        }
    }

    /*
     * Remove the element from the Stack
     */
    public T pop() {
        if (isEmpty()) {
            System.out.println("the list is empty");
            return null;
        }
        return list[peek--];
    }

    /*
     * Return the top element of the Stack
     */
    public T peek() {
        return list[peek];
    }

    /*
     * Display the stack
     */
    public void dispaly() {
        System.out.println("the list is : ");
        for (int i = 0; i <= peek; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>(5);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.dispaly();
        stack.push(9);
        stack.pop();
        stack.dispaly();
        System.out.println("Peek element is " + stack.peek());
        ;
    }
}