public class NaiveDispatcher {
    private Submission[] array;
    private int count;

    public NaiveDispatcher(int capacity) {
        array = new Submission[capacity];
        count = 0;
    }

    public void submit(Submission s) {
        if (count == array.length) return;
        array[count] = s;
        count++;
        // O(n) Insertion Sort mantığı ile sürekli sıralı tutuyoruz
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
        if (count == 0) return null;
        Submission best = array[0];
        for (int i = 1; i < count; i++) {
            array[i - 1] = array[i];
        }
        array[count - 1] = null;
        count--;
        return best;
    }

    private int compare(Submission s1, Submission s2) {
        if (s1.hasAccommodation() && !s2.hasAccommodation()) return 1;
        if (!s1.hasAccommodation() && s2.hasAccommodation()) return -1;
        // Erken olan daha büyük önceliğe sahip
        if (s1.getTimestampMs() < s2.getTimestampMs()) return 1;
        if (s1.getTimestampMs() > s2.getTimestampMs()) return -1;
        return 0;
    }
}