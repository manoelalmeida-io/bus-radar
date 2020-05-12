package com.bustr.backend.service;

import com.bustr.backend.exception.PrimaryKeyConstraintException;
import com.bustr.backend.exception.ResourceNotFoundException;
import com.bustr.backend.model.Stop;
import com.bustr.backend.repository.StopRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class StopService {

  private final StopRepository repository;

  public StopService(StopRepository repository) {
    this.repository = repository;
  }

  public List<Stop> all() {
    return repository.findAll();
  }

  public Optional<Stop> one(String code) {
    return repository.findById(code);
  }

  public Stop create(Stop stop) {
    if (stop.getCode() != null && !stop.getCode().isBlank()) {
      return repository.save(stop);
    }

    throw new PrimaryKeyConstraintException();
  }

  public Stop update(String code, Stop stop) {
    Optional<Stop> existent = repository.findById(code);

    if (existent.isPresent()) {
      stop.setCode(existent.get().getCode());
      return repository.save(stop);
    }

    throw new ResourceNotFoundException();
  }

  public void delete(String code) {
    Optional<Stop> existent = repository.findById(code);

    existent.ifPresentOrElse(repository::delete, () -> {
      throw new ResourceNotFoundException();
    });
  }
}
