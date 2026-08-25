public class WP4Demo {
    public static void main(String[] args) {
        System.out.println("---WP-4 Rollback Service (Stack)");
        SubmissionRegistry registry = new SubmissionRegistry();
        RollbackService rollback = new RollbackService(registry, 100);

        Submission s1 = new Submission("S-0001", "hw1_v1.zip", 512, 80000100L, 1, false);
        registry.put(s1);

        System.out.println("Original Submission:");
        printSubmission(registry.lookup("S-0001"));
        System.out.println("File: " + registry.lookup("S-0001").getFileName() + "\n");

        // Save current and update
        rollback.saveVersion("S-0001");
        registry.updateVersion("S-0001", "hw1_v2.zip", 1024, 80000900L);

        System.out.println("After Update (v2):");
        printSubmission(registry.lookup("S-0001"));
        System.out.println("File: " + registry.lookup("S-0001").getFileName() + "\n");

        // Rollback
        rollback.rollback("S-0001");

        System.out.println("After Rollback (v1):");
        printSubmission(registry.lookup("S-0001"));
        System.out.println("File: " + registry.lookup("S-0001").getFileName());
    }

    private static void printSubmission(Submission s) {
        if (s != null) {
            System.out.printf("%s | Accomodation Flag: %b | Timestamp MS: %d\n",
                    s.getStudentId(), s.hasAccommodation(), s.getUploadTime());
        }
    }
}