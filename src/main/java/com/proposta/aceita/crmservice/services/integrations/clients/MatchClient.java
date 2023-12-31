package com.proposta.aceita.crmservice.services.integrations.clients;

import com.proposta.aceita.crmservice.entities.req.intergrations.InterestRequestBody;
import com.proposta.aceita.crmservice.entities.req.intergrations.SaleRequestBody;
import com.proposta.aceita.crmservice.entities.res.NegotiationResponseBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "${services.match.name}", url = "${services.match.url}", decode404 = true)
public interface MatchClient {

    @PostMapping("/interests")
    void saveInterest(@RequestHeader("Authorization") String authHeader, InterestRequestBody body);

    @DeleteMapping("/interests/{interestId}")
    void deleteInterest(@RequestHeader("Authorization") String authHeader, @PathVariable Integer interestId);

    @PostMapping("/sales")
    void saveSale(@RequestHeader("Authorization") String authHeader, SaleRequestBody body);

    @DeleteMapping("/sales/{saleId}")
    void deleteSale(@RequestHeader("Authorization") String authHeader, @PathVariable Integer saleId);

    @GetMapping("/negotiations/sales/{saleId}")
    Optional<List<NegotiationResponseBody>> getNegotiationsBySale(@RequestHeader("Authorization") String authHeader, @PathVariable Integer saleId);

    @GetMapping("/negotiations/interests/{interestId}")
    Optional<List<NegotiationResponseBody>> getNegotiationsByInterest(@RequestHeader("Authorization") String authHeader, @PathVariable Integer interestId);

    @PutMapping("/negotiations/{negotiationId}/approved-by-seller")
    void approvedNegotiationBySeller(@RequestHeader("Authorization") String authHeader, @PathVariable String negotiationId);

    @PutMapping("/negotiations/{negotiationId}/approved-by-buyer")
    void approvedNegotiationByBuyer(@RequestHeader("Authorization") String authHeader, @PathVariable String negotiationId);

    @DeleteMapping("/negotiations/{negotiationId}/reproved-by-seller")
    void reprovedNegotiationBySeller(@RequestHeader("Authorization") String authHeader, @PathVariable String negotiationId);

    @DeleteMapping("/negotiations/{negotiationId}/reproved-by-buyer")
    void reprovedNegotiationByBuyer(@RequestHeader("Authorization") String authHeader, @PathVariable String negotiationId);

    @DeleteMapping("/negotiations/{negotiationId}")
    void deleteNegotiation(@RequestHeader("Authorization") String authHeader, @PathVariable String negotiationId);
}
