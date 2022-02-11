package com.ufrj.pp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.ufrj.pp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LawyerDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LawyerDTO.class);
        LawyerDTO lawyerDTO1 = new LawyerDTO();
        lawyerDTO1.setId(1L);
        LawyerDTO lawyerDTO2 = new LawyerDTO();
        assertThat(lawyerDTO1).isNotEqualTo(lawyerDTO2);
        lawyerDTO2.setId(lawyerDTO1.getId());
        assertThat(lawyerDTO1).isEqualTo(lawyerDTO2);
        lawyerDTO2.setId(2L);
        assertThat(lawyerDTO1).isNotEqualTo(lawyerDTO2);
        lawyerDTO1.setId(null);
        assertThat(lawyerDTO1).isNotEqualTo(lawyerDTO2);
    }
}
