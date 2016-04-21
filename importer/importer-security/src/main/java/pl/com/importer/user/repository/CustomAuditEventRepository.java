package pl.com.importer.user.repository;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.com.importer.config.security.auditor.AuditEventConverter;
import pl.com.importer.model.security.PersistentAuditEvent;

import java.util.Date;
import java.util.List;


/**
 * Wraps an implementation of Spring Boot's AuditEventRepository.
 */
@Repository
public class CustomAuditEventRepository {

    @Autowired
    private PersistenceAuditEventRepository persistenceAuditEventRepository;

    @Bean
    public AuditEventRepository auditEventRepository() {
        return new AuditEventRepository() {

            @Autowired
            private AuditEventConverter auditEventConverter;

            @Override
            public List<AuditEvent> find(String principal, Date after) {
                Iterable<PersistentAuditEvent> persistentAuditEvents;
                if (principal == null && after == null) {
                    persistentAuditEvents = persistenceAuditEventRepository.findAll();
                } else if (after == null) {
                    persistentAuditEvents =
                            persistenceAuditEventRepository.findByPrincipal(principal);
                } else {
                    persistentAuditEvents = persistenceAuditEventRepository
                            .findByPrincipalAndAuditEventDateAfter(principal,
                                                                   new LocalDateTime(after));
                }
                return auditEventConverter.convertToAuditEvent(persistentAuditEvents);
            }

            @Override
            @Transactional(propagation = Propagation.REQUIRES_NEW)
            public void add(AuditEvent event) {

                persistenceAuditEventRepository
                        .save(new PersistentAuditEvent(null, event.getPrincipal(),
                                                       new LocalDateTime(event.getTimestamp()),
                                                       event.getType(), auditEventConverter
                                                               .convertDataToStrings(
                                                                       event.getData())));
            }
        };
    }
}
