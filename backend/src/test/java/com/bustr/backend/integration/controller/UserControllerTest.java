package com.bustr.backend.integration.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bustr.backend.controller.UserController;
import com.bustr.backend.dto.UserDto;
import com.bustr.backend.model.Bus;
import com.bustr.backend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper mapper;
  @MockBean
  private UserService service;

  private UserDto user;
  private Bus near;

  @BeforeEach
  private void init() {
    user = new UserDto();
    user.setLatitude(-23.418323);
    user.setLongitude(-46.717530);

    near = new Bus();
    near.setCode("1234");
    near.setLatitude(-23.420096);
    near.setLongitude(-46.71591);
  }

  @Test
  void nearby() throws Exception {
    when(service.nearby(any(UserDto.class))).thenReturn(List.of(near));

    mockMvc.perform(post("/api/user/nearby")
        .contentType("application/json")
        .content(mapper.writeValueAsString(user)))
        .andExpect(status().isOk())
        .andExpect(content().json(mapper.writeValueAsString(List.of(near))));
  }
}
