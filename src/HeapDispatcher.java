public class HeapDispatcher {
    private Submission[] heap;
    private int size;

    public HeapDispatcher(int capacity) {
        heap = new Submission[capacity];
        size = 0;
    }

    public void submit(Submission s) {
        if (size == heap.length) return;
        heap[size] = s;
        siftUp(size);
        size++;
    }

    public Submission next() {
        if (size == 0) return null;
        Submission max = heap[0];
        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;
        if (size > 0) siftDown(0);
        return max;
    }

    public void loadBurst(Submission[] burst) {
        for (int i = 0; i < burst.length; i++) {
            if (burst[i] != null && size < heap.length) {
                heap[size++] = burst[i];
            }
        }
        for (int i = (size / 2) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (compare(heap[index], heap[parent]) > 0) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void siftDown(int index) {
        while (2 * index + 1 < size) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int largest = index;

            if (left < size && compare(heap[left], heap[largest]) > 0) {
                largest = left;
            }
            if (right < size && compare(heap[right], heap[largest]) > 0) {
                largest = right;
            }
            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        Submission temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private int compare(Submission s1, Submission s2) {
        if (s1.hasAccommodation() && !s2.hasAccommodation()) return 1;
        if (!s1.hasAccommodation() && s2.hasAccommodation()) return -1;
        if (s1.getTimestampMs() < s2.getTimestampMs()) return 1;
        if (s1.getTimestampMs() > s2.getTimestampMs()) return -1;
        return 0;
    }}