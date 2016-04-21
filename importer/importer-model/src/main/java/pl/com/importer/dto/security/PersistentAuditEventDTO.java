package pl.com.importer.dto.security;

import lombok.*;
import org.joda.time.LocalDateTime;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class PersistentAuditEventDTO
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersistentAuditEventDTO implements Serializable {

    @Setter(AccessLevel.NONE)
    private Long id;

    @Setter(AccessLevel.NONE)
    private String principal;

    @Setter(AccessLevel.NONE)
    private LocalDateTime auditEventDate;

    @Setter(AccessLevel.NONE)
    private String auditEventType;

    @Setter(AccessLevel.NONE)
    private Map<String, String> data = new HashMap<>();

}
