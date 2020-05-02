package com.bustr.backend;

import static org.assertj.core.api.Assertions.assertThat;

import com.bustr.backend.controller.LineController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {

  @Autowired
  private LineController controller;

  @Test
  void applicationStarts() {
    Assertions.assertDoesNotThrow(() -> BackendApplication.main(new String[] {}));
  }

  @Test
  void contextLoads() {
    assertThat(controller).isNotNull();
  }
}