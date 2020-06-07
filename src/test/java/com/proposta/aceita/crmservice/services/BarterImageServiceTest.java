package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.BarterImage;
import com.proposta.aceita.crmservice.repositories.BarterImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BarterImageServiceTest {

    @MockBean
    private BarterImageRepository barterImageRepository;

    private BarterImageService barterImageService;

    @BeforeEach
    void setup() {
        barterImageService = new BarterImageService(barterImageRepository);
    }

    @Test
    void get() {

        var barterImage = new BarterImage("5e9a7eb89d349572a4ecbc40", 23, "image/jpeg", "N7nKyxdHKK2S7Lsiyy0uiJbS/pt6EIpVFeVRI/h9BbpspSaUI/sKS+bBRoqy3FVk20RaxGwqU87MvJ2R8SSexYuzJjW+jFpbRPRaY95FSndRditJWc2N+h+i3ur1P286Y/2T//2Q==");

        var barterId = 23;

        when(barterImageRepository.findByBarterId(barterId)).thenReturn(Optional.of(List.of(barterImage)));

        barterImageService.get(barterId);

        verify(barterImageRepository).findByBarterId(barterId);
    }

    @Test
    void save() {
        var barterImage = new BarterImage(null, 23, "image/jpeg", "N7nKyxdHKK2S7Lsiyy0uiJbS/pt6EIpVFeVRI/h9BbpspSaUI/sKS+bBRoqy3FVk20RaxGwqU87MvJ2R8SSexYuzJjW+jFpbRPRaY95FSndRditJWc2N+h+i3ur1P286Y/2T//2Q==");

        barterImageService.save(List.of(barterImage));

        verify(barterImageRepository).saveAll(List.of(barterImage));
    }

    @Test
    void delete() {

        var id_ = "5e9a7eb89d349572a4ecbc40";

        barterImageService.delete(id_);

        verify(barterImageRepository).deleteById(id_);
    }

}
