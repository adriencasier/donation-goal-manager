package fr.adriencasier.core.user;

import io.quarkus.hibernate.reactive.panache.PanacheQuery;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;

import javax.inject.Singleton;

@Singleton
public class UserRepository implements PanacheRepositoryBase<User, Long> {

    public PanacheQuery<User> findByUsername(String username) {
        return find("username", username);
    }

}
