package fr.adriencasier.core.donation;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "donation", schema = "dgm")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Donation extends PanacheEntityBase {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Getter
    @Column(name = "external_id", nullable = false, updatable = false)
    private Long externalId;

    @Column(nullable = false)
    public String donor;

    @Column(nullable = false)
    public String receiver;

    @Column(nullable = false)
    public String comment;

    @Column(name = "created_at", nullable = false)
    public Date createdAt;

    @Column(nullable = false)
    public Float amount;

    @Column(nullable = false)
    public String currency;
}
