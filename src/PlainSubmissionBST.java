import java.util.ArrayList;
import java.util.List;

public class PlainSubmissionBST {

    // bst ağacımız
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

        // Yuklenme zamanina gore kiyas
        if (s.getUploadTime() < node.data.getUploadTime()) {
            node.left = insertRecursive(node.left, s);
        } else if (s.getUploadTime() > node.data.getUploadTime()) {
            node.right = insertRecursive(node.right, s);
        } else {
            // Ayni zamandaysa ekleme yapmaya gerek yok
            return node;
        }

        // Yuksekligi guncellemek içinn
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // Denge durumu için balancı eşitledik getirdik
        int balance = getBalance(node);


        if (balance > 1 && s.getUploadTime() < node.left.data.getUploadTime()) {
            return rightRotate(node);
        }

        if (balance < -1 && s.getUploadTime() > node.right.data.getUploadTime()) {
            return leftRotate(node);
        }

        if (balance > 1 && s.getUploadTime() > node.left.data.getUploadTime()) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

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


        y.left = x;
        x.right = T2;

        // Yukseklikleri tekrar heasap
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    // bir zaman araligindaki ödevi bulmak için
    public List<Submission> submittedBetween(long start, long end) {
        List<Submission> resultList = new ArrayList<>();
        findTimeRange(root, start, end, resultList);
        return resultList;
    }

    // aralik taramasi yaptık burda node a baktık
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