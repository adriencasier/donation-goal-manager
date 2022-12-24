package fr.adriencasier.core.user;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;

import javax.inject.Singleton;

@Singleton
public class UserConnectionsRepository implements PanacheRepositoryBase<UserConnections, Long> {

}
