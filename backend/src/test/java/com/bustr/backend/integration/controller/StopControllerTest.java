package com.bustr.backend.integration.controller;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.AdditionalAnswers.returnsLastArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bustr.backend.controller.StopController;
import com.bustr.backend.exception.ResourceNotFoundException;
import com.bustr.backend.model.Stop;
import com.bustr.backend.service.StopService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = StopController.class)
public class StopControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper mapper;
  @MockBean
  private StopService service;

  private Stop example;

  @BeforeEach
  private void init() {
    example = new Stop("332", 1.0, 1.0, "Term. Pirituba");
  }

  @Test
  void all() throws Exception {
    when(service.all()).thenReturn(List.of(example));

    mockMvc.perform(get("/api/stops"))
        .andExpect(status().isOk())
        .andExpect(content().json(mapper.writeValueAsString(List.of(example))));
  }

  @Test
  void one() throws Exception {
    when(service.one("332")).thenReturn(Optional.of(example));

    mockMvc.perform(get("/api/stops/{code}", "332"))
        .andExpect(status().isOk())
        .andExpect(content().json(mapper.writeValueAsString(example)));

    mockMvc.perform(get("/api/stops/{code}","233"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void create() throws Exception {
    when(service.create(any(Stop.class))).then(returnsFirstArg());

    mockMvc.perform(post("/api/stops")
        .contentType("application/json")
        .content(mapper.writeValueAsString(example)))
        .andExpect(status().isCreated())
        .andExpect(content().json(mapper.writeValueAsString(example)));
  }

  @Test
  void update() throws Exception {
    example = new Stop("", 2.0, 2.0, "Term. Pirituba");

    when(service.update(eq("332"), any(Stop.class))).then(returnsLastArg());

    mockMvc.perform(put("/api/stops/{code}", "332")
        .contentType("application/json")
        .content(mapper.writeValueAsString(example)))
        .andExpect(status().isOk())
        .andExpect(content().json(mapper.writeValueAsString(example)));
  }

  @Test
  void del() throws Exception {
    doThrow(ResourceNotFoundException.class).when(service).delete(anyString());
    doNothing().when(service).delete("332");

    mockMvc.perform(delete("/api/stops/{code}", "332"))
        .andExpect(status().isOk());

    mockMvc.perform(delete("/api/stops/{code}", "234"))
        .andExpect(status().isBadRequest());
  }
}
