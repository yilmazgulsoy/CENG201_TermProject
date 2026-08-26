public class RollbackService {
    private SubmissionRegistry registry;
    private VersionStack[] studentStacks;
//stack kullanarak geri alma işlemi burda
    public RollbackService(SubmissionRegistry registry, int maxStudents) {
        this.registry = registry;
        this.studentStacks = new VersionStack[maxStudents];
        for (int i = 0; i < maxStudents; i++) {
            studentStacks[i] = new VersionStack();
        }
    }

    private int getStudentIdx(String studentId) {
        return Integer.parseInt(studentId.substring(2)) - 1;
    }

    public void saveVersion(String studentId) {
        Submission s = registry.lookup(studentId);
        if (s != null) {
            int idx = getStudentIdx(studentId);
            VersionRecord record = new VersionRecord(s.getFileName(), s.getSizeKb(), s.getUploadTime(), s.getVersion());
            studentStacks[idx].push(record);
        }
    }

    public void rollback(String studentId) {
        Submission s = registry.lookup(studentId);
        if (s != null) {
            int idx = getStudentIdx(studentId);
            if (!studentStacks[idx].isEmpty()) {
                VersionRecord old = studentStacks[idx].pop();
                s.restoreFile(old.fileName, old.sizeKb, old.uploadTime, old.version);
            } else {
                System.out.println("no earlier version");
            }
        }
    }
}
//burda Ia dan destek aldım stağı geri alma işlemi