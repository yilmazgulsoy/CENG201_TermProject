public class RollbackService {
    private SubmissionRegistry registry;
    private VersionStack[] studentStacks;

    public RollbackService(SubmissionRegistry registry, int maxStudents) {
        this.registry = registry;
        this.studentStacks = new VersionStack[maxStudents];
        // 800 öğrenci için başlangıçta stack'leri oluşturuyoruz
        for (int i = 0; i < maxStudents; i++) {
            studentStacks[i] = new VersionStack();
        }
    }

    private int getStudentIdx(String studentId) {
        // "S-0042" gibi bir string'den indeksi çekiyoruz
        return Integer.parseInt(studentId.substring(2)) - 1;
    }

    // Yeni dosya yüklenmeden önce eskisini kaydetmek için çağrılacak
    public void saveVersion(String studentId) {
        Submission s = registry.lookup(studentId);
        if (s != null) {
            int idx = getStudentIdx(studentId);
            VersionRecord record = new VersionRecord(s.getFileName(), s.getSizeKb(), s.getTimestampMs(), s.getVersion());
            studentStacks[idx].push(record);
        }
    }

    public void rollback(String studentId) {
        Submission s = registry.lookup(studentId);
        if (s != null) {
            int idx = getStudentIdx(studentId);
            if (!studentStacks[idx].isEmpty()) {
                VersionRecord old = studentStacks[idx].pop();
                s.restoreFile(old.fileName, old.sizeKb, old.timestampMs, old.version);
            } else {
                System.out.println("no earlier version");
            }
        }
    }
}