package com.proposta.aceita.crmservice.services.integrations;

import com.proposta.aceita.crmservice.entities.req.NegotiationRequestBody;
import com.proposta.aceita.crmservice.services.integrations.clients.NotificationClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.annotation.PostConstruct;

@Service
public class NotificationService extends IntegrationService {

    @Value("${services.notification.username}")
    private String username;

    @Value("${services.notification.password}")
    private String password;

    private final NotificationClient notificationClient;

    private String authHeader;

    public NotificationService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    @PostConstruct
    void postConstruct() {
        final String auth = username + ":" + password;
        authHeader = "Basic " + Base64Utils.encodeToString(auth.getBytes());
    }

    public void sendMatchEmail(NegotiationRequestBody body) {
        notificationClient.sendMatchEmail(authHeader, body);
    }

    public void dealEmail(NegotiationRequestBody body) {
        notificationClient.sendMatchEmail(authHeader, body);
    }
}