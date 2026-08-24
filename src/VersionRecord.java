public class VersionRecord {
    String fileName;
    int sizeKb;
    long uploadTime;
    int version;
    VersionRecord next;

    public VersionRecord(String fileName, int sizeKb, long uploadTime, int version) {
        this.fileName = fileName;
        this.sizeKb = sizeKb;
        this.uploadTime = uploadTime;
        this.version = version;
        this.next = null;
    }
}