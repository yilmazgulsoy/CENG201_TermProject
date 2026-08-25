import java.util.ArrayList;
import java.util.List;

public class PlainSubmissionBST {

    // Agacin her bir dugumu
    private class Node {
        Submission data;
        Node left;
        Node right;
        int height;

        Node(Submission data) {
            this.data = data;
            this.height = 1;
        }
    }

    private Node root;

    public PlainSubmissionBST() {
        this.root = null;
    }

    public void insert(Submission s) {
        root = insertRecursive(root, s);
    }

    private Node insertRecursive(Node node, Submission s) {
        if (node == null) {
            return new Node(s);
        }

        // Yuklenme zamanina gore kiyasliyoruz
        if (s.getUploadTime() < node.data.getUploadTime()) {
            node.left = insertRecursive(node.left, s);
        } else if (s.getUploadTime() > node.data.getUploadTime()) {
            node.right = insertRecursive(node.right, s);
        } else {
            // Ayni zamandaysa ekleme yapmaya gerek yok
            return node;
        }

        // Yuksekligi guncelle
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // Denge durumunu kontrol et
        int balance = getBalance(node);

        // Sol Sol durumu
        if (balance > 1 && s.getUploadTime() < node.left.data.getUploadTime()) {
            return rightRotate(node);
        }
        // Sag Sag durumu
        if (balance < -1 && s.getUploadTime() > node.right.data.getUploadTime()) {
            return leftRotate(node);
        }
        // Sol Sag durumu
        if (balance > 1 && s.getUploadTime() > node.left.data.getUploadTime()) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // Sag Sol durumu
        if (balance < -1 && s.getUploadTime() < node.right.data.getUploadTime()) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private int getHeight(Node node) {
        if (node == null) return 0;
        return node.height;
    }

    private int getBalance(Node node) {
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Yonleri degistir
        x.right = y;
        y.left = T2;

        // Yukseklikleri tekrar hesapla
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Yonler değiştidrik
        y.left = x;
        x.right = T2;

        // Yukseklikleri tekrar heasapla
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    // bir zaman araligindaki ödevi bulma
    public List<Submission> submittedBetween(long start, long end) {
        List<Submission> resultList = new ArrayList<>();
        findTimeRange(root, start, end, resultList);
        return resultList;
    }

    // aralik taramasi yaptık burda
    private void findTimeRange(Node node, long start, long end, List<Submission> list) {
        if (node == null) {
            return;
        }

        if (start < node.data.getUploadTime()) {
            findTimeRange(node.left, start, end, list);
        }

        if (node.data.getUploadTime() >= start && node.data.getUploadTime() <= end) {
            list.add(node.data);
        }

        if (end > node.data.getUploadTime()) {
            findTimeRange(node.right, start, end, list);
        }
    }
}