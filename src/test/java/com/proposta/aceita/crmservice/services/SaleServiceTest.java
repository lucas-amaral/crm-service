package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.Property;
import com.proposta.aceita.crmservice.entities.Sale;
import com.proposta.aceita.crmservice.entities.req.AddSaleRequestBody;
import com.proposta.aceita.crmservice.entities.req.EditSaleRequestBody;
import com.proposta.aceita.crmservice.exceptions.InterestException;
import com.proposta.aceita.crmservice.repositories.SaleRepository;
import com.proposta.aceita.crmservice.services.integrations.MatchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.proposta.aceita.crmservice.entities.enums.PropertyType.APARTMENT;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
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
    void getByUser() {

        var username = "joao@gmail.com";
        var property1 = new Property(1, null, "Descricao", APARTMENT, BigDecimal.valueOf(2133), 232, null, 23423422, 3, 2, 2, true, true, true, false, null, true);
        var property2 = new Property(2, null, "Descricao", APARTMENT, BigDecimal.valueOf(2133), 232, null, 23423422, 3, 2, 2, true, true, true, false, null, true);

        when(propertyService.getByUser(username)).thenReturn(List.of(property1, property2));

        saleService.getByUser(username);

        verify(propertyService).getByUser(username);
        verify(saleRepository).findByPropertyId(1);
        verify(saleRepository).findByPropertyId(2);

        verifyNoMoreInteractions(propertyService, saleRepository);
    }


    @Test
    void getByUserWithoutProperty() {

        var username = "joao@gmail.com";

        when(propertyService.getByUser(username)).thenReturn(List.of());

        saleService.getByUser(username);

        verify(propertyService).getByUser(username);

        verifyNoMoreInteractions(propertyService);
        verifyNoInteractions(saleRepository);
    }

    @Test
    void saveAdd() {

        var propertyId = 234;

        var body = new AddSaleRequestBody(propertyId, BigDecimal.valueOf(214.55), true, BigDecimal.valueOf(100), false, null, false, null, LocalDate.of(2020, 10, 20));

        var property = new Property(propertyId, null, "Descricao", APARTMENT, BigDecimal.valueOf(2133), 232, null, 23423422, 3, 2, 2, true, true, true, false, null, true);
        var sale = new Sale(356, property, BigDecimal.valueOf(214.55), true, BigDecimal.valueOf(100), false, null, false, null, LocalDate.of(2020, 10, 20));
        var saleWithoutId = new Sale(null, property, BigDecimal.valueOf(214.55), true, BigDecimal.valueOf(100), false, null, false, null, LocalDate.of(2020, 10, 20));

        when(propertyService.getById(propertyId)).thenReturn(Optional.of(property));
        when(saleRepository.findByPropertyId(propertyId)).thenReturn(Optional.empty());
        when(saleRepository.save(saleWithoutId)).thenReturn(sale);

        saleService.save(body);

        verify(saleRepository).save(saleWithoutId);
        verify(matchService).saveSale(sale);
    }

    @Test
    void saveEdit() {

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
    void saveShouldThrowErrorIfAlreadyExistSaleForProperty() {

        var propertyId = 234;

        var body = new AddSaleRequestBody(propertyId, BigDecimal.valueOf(214.55), true, BigDecimal.valueOf(100), false, null, false, null, LocalDate.of(2020, 10, 20));

        var property = new Property(propertyId, null, "Descricao", APARTMENT, BigDecimal.valueOf(2133), 232, null, 23423422, 3, 2, 2, true, true, true, false, null, true);
        var sale = new Sale(356, property, BigDecimal.valueOf(214.55), true, BigDecimal.valueOf(100), false, null, false, null, LocalDate.of(2020, 10, 20));

        when(propertyService.getById(propertyId)).thenReturn(Optional.of(property));
        when(saleRepository.findByPropertyId(propertyId)).thenReturn(Optional.of(sale));

        assertThatExceptionOfType(InterestException.class)
                .isThrownBy(() -> saleService.save(body))
                .matches(e -> e.getMessage().equals("Property 234 already have sale"));

        verify(saleRepository, times(0)).save(sale);
        verify(matchService, times(0)).saveSale(sale);
    }

    @Test
    void delete() {

        var id = 23;

        saleService.delete(id);

        verify(saleRepository).deleteById(id);
    }

}
