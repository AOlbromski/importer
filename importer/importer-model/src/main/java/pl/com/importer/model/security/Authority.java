package pl.com.importer.model.security;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import pl.com.importer.model.ExchangeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * An authority (a security role) used by Spring Security.
 */
@Entity
@Table(name = "T_AUTHORITY", schema = "security")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Authority implements Serializable, ExchangeEntity<Authority> {

    private static final long serialVersionUID = -8120816833750222829L;

    @NotNull
    @Size(min = 0, max = 50)
    @Id
    @Column(length = 50)
    @Setter(AccessLevel.NONE)
    private String name;
}