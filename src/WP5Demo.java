import java.util.List;

public class WP5Demo {
    public static void main(String[] args) {
        System.out.println("---WP-5 AVL Tree Timeline");
        SubmissionTimeline timeline = new SubmissionTimeline();

        timeline.addSubmission(new Submission("S-0001", "file1.pdf", 1150, 80000800L, 1, false));
        timeline.addSubmission(new Submission("S-0002", "file2.pdf", 1000, 80000900L, 1, true));
        timeline.addSubmission(new Submission("S-0003", "file3.pdf", 1200, 80000200L, 1, false));
        timeline.addSubmission(new Submission("S-0004", "file4.pdf", 1300, 80000700L, 1, false));

        long startRange = 80000500L;
        long endRange = 80000850L;

        System.out.println("Querying submissions between " + startRange + " and " + endRange + ":");
        List<Submission> results = timeline.getSubmissionsInWindow(startRange, endRange);

        for (Submission s : results) {
            printSubmission(s);
        }
    }

    private static void printSubmission(Submission s) {
        if (s != null) {
            System.out.printf("%s | Accomodation Flag: %b | Timestamp MS: %d\n",
                    s.getStudentId(), s.hasAccommodation(), s.getUploadTime());
        }
    }
}