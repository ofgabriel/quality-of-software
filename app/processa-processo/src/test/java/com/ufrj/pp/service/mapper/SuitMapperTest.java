package com.ufrj.pp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SuitMapperTest {

    private SuitMapper suitMapper;

    @BeforeEach
    public void setUp() {
        suitMapper = new SuitMapperImpl();
    }
}
