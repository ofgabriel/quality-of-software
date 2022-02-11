package com.ufrj.pp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GroupMapperTest {

    private GroupMapper groupMapper;

    @BeforeEach
    public void setUp() {
        groupMapper = new GroupMapperImpl();
    }
}
