package assignments;

import org.junit.Test;
import static org.junit.Assert.*;

public class ObserverPatternDemoTest {
    @Test
    public void testObserverReceivesUpdates() {
        NewsAgency agency = new NewsAgency();
        NewsChannel channel = new NewsChannel("Test Channel");
        agency.attach(channel);
        agency.publishNews("Test News");
        assertEquals("Test News", channel.getLatestNews());
    }

    @Test
    public void testDetachObserver() {
        NewsAgency agency = new NewsAgency();
        NewsChannel channel = new NewsChannel("Test Channel");
        agency.attach(channel);
        agency.detach(channel);
        agency.publishNews("No News");
        assertNull(channel.getLatestNews());
    }
}
