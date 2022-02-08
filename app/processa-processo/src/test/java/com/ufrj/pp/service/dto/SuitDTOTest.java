package com.ufrj.pp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.ufrj.pp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SuitDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SuitDTO.class);
        SuitDTO suitDTO1 = new SuitDTO();
        suitDTO1.setId(1L);
        SuitDTO suitDTO2 = new SuitDTO();
        assertThat(suitDTO1).isNotEqualTo(suitDTO2);
        suitDTO2.setId(suitDTO1.getId());
        assertThat(suitDTO1).isEqualTo(suitDTO2);
        suitDTO2.setId(2L);
        assertThat(suitDTO1).isNotEqualTo(suitDTO2);
        suitDTO1.setId(null);
        assertThat(suitDTO1).isNotEqualTo(suitDTO2);
    }
}
