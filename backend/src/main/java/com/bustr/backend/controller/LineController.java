package com.bustr.backend.controller;

import com.bustr.backend.exception.ResourceNotFoundException;
import com.bustr.backend.model.Line;
import com.bustr.backend.service.LineService;
import java.util.List;
import java.util.Optional;
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
public class LineController {

  private final LineService service;

  public LineController(LineService repository) {
    this.service = repository;
  }

  @GetMapping("/lines")
  public ResponseEntity<?> all() {
    List<Line> lines = service.all();
    return ResponseEntity.ok(lines);
  }

  @GetMapping("/lines/{code}")
  public ResponseEntity<?> one(@PathVariable String id) {
    Optional<Line> line = service.one(id);

    if (line.isPresent()) {
      return ResponseEntity.ok(line.get());
    }

    throw new ResourceNotFoundException(id);
  }

  @PostMapping("/lines")
  public ResponseEntity<?> create(@RequestBody Line line) {
    Line created = service.create(line);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @PutMapping("/lines/{code}")
  public ResponseEntity<?> update(@PathVariable String code, @RequestBody Line line) {
    Line updated = service.update(code, line);
    return ResponseEntity.ok(updated);
  }

  @DeleteMapping("/lines/{code}")
  public ResponseEntity<?> delete(@PathVariable String code) {
    service.delete(code);
    return ResponseEntity.ok().build();
  }
}
