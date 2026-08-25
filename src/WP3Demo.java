public class WP3Demo {
    public static void main(String[] args) {
        // Test data exactly matching the output in the photo
        Submission[] submissions = {
                new Submission("S-0001", "file1.pdf", 1150, 80000800L, 1, false),
                new Submission("S-0002", "file2.pdf", 1000, 80000900L, 1, true),
                new Submission("S-0003", "file3.pdf", 1200, 80000200L, 1, false),
                new Submission("S-0004", "file4.pdf", 1300, 80000700L, 1, false),
                new Submission("S-0005", "file5.pdf", 1400, 80000300L, 1, true),
                new Submission("S-0006", "file6.pdf", 1500, 80000400L, 1, false),
                new Submission("S-0007", "file7.pdf", 1600, 80000100L, 1, false),
                new Submission("S-0008", "file8.pdf", 1700, 80000600L, 1, false)
        };

        // 1. Naive Dispatcher Test
        System.out.println("---WP-3 Naive Dispatcher");
        NaiveDispatcher naive = new NaiveDispatcher(10);
        for (int i = 0; i < submissions.length; i++) {
            naive.submit(submissions[i]);
        }
        for (int i = 0; i < submissions.length; i++) {
            Submission s = naive.next();
            if (s != null) {
                printSubmission(s);
            }
        }
        System.out.println();

        // 2. Heap Dispatcher Test
        System.out.println("---WP-3 Heap Dispatcher");
        HeapDispatcher heap = new HeapDispatcher(10);
        for (int i = 0; i < submissions.length; i++) {
            heap.submit(submissions[i]);
        }
        for (int i = 0; i < submissions.length; i++) {
            Submission s = heap.next();
            if (s != null) {
                printSubmission(s);
            }
        }
        System.out.println();

        // 3. Load Burst (Toplu Yükleme - insertAll) Test
        System.out.println("---WP-3 Load Burst");
        HeapDispatcher burstHeap = new HeapDispatcher(10);
        burstHeap.insertAll(submissions); // Testing the O(n) heap build method
        for (int i = 0; i < submissions.length; i++) {
            Submission s = burstHeap.next();
            if (s != null) {
                printSubmission(s);
            }
        }
    }

    private static void printSubmission(Submission s) {
        if (s != null) {
            System.out.printf("%s | Accomodation Flag: %b | Timestamp MS: %d\n",
                    s.getStudentId(), s.hasAccommodation(), s.getUploadTime());
        }
    }
}