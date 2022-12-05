import java.util.ArrayList;
import java.util.List;

public class Queue<E> {
    private final List<E> queue = new ArrayList<>();
    private final List<Listener> listeners = new ArrayList<>();

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

        q.add("World");
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(3000);
                q.remove(0);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t.start();
        q.addListener(() -> {
            System.out.println(q.isEmpty());
            System.out.println("done observer");
        });
        for (int i = 0; i < 100; i++) {
            System.out.println("do something");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("done");
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    public synchronized void add(E e) {
        queue.add(e);
    }

    public synchronized E remove(int i) {
        E e = queue.remove(i);
        if (queue.isEmpty()) {
            System.out.println("Calling on empty()");
            listeners.forEach(Listener::onEmpty);
        }
        return e;
    }

    public void addListener(Listener l) {
        listeners.add(l);
    }

    interface Listener {
        void onEmpty();
    }
}
