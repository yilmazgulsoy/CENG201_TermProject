public class NaiveUploadQueue {
    private Submission[] array;
    private int count;

    public NaiveUploadQueue(int capacity) {
        array = new Submission[capacity];
        count = 0;
    }

    public boolean enqueue(Submission s) {
        if (count == array.length) {
            return false;
        }
        array[count] = s;
        count++;
        return true;
    }

    public Submission dequeue() {
        if (count == 0) {
            return null;
        }
        Submission first = array[0];

        for (int i = 1; i < count; i++) {
            array[i - 1] = array[i];
        }

        array[count - 1] = null;
        count--;
        return first;
    }

    public int size() {
        return count;
    }
}