public class HeapDispatcher {
    private Submission[] maxHeap;
    private int size;

    public HeapDispatcher(int capacity) {
        maxHeap = new Submission[capacity];
        size = 0;
    }

    public void submit(Submission s) {
        if (size == maxHeap.length) {
            return;
        }
        maxHeap[size] = s;
        heapUp(size);
        size++;
    }

    public Submission next() {
        if (size == 0) {
            return null;
        }
        Submission max = maxHeap[0];
        maxHeap[0] = maxHeap[size - 1];
        maxHeap[size - 1] = null;
        size--;

        if (size > 0) {
            heapDown(0);
        }
        return max;
    }

    public void insertAll(Submission[] burst) {
        for (int i = 0; i < burst.length; i++) {
            if (burst[i] != null && size < maxHeap.length) {
                maxHeap[size] = burst[i];
                size++;
            }
        }

        int baslangic = (size / 2) - 1;
        for (int i = baslangic; i >= 0; i--) {
            heapDown(i);
        }
    }

    private void heapUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (compare(maxHeap[index], maxHeap[parent]) > 0) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void heapDown(int index) {
        while ((2 * index) + 1 < size) {
            int left = (2 * index) + 1;
            int right = (2 * index) + 2;
            int largest = index;

            if (left < size && compare(maxHeap[left], maxHeap[largest]) > 0) {
                largest = left;
            }
            if (right < size && compare(maxHeap[right], maxHeap[largest]) > 0) {
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
        Submission temp = maxHeap[i];
        maxHeap[i] = maxHeap[j];
        maxHeap[j] = temp;
    }

    private int compare(Submission s1, Submission s2) {
        boolean acc1 = s1.hasAccommodation();
        boolean acc2 = s2.hasAccommodation();
// öncelikli olanları kuyruğa yerleştirdik true çevirenleri accomun ile
        if (acc1 == true && acc2 == false) return 1;
        if (acc1 == false && acc2 == true) return -1;

        if (s1.getUploadTime() < s2.getUploadTime()) return 1;
        if (s1.getUploadTime() > s2.getUploadTime()) return -1;

        return 0;
    }
}
// önceki gibi dizide aradımız eleman o(N) di kolay bulmak için maxheap kullandık o(1) hızında daha optimal oldu
// wp 4 5 test için