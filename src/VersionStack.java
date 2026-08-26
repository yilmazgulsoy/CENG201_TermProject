import java.util.NoSuchElementException;
// üstteki importu aI dan hata veriyodu
// lıfo kullandık güncelleme için
public class VersionStack {
    private VersionRecord top;

    public VersionStack() {
        this.top = null;
    }

    public void push(VersionRecord v) {
        v.next = top;
        top = v;
    }

    public VersionRecord pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("No earlier version");
        }
        VersionRecord popped = top;
        top = top.next;
        return popped;
    }

    public boolean isEmpty() {
        return top == null;
    }
}