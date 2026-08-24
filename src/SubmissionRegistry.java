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

    private HashNode[] hashTable;
    private int size;
    private final double LOAD_FACTOR = 0.75;

    public SubmissionRegistry() {
        hashTable = new HashNode[16];
        size = 0;
    }

    public int findIndex(String studentId) {
        int hashKodu = studentId.hashCode();
        return Math.abs(hashKodu) % hashTable.length;
    }

    public void put(Submission s) {
        double doluluk = (double) size / hashTable.length;
        if (doluluk >= LOAD_FACTOR) {
            resize();
        }

        int idx = findIndex(s.getStudentId());
        HashNode current = hashTable[idx];

        while (current != null) {
            if (current.key.equals(s.getStudentId())) {
                current.value = s;
                return;
            }
            current = current.next;
        }

        HashNode newNode = new HashNode(s.getStudentId(), s);
        newNode.next = hashTable[idx];
        hashTable[idx] = newNode;
        size++;
    }

    public Submission lookup(String studentId) {
        int idx = findIndex(studentId);
        HashNode current = hashTable[idx];

        while (current != null) {
            if (current.key.equals(studentId)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public int updateVersion(String studentId, String fileName, int sizeKb, long uploadTime) {
        Submission s = lookup(studentId);
        if (s != null) {
            s.replaceFile(fileName, sizeKb, uploadTime);
            return s.getVersion();
        }
        return -1;
    }

    private void resize() {
        HashNode[] oldTable = hashTable;
        hashTable = new HashNode[oldTable.length * 2];
        size = 0;

        for (int i = 0; i < oldTable.length; i++) {
            HashNode node = oldTable[i];
            while (node != null) {
                put(node.value);
                node = node.next;
            }
        }
    }

    public int size() {
        return size;
    }
}