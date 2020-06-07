package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.Property;
import com.proposta.aceita.crmservice.entities.Sale;
import com.proposta.aceita.crmservice.entities.req.EditSaleRequestBody;
import com.proposta.aceita.crmservice.repositories.SaleRepository;
import com.proposta.aceita.crmservice.services.integrations.MatchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static com.proposta.aceita.crmservice.entities.enums.PropertyType.APARTMENT;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class SaleServiceTest {

    @MockBean
    private SaleRepository saleRepository;

    @MockBean
    private PropertyService propertyService;

    @MockBean
    private MatchService matchService;

    private SaleService saleService;

    @BeforeEach
    void setup() {
        saleService = new SaleService(saleRepository, propertyService, matchService);
    }
    
    @Test
    void getById() {

        var id = 23;

        saleService.getById(id);

        verify(saleRepository).findById(id);
    }

    @Test
    void getByProperty() {

        saleService.getByProperty(54);

        verify(saleRepository).findByPropertyId(54);
    }

    @Test
    void save() {

        var body = new EditSaleRequestBody(356, 234, BigDecimal.valueOf(214.55), true, BigDecimal.valueOf(100), false, null, false, null, LocalDate.of(2020, 10, 20));

        var property = new Property(234, null, "Descricao", APARTMENT, BigDecimal.valueOf(2133), 232, null, 23423422, 3, 2, 2, true, true, true, false, null, true);
        var sale = new Sale(356, property, BigDecimal.valueOf(214.55), true, BigDecimal.valueOf(100), false, null, false, null, LocalDate.of(2020, 10, 20));

        when(propertyService.getById(234)).thenReturn(Optional.of(property));
        when(saleRepository.save(sale)).thenReturn(sale);

        saleService.save(body);

        verify(saleRepository).save(sale);
        verify(matchService).saveSale(sale);
    }

    @Test
    void saveWithoutProperty() {

        var body = new EditSaleRequestBody(356, 234, BigDecimal.valueOf(214.55), true, BigDecimal.valueOf(100), false, null, false, null, LocalDate.of(2020, 10, 20));

        when(propertyService.getById(234)).thenReturn(Optional.empty());

        saleService.save(body);

        verifyNoInteractions(saleRepository);
    }

    @Test
    void delete() {

        var id = 23;

        saleService.delete(id);

        verify(saleRepository).deleteById(id);
    }

}
