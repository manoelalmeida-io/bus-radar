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

import com.bustr.backend.controller.LineController;
import com.bustr.backend.exception.ResourceNotFoundException;
import com.bustr.backend.model.Line;
import com.bustr.backend.service.LineService;
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
@WebMvcTest(controllers = LineController.class)
public class LineControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper mapper;
  @MockBean
  private LineService service;

  private Line example;

  @BeforeEach
  private void init() {
    example = new Line(
        "0292",
        "TERM. PIRITUBA/TERM. LAPA",
        "TERM. PIRITUBA",
        "TERM. LAPA",
        "#FFFFFF"
    );
  }

  @Test
  void list() throws Exception {
    when(service.all()).thenReturn(List.of(example));

    mockMvc.perform(get("/api/lines")
        .contentType("application/json"))
        .andExpect(status().isOk())
        .andExpect(content().json(mapper.writeValueAsString(List.of(example))));
  }

  @Test
  void one() throws Exception {
    when(service.one("0292")).thenReturn(Optional.of(example));

    mockMvc.perform(get("/api/lines/{code}", "0292")
        .contentType("application/json"))
        .andExpect(status().isOk())
        .andExpect(content().json(mapper.writeValueAsString(example)));
  }

  @Test
  void create() throws Exception {
    when(service.create(any(Line.class))).then(returnsFirstArg());

    mockMvc.perform(post("/api/lines")
        .contentType("application/json")
        .content(mapper.writeValueAsString(example)))
        .andExpect(status().isCreated())
        .andExpect(content().json(mapper.writeValueAsString(example)));
  }

  @Test
  void update() throws Exception {
    when(service.update(eq("0292"), any(Line.class))).then(returnsLastArg());

    mockMvc.perform(put("/api/lines/{code}", "0292")
        .contentType("application/json")
        .content(mapper.writeValueAsString(example)))
        .andExpect(status().isOk())
        .andExpect(content().json(mapper.writeValueAsString(example)));
  }

  @Test
  void del() throws Exception {
    doThrow(ResourceNotFoundException.class).when(service).delete(anyString());
    doNothing().when(service).delete("0292");

    mockMvc.perform(delete("/api/lines/{code}", "0292"))
        .andExpect(status().isOk());

    mockMvc.perform(delete("/api/lines/{code}", "0234"))
        .andExpect(status().isBadRequest());
  }
}
