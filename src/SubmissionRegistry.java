public class SubmissionRegistry {
    private static class HashNode {
        String key;
        Submission value;
        HashNode next;

        HashNode(String key, Submission value) {
            this.key = key;
            this.value = value;
        }
    }

    private HashNode[] table;
    private int size;
    private final double LOAD_FACTOR = 0.75;

    public SubmissionRegistry() {
        table = new HashNode[16];
        size = 0;
    }

    public int bucketIndex(String studentId) {
        return Math.abs(studentId.hashCode()) % table.length;
    }

    public void put(Submission s) {
        if ((double) size / table.length >= LOAD_FACTOR) {
            resize();
        }
        int idx = bucketIndex(s.getStudentId());
        HashNode current = table[idx];

        while (current != null) {
            if (current.key.equals(s.getStudentId())) {
                current.value = s;
                return;
            }
            current = current.next;
        }
        HashNode newNode = new HashNode(s.getStudentId(), s);
        newNode.next = table[idx];
        table[idx] = newNode;
        size++;
    }

    public Submission lookup(String studentId) {
        int idx = bucketIndex(studentId);
        HashNode current = table[idx];
        while (current != null) {
            if (current.key.equals(studentId)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public int updateVersion(String studentId, String fileName, int sizeKb, long timestampMs) {
        Submission s = lookup(studentId);
        if (s != null) {
            s.replaceFile(fileName, sizeKb, timestampMs);
            return s.getVersion();
        }
        return -1;
    }

    private void resize() {
        HashNode[] oldTable = table;
        table = new HashNode[oldTable.length * 2];
        size = 0;
        for (HashNode node : oldTable) {
            while (node != null) {
                put(node.value);
                node = node.next;
            }
        }
    }

    public int size() { return size; }
}