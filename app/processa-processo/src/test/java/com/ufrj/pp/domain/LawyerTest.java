package com.ufrj.pp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ufrj.pp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LawyerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Lawyer.class);
        Lawyer lawyer1 = new Lawyer();
        lawyer1.setId(1L);
        Lawyer lawyer2 = new Lawyer();
        lawyer2.setId(lawyer1.getId());
        assertThat(lawyer1).isEqualTo(lawyer2);
        lawyer2.setId(2L);
        assertThat(lawyer1).isNotEqualTo(lawyer2);
        lawyer1.setId(null);
        assertThat(lawyer1).isNotEqualTo(lawyer2);
    }
}
