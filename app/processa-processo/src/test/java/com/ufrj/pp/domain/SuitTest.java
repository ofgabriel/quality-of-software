package com.ufrj.pp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ufrj.pp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SuitTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Suit.class);
        Suit suit1 = new Suit();
        suit1.setId(1L);
        Suit suit2 = new Suit();
        suit2.setId(suit1.getId());
        assertThat(suit1).isEqualTo(suit2);
        suit2.setId(2L);
        assertThat(suit1).isNotEqualTo(suit2);
        suit1.setId(null);
        assertThat(suit1).isNotEqualTo(suit2);
    }
}
