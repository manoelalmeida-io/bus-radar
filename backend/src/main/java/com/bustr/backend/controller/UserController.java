package com.bustr.backend.controller;

import com.bustr.backend.dto.UserDto;
import com.bustr.backend.model.Bus;
import com.bustr.backend.service.UserService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @GetMapping("/user/nearby")
  public ResponseEntity<List<Bus>> nearby(@RequestBody UserDto user) {
    List<Bus> buses = service.nearby(user);
    return ResponseEntity.ok(buses);
  }
}
