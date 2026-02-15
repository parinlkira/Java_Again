package assignments;

import java.util.ArrayList;
import java.util.List;

// Subscriber interface
interface Subscriber {
    void update(String message);
}

// Publisher interface
interface Publisher {
    void subscribe(Subscriber s);
    void unsubscribe(Subscriber s);
    void notifySubscribers(String message);
}

// Concrete Publisher
class SimplePublisher implements Publisher {
    private List<Subscriber> subscribers = new ArrayList<>();

    @Override
    public void subscribe(Subscriber s) {
        subscribers.add(s);
    }

    @Override
    public void unsubscribe(Subscriber s) {
        subscribers.remove(s);
    }

    @Override
    public void notifySubscribers(String message) {
        for (Subscriber s : subscribers) {
            s.update(message);
        }
    }

    public void publish(String message) {
        System.out.println("Publisher: " + message);
        notifySubscribers(message);
    }
}

// Concrete Subscriber
class SimpleSubscriber implements Subscriber {
    private String name;

    public SimpleSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}

// Demo class
public class PublisherSubscriberDemo {
    public static void main(String[] args) {
        SimplePublisher publisher = new SimplePublisher();
        SimpleSubscriber sub1 = new SimpleSubscriber("Subscriber 1");
        SimpleSubscriber sub2 = new SimpleSubscriber("Subscriber 2");

        publisher.subscribe(sub1);
        publisher.subscribe(sub2);

        publisher.publish("Hello Subscribers!");
        publisher.publish("Observer Pattern in action.");

        publisher.unsubscribe(sub1);
        publisher.publish("Only Subscriber 2 should receive this.");
    }
}
