package com.ufrj.pp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LawyerMapperTest {

    private LawyerMapper lawyerMapper;

    @BeforeEach
    public void setUp() {
        lawyerMapper = new LawyerMapperImpl();
    }
}
