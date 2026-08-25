public class WP7Demo {
    public static void main(String[] args) {
        System.out.println("---WP-7 System Test");

        ScenarioGenerator generator = new ScenarioGenerator(42L);


        Submission[] subs = new Submission[100];
        for (int i = 0; i < 100; i++) {
            subs[i] = generator.nextUpload(i);
        }


        CircularUploadQueue queue = new CircularUploadQueue(100);
        HeapDispatcher dispatcher = new HeapDispatcher(100);
        SubmissionRegistry registry = new SubmissionRegistry();
        SubmissionTimeline timeline = new SubmissionTimeline();

        System.out.println("-> Step 1: Enqueue");
        for (int i = 0; i < subs.length; i++) {
            if (subs[i] != null) {
                queue.enqueue(subs[i]);
            }
        }

        System.out.println("-> Step 2: Queue to Dispatcher");
        while (queue.size() > 0) {
            dispatcher.submit(queue.dequeue());
        }

        System.out.println("-> Step 3: Processing (Registry & BST)");
        while (true) {
            Submission s = dispatcher.next();
            if (s == null) {
                break; // bitti
            }

            registry.put(s);
            timeline.addSubmission(s);

            printSubmission(s);
        }

        System.out.println("\nDone.");
    }

    private static void printSubmission(Submission s) {
        if (s != null) {
            System.out.printf("%s | Accomodation Flag: %b | Timestamp MS: %d\n",
                    s.getStudentId(), s.hasAccommodation(), s.getUploadTime());
        }
    }
}