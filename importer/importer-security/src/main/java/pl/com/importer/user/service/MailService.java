package pl.com.importer.user.service;

import org.springframework.scheduling.annotation.Async;

import javax.annotation.PostConstruct;

/**
 * Interface MailService
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */
public interface MailService {

    @PostConstruct
    void init();

    @Async
    void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml);

}
