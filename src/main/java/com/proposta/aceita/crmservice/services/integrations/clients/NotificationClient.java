package com.proposta.aceita.crmservice.services.integrations.clients;

import com.proposta.aceita.crmservice.entities.req.NegotiationRequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "${services.notification.name}", url = "${services.notification.url}", decode404 = true)
public interface NotificationClient {

    @PostMapping("/emails/match-to-buyer")
    void sendMatchEmail(@RequestHeader("Authorization") String authHeader, @RequestBody NegotiationRequestBody body);

    @PutMapping("/deal")
    void dealEmail(@RequestHeader("Authorization") String authHeader, @RequestBody NegotiationRequestBody body);
}
