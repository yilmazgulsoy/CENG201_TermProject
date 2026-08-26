public class NaiveDispatcher {
    private Submission[] array;
    private int count;

    public NaiveDispatcher(int capacity) {
        array = new Submission[capacity];
        count = 0;
    }

    public void submit(Submission s) {
        if (count == array.length) {
            return;
        }
        array[count] = s;
        count++;

        for (int i = count - 1; i > 0; i--) {
            if (compare(array[i], array[i - 1]) > 0) {
                Submission temp = array[i];
                array[i] = array[i - 1];
                array[i - 1] = temp;
            } else {
                break;
            }
        }
    }

    public Submission next() {
        if (count == 0) {
            return null;
        }
        Submission best = array[0];

        for (int i = 1; i < count; i++) {
            array[i - 1] = array[i];
        }

        array[count - 1] = null;
        count--;
        return best;
    }

    private int compare(Submission s1, Submission s2) {
        boolean acc1 = s1.hasAccommodation();
        boolean acc2 = s2.hasAccommodation();

        if (acc1 == true && acc2 == false) {
            return 1;
        }
        if (acc1 == false && acc2 == true) {
            return -1;
        }

        if (s1.getUploadTime() < s2.getUploadTime()) {
            return 1;
        }
        if (s1.getUploadTime() > s2.getUploadTime()) {
            return -1;
        }
        return 0;
    }
}
//düz arrayde o(n) sürcekti heapdispactherle geç
// wp4 5 test için