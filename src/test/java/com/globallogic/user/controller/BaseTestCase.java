package com.globallogic.user.controller;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public class BaseTestCase {
    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.initMocks(this);
    }
}
