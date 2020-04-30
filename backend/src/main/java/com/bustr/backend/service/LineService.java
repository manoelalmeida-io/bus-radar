package com.bustr.backend.service;

import com.bustr.backend.exception.ResourceNotFoundException;
import com.bustr.backend.model.Line;
import com.bustr.backend.repository.LineRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class LineService {

  private final LineRepository repository;

  public LineService(LineRepository repository) {
    this.repository = repository;
  }

  public List<Line> all() {
    return repository.findAll();
  }

  public Optional<Line> one(String code) {
    return repository.findById(code.replace('-', '/'));
  }

  public Line create(Line line) {
    return repository.save(line);
  }

  public Line update(String code, Line line) {
    Optional<Line> existent = repository.findById(code.replace('-', '/'));

    if (existent.isPresent()) {
      line.setCode(existent.get().getCode());
      return repository.save(line);
    }

    throw new ResourceNotFoundException(code);
  }

  public void delete(String code) {
    Optional<Line> existent = repository.findById(code.replace('-', '/'));

    existent.ifPresentOrElse(repository::delete,() -> {
      throw new ResourceNotFoundException();
    });
  }
}
