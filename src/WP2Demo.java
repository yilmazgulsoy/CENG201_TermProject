public class WP2Demo {
    public static void main(String[] args) {
        Submission[] submissions = {
                new Submission("S-0001", "file1.pdf", 1150, 80000800L, 1, false),
                new Submission("S-0002", "file2.pdf", 1000, 80000900L, 1, true),
                new Submission("S-0003", "file3.pdf", 1200, 80000200L, 1, false)
        };

        // 1. Naive Queue Test
        System.out.println("---WP-2 Naive Queue");
        NaiveUploadQueue naiveQueue = new NaiveUploadQueue(10);
        for (Submission s : submissions) {
            naiveQueue.enqueue(s);
        }
        while (naiveQueue.size() > 0) {
            printSubmission(naiveQueue.dequeue());
        }

        System.out.println("\n---WP-2 Circular Queue");
        CircularUploadQueue circularQueue = new CircularUploadQueue(10);
        for (Submission s : submissions) {
            circularQueue.enqueue(s);
        }
        while (circularQueue.size() > 0) {
            printSubmission(circularQueue.dequeue());
        }
    }

    private static void printSubmission(Submission s) {
        if (s != null) {
            System.out.printf("%s | Accomodation Flag: %b | Timestamp MS: %d\n",
                    s.getStudentId(), s.hasAccommodation(), s.getUploadTime());
        }
    }
}