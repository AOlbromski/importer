package pl.com.importer.model.security;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;
import pl.com.importer.model.ExchangeEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Persist AuditEvent managed by the Spring Boot actuator.
 */
@Entity
@Table(name = "T_PERSISTENT_AUDIT_EVENT", schema = "security")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PersistentAuditEvent implements Serializable, ExchangeEntity<PersistentAuditEvent> {

    private static final long serialVersionUID = 8707943272870452496L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private String principal;

    @Column(name = "event_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    @Setter(AccessLevel.NONE)
    private LocalDateTime auditEventDate;

    @Column(name = "event_type")
    @Setter(AccessLevel.NONE)
    private String auditEventType;

    @ElementCollection
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    @CollectionTable(
            name = "T_PERSISTENT_AUDIT_EVENT_DATA",
            schema = "security",
            joinColumns = @JoinColumn(name = "event_id"))
    @Setter(AccessLevel.NONE)
    private Map<String, String> data = new HashMap<>();
}