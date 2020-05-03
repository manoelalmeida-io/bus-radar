package com.bustr.backend.controller;

import com.bustr.backend.dto.BusDto;
import com.bustr.backend.exception.ResourceNotFoundException;
import com.bustr.backend.model.Bus;
import com.bustr.backend.service.BusService;
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
public class BusController {

  private final BusService service;
  private final ModelMapper mapper = new ModelMapper();

  public BusController(BusService service) {
    this.service = service;
  }

  @GetMapping("/buses")
  public ResponseEntity<List<Bus>> all() {
    List<Bus> buses = service.all();
    return ResponseEntity.ok(buses);
  }

  @GetMapping("/buses/{code}")
  public ResponseEntity<Bus> one(@PathVariable String code) {
    Optional<Bus> bus = service.one(code);

    if (bus.isPresent()) {
      return ResponseEntity.ok(bus.get());
    }

    throw new ResourceNotFoundException(code);
  }

  @PostMapping("/buses")
  public ResponseEntity<Bus> create(@RequestBody BusDto bus) {
    Bus created = service.create(mapper.map(bus, Bus.class));
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @PutMapping("/buses/{code}")
  public ResponseEntity<Bus> update(@PathVariable String code, @RequestBody BusDto bus) {
    Bus updated = service.update(code, mapper.map(bus, Bus.class));
    return ResponseEntity.ok(updated);
  }

  @DeleteMapping("/buses/{code}")
  public ResponseEntity<Void> delete(@PathVariable String code) {
    service.delete(code);
    return ResponseEntity.ok().build();
  }
}
