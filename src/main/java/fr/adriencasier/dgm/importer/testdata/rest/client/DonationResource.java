package fr.adriencasier.dgm.importer.testdata.rest.client;

import fr.adriencasier.core.donation.Donation;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static fr.adriencasier.core.tasks.DonationStack.add;
import static fr.adriencasier.core.tasks.DonationStack.addAll;

@Path("/testdata/donation")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DonationResource {

    @Inject
    @RestClient
    DonationService service;

    @POST
    @Path("/import/all")
    public void runImporter() {
        addAll(service.getAll().stream()
                .map(d -> Donation.builder()
                        .externalId(Long.valueOf(d.getDonationId()))
                        .donor(d.getName())
                        .receiver(d.getMemberId())
                        .createdAt(d.getCreatedAt())
                        .currency(d.getCurrency())
                        .comment(d.getMessage())
                        .amount(d.getAmount())
                        .build()
                )
                .toList());
    }

    @POST
    @Path("/add")
    public void importSingle(fr.adriencasier.dgm.importer.testdata.rest.client.Donation d) {
        add(Donation.builder()
                .externalId(Long.valueOf(d.getDonationId()))
                .donor(d.getName())
                .receiver(d.getMemberId())
                .createdAt(d.getCreatedAt())
                .currency(d.getCurrency())
                .comment(d.getMessage())
                .amount(d.getAmount())
                .build()
        );
    }
}
