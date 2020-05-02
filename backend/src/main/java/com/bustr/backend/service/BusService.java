package com.bustr.backend.service;

import com.bustr.backend.exception.ForeignKeyConstraintException;
import com.bustr.backend.exception.ResourceNotFoundException;
import com.bustr.backend.model.Bus;
import com.bustr.backend.model.Line;
import com.bustr.backend.repository.BusRepository;
import com.bustr.backend.repository.LineRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BusService {

  private final BusRepository repository;
  private final LineRepository lines;

  public BusService(BusRepository repository, LineRepository lines) {
    this.repository = repository;
    this.lines = lines;
  }

  public List<Bus> all() {
    return repository.findAll();
  }

  public Optional<Bus> one(String code) {
    return repository.findById(code);
  }

  public Bus create(Bus bus) {
    Optional<Line> line = lines.findById(bus.getLine());

    if (line.isPresent()) {
      return repository.save(bus);
    }

    throw new ForeignKeyConstraintException();
  }

  public Bus update(String code, Bus bus) {
    Optional<Bus> existent = repository.findById(code);

    if (existent.isPresent()) {
      Optional<Line> line = lines.findById(bus.getLine());

      if (line.isPresent()) {
        bus.setCode(existent.get().getCode());
        return repository.save(bus);
      }
      throw new ForeignKeyConstraintException();
    }

    throw new ResourceNotFoundException(code);
  }

  public void delete(String code) {
    Optional<Bus> bus = repository.findById(code);

    bus.ifPresentOrElse(repository::delete, () -> {
      throw new ResourceNotFoundException();
    });
  }
}
