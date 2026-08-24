public class VersionRecord {
    String fileName;
    int sizeKb;
    long timestampMs;
    int version;
    VersionRecord next;

    public VersionRecord(String fileName, int sizeKb, long timestampMs, int version) {
        this.fileName = fileName;
        this.sizeKb = sizeKb;
        this.timestampMs = timestampMs;
        this.version = version;
        this.next = null;
    }
}