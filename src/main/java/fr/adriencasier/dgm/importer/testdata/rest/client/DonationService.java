package fr.adriencasier.dgm.importer.testdata.rest.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Produces("application/json")
@Path("/donation")
@RegisterRestClient(configKey="test-data")
public interface DonationService {

    @GET
    List<Donation> getAll();
}
