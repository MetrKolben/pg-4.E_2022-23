import java.util.ArrayList;
import java.util.List;

public class Queue<E> {
    private final List<E> queue = new ArrayList<>();

    public static void main(String[] args) {
        Queue<String> q = new Queue<>();
        System.out.println(q.isEmpty());
        q.add("Hello");
        System.out.println(q.isEmpty());
        for (int i = 0; i < 10; i++) {
            q.add("element #" + i);
        }

        System.out.println("start of observing");
        while (!q.isEmpty()) {
            System.out.println("something...");
            if (Math.random() < 0.1) {
                q.remove(0);
            }
        }
        System.out.println("done polling");
        System.out.println(q.isEmpty());
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void add(E e) {
        queue.add(e);
    }

    public void remove(int i) {
        queue.remove(i);
    }
}
