
/*
 * Class for implement the Queue using Static Array
 */
public class Queue<T> {
    int size;
    int front = -1;
    int rear = -1;
    T[] list;

    /**
     * Constructor to initialize program details.
     * 
     * @param size size of the Queue.
     */
    public Queue(int size) {
        this.size = size;
        this.list = (T[]) new Object[size];
    }

    /*
     * Check that Queue is empty or not.
     */
    public boolean isEmpty() {
        return rear == -1;
    }

    /*
     * Check that Queue is full or not.
     */
    public boolean isFull() {
        return rear == size - 1;
    }

    /*
     * add the element in the Queue
     */
    public void add(T data) {
        if (isFull()) {
            System.out.println("The queue is already full");
            return;
        }
        if (front == -1) {
            front = 0;
        }
        list[++rear] = data;

    }

    /*
     * Remove the element from the Queue
     */
    public T remove() {
        if (isEmpty()) {
            System.out.println("the queue is empty");
            return null;
        }
        T result = list[front];
        if (front == rear) {
            front = rear = -1;
        } else {
            front++;
        }
        return result;
    }

    /*
     * Return the top element of the Queue
     */
    public T peek() {
        return list[front];
    }

    /*
     * Display the Queue
     */
    public void dispaly() {
        if (isEmpty()) {
            System.out.println("the list is empty");
        }
        System.out.println("List is : ");
        for (int i = front; i <= rear; i++) {
            System.out.print(list[i] + "  ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>(5);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        queue.add(6);
        queue.dispaly();
        queue.add(7);
        queue.dispaly();
    }
}
