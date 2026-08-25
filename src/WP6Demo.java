public class WP6Demo {
    public static void main(String[] args) {
        System.out.println("---WP-6 Sorting and Binary Search");
        ReportService report = new ReportService();

        Submission student1 = new Submission("S-0001", "file1.pdf", 512, 80000800L, 1, false);
        Submission student2 = new Submission("S-0002", "file2.pdf", 2048, 80000900L, 1, true);
        Submission student3 = new Submission("S-0003", "file3.pdf", 1024, 80000200L, 1, false);

        Submission[] submissions = { student1, student2, student3 };

        System.out.println("-> Sorting by Size :");
        report.bubbleSortBySize(submissions, 3);
        for (Submission student : submissions) {
            System.out.printf("%s | Size: %d KB | Timestamp MS: %d\n",
                    student.getStudentId(), student.getSizeKb(), student.getUploadTime());
        }

        // Binary search icin dizinin id gore sirali yaptım
        Submission[] sortedById = { submissions[0], submissions[2], submissions[1] };

        System.out.println("\n-> Binary Search for S-0003:");
        Submission found = report.findStudentBinary(sortedById, 3, "S-0003");
        if (found != null) {
            System.out.println("Found:");
            printSubmission(found);
        }
    }

    private static void printSubmission(Submission student) {
        if (student != null) {
            System.out.printf("%s | Accomodation Flag: %b | Timestamp MS: %d\n",
                    student.getStudentId(), student.hasAccommodation(), student.getUploadTime());
        }
    }
}