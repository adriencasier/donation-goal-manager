package fr.adriencasier.core.donation;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;

import javax.inject.Singleton;

@Singleton
public class DonationRepository implements PanacheRepositoryBase<Donation, Long> {
}
