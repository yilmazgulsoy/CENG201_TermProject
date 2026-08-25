public class WP1Demo {
    public static void main(String[] args) {
        System.out.println("---WP-1 Hash Table Registry");
        SubmissionRegistry registry = new SubmissionRegistry();

        Submission s1 = new Submission("S-0001", "file1.pdf", 1150, 80000800L, 1, false);
        registry.put(s1);

        System.out.println("Looking up S-0001 after insertion:");
        Submission found = registry.lookup("S-0001");
        printSubmission(found);

        System.out.println("\nUpdating S-0001 with new file...");
        registry.updateVersion("S-0001", "file1_v2.pdf", 2048, 80001200L);
        found = registry.lookup("S-0001");
        printSubmission(found);
        System.out.println("Updated File Name: " + found.getFileName() + " | Version: " + found.getVersion());
    }

    private static void printSubmission(Submission s) {
        if (s != null) {
            System.out.printf("%s | Accomodation Flag: %b | Timestamp MS: %d\n",
                    s.getStudentId(), s.hasAccommodation(), s.getUploadTime());
        }
    }
}