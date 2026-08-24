public class CircularUploadQueue {
    private Submission[] array;
    private int front;
    private int rear;
    private int count;

    public CircularUploadQueue(int capacity) {
        array = new Submission[capacity];
        front = 0;
        rear = 0;
        count = 0;
    }

    public boolean enqueue(Submission s) {
        if (count == array.length) return false;
        array[rear] = s;
        rear = (rear + 1) % array.length;
        count++;
        return true;
    }

    public Submission dequeue() {
        if (count == 0) return null;
        Submission s = array[front];
        array[front] = null;
        front = (front + 1) % array.length;
        count--;
        return s;
    }

    public int size() { return count; }
}