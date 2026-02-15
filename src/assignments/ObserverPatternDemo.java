package assignments;

import java.util.ArrayList;
import java.util.List;

// Observer interface
interface Observer {
    void update(String message);
}

// Subject interface
interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers(String message);
}

// Concrete Subject
class NewsAgency implements Subject {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer o : observers) {
            o.update(message);
        }
    }

    public void publishNews(String news) {
        System.out.println("NewsAgency: Publishing news - " + news);
        notifyObservers(news);
    }
}

// Concrete Observer
class NewsChannel implements Observer {
    private String name;
    private String latestNews;

    public NewsChannel(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        latestNews = message;
        System.out.println(name + " received news: " + message);
    }

    public String getLatestNews() {
        return latestNews;
    }
}

// Demo class
public class ObserverPatternDemo {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();
        NewsChannel channel1 = new NewsChannel("Channel One");
        NewsChannel channel2 = new NewsChannel("Channel Two");

        agency.attach(channel1);
        agency.attach(channel2);

        agency.publishNews("Observer Pattern Implemented!");
        agency.publishNews("Breaking News: Java Example");
    }
}
