public class Submission {
    public static final long DEADLINE_MS = 86_340_000L;
    private final String studentId;
    private String fileName;
    private int sizeKb;
    private long uploadTime;
    private int version;
    private final boolean accommodationFlag;

    public Submission(String studentId, String fileName, int sizeKb,
                      long uploadTime, int version, boolean accommodationFlag) {
        this.studentId = studentId;
        this.fileName = fileName;
        this.sizeKb = sizeKb;
        this.uploadTime = uploadTime;
        this.version = version;
        this.accommodationFlag = accommodationFlag;
    }

    public String getStudentId() { return studentId; }
    public String getFileName() { return fileName; }
    public int getSizeKb() { return sizeKb; }
    public long getUploadTime() { return uploadTime; }
    public int getVersion() { return version; }
    public boolean hasAccommodation() { return accommodationFlag; }

    public void replaceFile(String fileName, int sizeKb, long uploadTime) {
        this.fileName = fileName;
        this.sizeKb = sizeKb;
        this.uploadTime = uploadTime;
        this.version++;
    }

    public void restoreFile(String fileName, int sizeKb, long uploadTime, int version) {
        this.fileName = fileName;
        this.sizeKb = sizeKb;
        this.uploadTime = uploadTime;
        this.version = version;
    }

    public boolean overTime() {
        if (uploadTime > DEADLINE_MS) {
            return true;
        } else {
            return false;
        }
    }

    public String clock() {
        return String.format("%02d:%02d:%02d.%03d",
                uploadTime / 3_600_000, (uploadTime / 60_000) % 60,
                (uploadTime / 1_000) % 60, uploadTime % 1_000);
    }

    @Override
    public String toString() {
        String accStr = "";
        if (accommodationFlag == true) {
            accStr = " [ACC]";
        }

        String lateStr = "";
        if (overTime() == true) {
            lateStr = " LATE";
        }

        return String.format("%s v%d %-22s %5d KB %s%s%s",
                studentId, version, fileName, sizeKb, clock(),
                accStr, lateStr);
    }
}
//her bir ödevi burda tuttuk
