package fr.adriencasier.core.user;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "dgm")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends PanacheEntityBase {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Getter
    @Column(name = "username", nullable = false, updatable = false)
    private String username;

    @Getter
    @Builder.Default
    @Column(name = "pot", nullable = false)
    private Float pot = 0.0f;

    @Getter
    @Builder.Default
    @Column(name = "experimental", nullable = false)
    private Boolean experimental = false;
}
