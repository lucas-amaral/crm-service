package com.proposta.aceita.crmservice.entities;

import com.proposta.aceita.crmservice.entities.req.GarageRequestBody;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GarageTest {

    @Test
    public void fromList() {
        var property = new Property();

        var bodyBox20 = new GarageRequestBody(3, 20, "snajknjkasnsa");
        var bodyBox23 = new GarageRequestBody(4, 23, "snajknjkasnsa");
        var bodyBox37 = new GarageRequestBody(4,  37, "snajknjkasnsa");

        var garageBox20 = new Garage(3, property, 20, "snajknjkasnsa");
        var garageBox23 = new Garage(4, property, 23, "snajknjkasnsa");
        var garageBox37 = new Garage(4, property, 37, "snajknjkasnsa");

        assertThat(Garage.ofList(List.of(bodyBox20, bodyBox23, bodyBox37), property))
                .isEqualTo(List.of(garageBox20, garageBox23, garageBox37));

    }

    @Test
    public void fromListWithEmptyList() {
        var property = new Property();

        assertThat(Garage.ofList(Collections.emptyList(), property)).isEqualTo(Collections.emptyList());

    }

    @Test
    public void fromListWithNull() {

        assertThat(Garage.ofList(null, null)).isEqualTo(Collections.emptyList());

    }
}
