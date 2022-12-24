package fr.adriencasier.core.tasks;

import fr.adriencasier.core.donation.Donation;
import io.quarkus.scheduler.Scheduled;
import io.vertx.core.eventbus.EventBus;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Singleton
public class DonationStack {

    @Inject
    Logger log;

    @Inject
    EventBus bus;

    private static final List<Donation> stack = new ArrayList<>();

    public static void add(Donation d) {
        stack.add(d);
    }

    public static void addAll(Collection<Donation> d) {
        stack.addAll(d);
    }

    @Scheduled(every = "10s")
    public synchronized void task() {
        int processedCount = 0;
        for (Donation d : DonationStack.stack) {
            try {
                processItem(d);
                bus.publish("donation-processed", d.getId());
                processedCount++;
                stack.remove(d);
            } catch (Exception e) {
                log.error("Cannot process donation " + d.getExternalId(), e);
            }
        }
        bus.publish("stack-processed", processedCount);
    }

    @Transactional
    private void processItem(Donation d) {
        d.persist();
    }
}
