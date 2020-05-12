package com.bustr.backend.unit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;

import com.bustr.backend.exception.PrimaryKeyConstraintException;
import com.bustr.backend.exception.ResourceNotFoundException;
import com.bustr.backend.model.Stop;
import com.bustr.backend.repository.StopRepository;
import com.bustr.backend.service.StopService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StopServiceTest {

  private final StopRepository repository = Mockito.mock(StopRepository.class);
  private StopService service;
  private Stop example;

  @BeforeEach
  private void init() {
    service = new StopService(repository);
    example = new Stop("123", 1.0, 1.0);
  }

  @Test
  void all() {
    List<Stop> all = List.of(example);

    Mockito.when(repository.findAll()).thenReturn(all);

    List<Stop> stops = service.all();
    assertThat(stops.get(0)).isEqualTo(example);
  }

  @Test
  void one() {
    Mockito.when(repository.findById("123")).thenReturn(Optional.of(example));
    Mockito.when(repository.findById("321")).thenReturn(Optional.empty());

    Optional<Stop> exists = service.one("123");
    Optional<Stop> notExists = service.one("321");

    assertThat(exists.isPresent()).isTrue();
    assertThat(notExists.isPresent()).isFalse();
  }

  @Test
  void create() {
    Stop emptyCode = new Stop("", 1.0, 1.0);
    Stop blankCode = new Stop();
    blankCode.setLatitude(1.0);
    blankCode.setLongitude(1.0);

    Mockito.when(repository.save(any(Stop.class))).then(returnsFirstArg());

    Stop created = service.create(example);

    assertThat(created.getCode()).isEqualTo("123");
    Assertions.assertThrows(PrimaryKeyConstraintException.class, () -> service.create(emptyCode));
    Assertions.assertThrows(PrimaryKeyConstraintException.class, () -> service.create(blankCode));
  }

  @Test
  void update() {
    Stop stop = new Stop("", 2.0, 2.0);

    Mockito.when(repository.findById("123")).thenReturn(Optional.of(example));
    Mockito.when(repository.save(any(Stop.class))).then(returnsFirstArg());

    Stop updated = service.update("123", stop);

    assertThat(updated.getCode()).isEqualTo("123");
    assertThat(updated.getLatitude()).isEqualTo(2.0);
    Assertions.assertThrows(ResourceNotFoundException.class, () -> service.update("42", stop));
  }

  @Test
  void delete() {
    Mockito.when(repository.findById("123")).thenReturn(Optional.of(example));

    Assertions.assertDoesNotThrow(() -> service.delete("123"));
    Assertions.assertThrows(ResourceNotFoundException.class, () -> service.delete("42"));
  }
}
