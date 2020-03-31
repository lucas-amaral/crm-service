package com.proposta.aceita.crmservice.entities;

import com.proposta.aceita.crmservice.entities.req.EditBarterRequestBody;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static com.proposta.aceita.crmservice.entities.enums.BarterType.CAR;
import static com.proposta.aceita.crmservice.entities.enums.BarterType.PROPERTY;
import static org.assertj.core.api.Assertions.assertThat;

public class BarterTest {

    @Test
    public void fromList() {
        var interest = new Interest();

        var body20 = new EditBarterRequestBody(3, null, CAR, BigDecimal.TEN);
        var body23 = new EditBarterRequestBody(4, null, PROPERTY, BigDecimal.TEN);
        var body37 = new EditBarterRequestBody(4, null, CAR, BigDecimal.ONE);

        var barter20 = new Barter(3, interest, CAR, BigDecimal.TEN);
        var barter23 = new Barter(4, interest, PROPERTY, BigDecimal.TEN);
        var barter37 = new Barter(4, interest, CAR, BigDecimal.ONE);

        assertThat(Barter.fromList(List.of(body20, body23, body37), interest))
                .isEqualTo(List.of(barter20, barter23, barter37));

    }

    @Test
    public void fromListWithEmptyList() {
        var interest = new Interest();

        assertThat(Barter.fromList(Collections.emptyList(), interest)).isEqualTo(Collections.emptyList());

    }

    @Test
    public void fromListWithNull() {

        assertThat(Barter.fromList(null, null)).isEqualTo(Collections.emptyList());

    }
}
