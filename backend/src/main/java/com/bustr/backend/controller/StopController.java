package com.bustr.backend.controller;

import com.bustr.backend.dto.StopDto;
import com.bustr.backend.exception.ResourceNotFoundException;
import com.bustr.backend.model.Stop;
import com.bustr.backend.service.StopService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StopController {

  private final StopService service;
  private final ModelMapper mapper = new ModelMapper();

  public StopController(StopService service) {
    this.service = service;
  }

  @GetMapping("/stops")
  public ResponseEntity<List<Stop>> all() {
    List<Stop> stops = service.all();
    return ResponseEntity.ok(stops);
  }

  @GetMapping("/stops/{code}")
  public ResponseEntity<Stop> one(@PathVariable String code) {
    Optional<Stop> stop = service.one(code);

    if (stop.isPresent()) {
      return ResponseEntity.ok(stop.get());
    }

    throw new ResourceNotFoundException();
  }

  @PostMapping("/stops")
  public ResponseEntity<Stop> create(@Valid @RequestBody StopDto stop) {
    Stop created = service.create(mapper.map(stop, Stop.class));
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @PutMapping("/stops/{code}")
  public ResponseEntity<Stop> update(@PathVariable String code, @Valid @RequestBody StopDto stop) {
    Stop updated = service.update(code, mapper.map(stop, Stop.class));
    return ResponseEntity.ok(updated);
  }

  @DeleteMapping("/stops/{code}")
  public ResponseEntity<Void> delete(@PathVariable String code) {
    service.delete(code);
    return ResponseEntity.ok().build();
  }
}
