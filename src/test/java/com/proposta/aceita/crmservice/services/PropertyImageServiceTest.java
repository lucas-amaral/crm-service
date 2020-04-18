package com.proposta.aceita.crmservice.services;

import com.proposta.aceita.crmservice.entities.PropertyImage;
import com.proposta.aceita.crmservice.repositories.PropertyImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PropertyImageServiceTest {

    @MockBean
    private PropertyImageRepository propertyImageRepository;

    private PropertyImageService propertyImageService;

    @BeforeEach
    public void setup() {
        propertyImageService = new PropertyImageService(propertyImageRepository);
    }

    @Test
    public void get() {

        var propertyImage = new PropertyImage("5e9a7eb89d349572a4ecbc40", 23, "image/jpeg", "N7nKyxdHKK2S7Lsiyy0uiJbS/pt6EIpVFeVRI/h9BbpspSaUI/sKS+bBRoqy3FVk20RaxGwqU87MvJ2R8SSexYuzJjW+jFpbRPRaY95FSndRditJWc2N+h+i3ur1P286Y/2T//2Q==");

        var propertyId = 23;

        when(propertyImageRepository.findByPropertyId(propertyId)).thenReturn(Optional.of(List.of(propertyImage)));

        propertyImageService.get(propertyId);

        verify(propertyImageRepository).findByPropertyId(propertyId);
    }

    @Test
    public void save() {
        var propertyImage = new PropertyImage(null, 23, "image/jpeg", "N7nKyxdHKK2S7Lsiyy0uiJbS/pt6EIpVFeVRI/h9BbpspSaUI/sKS+bBRoqy3FVk20RaxGwqU87MvJ2R8SSexYuzJjW+jFpbRPRaY95FSndRditJWc2N+h+i3ur1P286Y/2T//2Q==");

        propertyImageService.save(List.of(propertyImage));

        verify(propertyImageRepository).saveAll(List.of(propertyImage));
    }

    @Test
    public void delete() {

        var id_ = "5e9a7eb89d349572a4ecbc40";

        propertyImageService.delete(id_);

        verify(propertyImageRepository).deleteById(id_);
    }

}
