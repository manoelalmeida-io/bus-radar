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

import com.bustr.backend.controller.BusController;
import com.bustr.backend.exception.ResourceNotFoundException;
import com.bustr.backend.model.Bus;
import com.bustr.backend.model.Line;
import com.bustr.backend.service.BusService;
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
@WebMvcTest(controllers = BusController.class)
public class BusControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper mapper;
  @MockBean
  private BusService service;

  private Line line;
  private Bus example;

  @BeforeEach
  private void init() {
    line = new Line(
        "0292",
        "TERM. PIRITUBA/TERM. LAPA",
        "TERM. PIRITUBA",
        "TERM. LAPA",
        "#FFFFFF"
    );
    example = new Bus("923892", 1.0, 1.0, line);
  }

  @Test
  void list() throws Exception {
    when(service.all()).thenReturn(List.of(example));

    mockMvc.perform(get("/api/buses"))
        .andExpect(status().isOk())
        .andExpect(content().json(mapper.writeValueAsString(List.of(example))));
  }

  @Test
  void one() throws Exception {
    when(service.one("923892")).thenReturn(Optional.of(example));

    mockMvc.perform(get("/api/buses/{code}", "923892"))
        .andExpect(status().isOk())
        .andExpect(content().json(mapper.writeValueAsString(example)));

    mockMvc.perform(get("/api/buses/{code}","123423"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void create() throws Exception {
    when(service.create(any(Bus.class))).then(returnsFirstArg());

    mockMvc.perform(post("/api/buses")
        .contentType("application/json")
        .content(mapper.writeValueAsString(example)))
        .andExpect(status().isCreated())
        .andExpect(content().json(mapper.writeValueAsString(example)));
  }

  @Test
  void update() throws Exception {
    example = new Bus("", 1.0, 1.0, line);

    when(service.update(eq("923892"), any(Bus.class))).then(returnsLastArg());

    mockMvc.perform(put("/api/buses/{code}", "923892")
        .contentType("application/json")
        .content(mapper.writeValueAsString(example)))
        .andExpect(status().isOk())
        .andExpect(content().json(mapper.writeValueAsString(example)));
  }

  @Test
  void del() throws Exception {
    doThrow(ResourceNotFoundException.class).when(service).delete(anyString());
    doNothing().when(service).delete("923892");

    mockMvc.perform(delete("/api/buses/{code}", "923892"))
        .andExpect(status().isOk());

    mockMvc.perform(delete("/api/buses/{code}", "0234"))
        .andExpect(status().isBadRequest());
  }
}
