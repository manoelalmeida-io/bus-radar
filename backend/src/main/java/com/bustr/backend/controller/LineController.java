package com.bustr.backend.controller;

import com.bustr.backend.dto.LineDto;
import com.bustr.backend.exception.ResourceNotFoundException;
import com.bustr.backend.model.Line;
import com.bustr.backend.service.LineService;
import java.util.List;
import java.util.Optional;
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
public class LineController {

  private final LineService service;
  private final ModelMapper mapper = new ModelMapper();

  public LineController(LineService repository) {
    this.service = repository;
  }

  @GetMapping("/lines")
  public ResponseEntity<List<Line>> all() {
    List<Line> lines = service.all();
    return ResponseEntity.ok(lines);
  }

  @GetMapping("/lines/{code}")
  public ResponseEntity<Line> one(@PathVariable String code) {
    Optional<Line> line = service.one(code);

    if (line.isPresent()) {
      return ResponseEntity.ok(line.get());
    }

    throw new ResourceNotFoundException(code);
  }

  @PostMapping("/lines")
  public ResponseEntity<Line> create(@RequestBody LineDto line) {
    Line created = service.create(mapper.map(line, Line.class));
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @PutMapping("/lines/{code}")
  public ResponseEntity<Line> update(@PathVariable String code, @RequestBody LineDto line) {
    Line updated = service.update(code, mapper.map(line, Line.class));
    return ResponseEntity.ok(updated);
  }

  @DeleteMapping("/lines/{code}")
  public ResponseEntity<Void> delete(@PathVariable String code) {
    service.delete(code);
    return ResponseEntity.ok().build();
  }
}
