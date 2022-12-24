package fr.adriencasier.core.user;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import lombok.Getter;

import javax.persistence.*;

public abstract class UserConnections extends PanacheEntityBase {

    @Id
    @Getter
    @Column(nullable = false)
    String id;

    @Getter
    @Column(name = "dgm_user", nullable = false)
    @ManyToOne(targetEntity = User.class, optional = false, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    Long user;

    @Getter
    @Column(nullable = false)
    String username;
}
