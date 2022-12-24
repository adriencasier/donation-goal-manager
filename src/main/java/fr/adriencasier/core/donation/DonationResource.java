package fr.adriencasier.core.donation;

import io.smallrye.mutiny.Uni;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static fr.adriencasier.core.tasks.DonationStack.add;

@Path("donation")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DonationResource {

    @Inject
    DonationRepository repo;

    @GET
    @Path("/list")
    @RolesAllowed("admin")
    public Uni<List<Donation>> getList() {
        return repo.listAll();
    }

    @POST
    @Path("/add")
    @RolesAllowed("admin")
    public void addDonation(Donation d) {
        add(Donation.builder()
                .externalId(d.getExternalId())
                .donor(d.donor)
                .receiver(d.receiver)
                .createdAt(d.createdAt)
                .currency(d.currency)
                .comment(d.comment)
                .amount(d.amount)
                .build());
    }

    @POST
    @Path("/update")
    @RolesAllowed("admin")
    @Transactional
    public Uni<Donation> updateDonation(Donation donation) {
        return repo.findById(donation.getId(), LockModeType.PESSIMISTIC_WRITE).invoke(d -> {
                            d.comment = donation.comment;
                            d.amount = donation.amount;
                            d.currency = donation.currency;
                            d.donor = donation.donor;
                            d.receiver = donation.receiver;
                        }
                );
    }

    @DELETE
    @Path("/delete/{id}")
    @RolesAllowed("admin")
    @Transactional
    public Uni<Boolean> deleteDonation(@PathParam("id")Long id) {
        return repo.deleteById(id);
    }
}
