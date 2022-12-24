package fr.adriencasier.core.user;

import io.quarkus.security.identity.SecurityIdentity;
import io.smallrye.mutiny.Uni;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Objects;

@Path("user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserRepository userRepo;

    @Inject
    UserConnectionsRepository userConRepo;

    @Inject
    SecurityIdentity identity;

    @GET
    @Path("/list")
    @RolesAllowed("admin")
    public Uni<List<User>> listUsers() {
        return userRepo.listAll();
    }

    @GET
    public Uni<User> getOwnUserInfo() {
        String username = identity.getPrincipal().getName();
        Uni<User> user = userRepo.findByUsername(username).firstResult();
        if (Objects.isNull(user)) {
            user = User.builder().username(username).build().persist();
        }
        return user;
    }

    @GET
    @Path("{username}/info")
    @RolesAllowed("admin")
    public Uni<User> getUserInfo(@PathParam("username") String username) {
        return userRepo.findByUsername(username).firstResult();
    }

    @GET
    @Path("{username}/connections")
    @RolesAllowed("admin")
    public Uni<List<UserConnections>> getUserConnections(@PathParam("username") String username) {
        return userRepo.findByUsername(username)
            .firstResult()
            .onItem()
            .transformToUni(user ->
            userConRepo.find("dgm_user", user.getId()).list()
        );
    }
}
