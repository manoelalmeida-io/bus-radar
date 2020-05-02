package com.bustr.backend.unit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;

import com.bustr.backend.exception.ForeignKeyConstraintException;
import com.bustr.backend.exception.ResourceNotFoundException;
import com.bustr.backend.model.Bus;
import com.bustr.backend.model.Line;
import com.bustr.backend.repository.BusRepository;
import com.bustr.backend.repository.LineRepository;
import com.bustr.backend.service.BusService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.geo.Point;

public class BusServiceTest {

  private final BusRepository repository = Mockito.mock(BusRepository.class);
  private final LineRepository lines = Mockito.mock(LineRepository.class);
  private BusService service;
  private Bus example, constraintFail;
  private Line line;

  @BeforeEach
  private void init() {
    service = new BusService(repository, lines);
    example = new Bus("923204", new Point(1, 1),"0292");
    constraintFail = new Bus("723482", new Point(1, 2), "4834");
    line = new Line(
        "0292",
        "TERM. PIRITUBA/TERM. LAPA",
        "TERM. PIRITUBA",
        "TERM. LAPA",
        "#FFFFFF"
    );
  }

  @Test
  void all() {
    List<Bus> all = new ArrayList<>();
    all.add(example);

    Mockito.when(repository.findAll()).thenReturn(all);

    List<Bus> buses = service.all();
    assertThat(buses.get(0)).isEqualTo(example);
  }

  @Test
  void one() {
    Mockito.when(repository.findById("923204")).thenReturn(Optional.of(example));
    Mockito.when(repository.findById("723842")).thenReturn(Optional.empty());

    Optional<Bus> exists = service.one("923204");
    Optional<Bus> notExists = service.one("723842");

    assertThat(exists.isPresent()).isTrue();
    assertThat(exists.get()).isEqualTo(example);
    assertThat(notExists).isEmpty();
  }

  @Test
  void create() {
    Mockito.when(repository.save(any(Bus.class))).then(returnsFirstArg());
    Mockito.when(lines.findById("0292")).thenReturn(Optional.of(line));
    Mockito.when(lines.findById("4834")).thenReturn(Optional.empty());

    Bus created = service.create(example);

    assertThat(created.getCode()).isEqualTo("923204");
    assertThat(created.getLine()).isEqualTo("0292");
    Assertions.assertThrows(ForeignKeyConstraintException.class, () -> service.create(constraintFail));
  }

  @Test
  void update() {
    Bus bus = new Bus("", new Point(4, 3),"2039");
    Bus exceptionBus = new Bus("", new Point(4, 3), "4834");

    Mockito.when(repository.save(any(Bus.class))).then(returnsFirstArg());
    Mockito.when(repository.findById("923204")).thenReturn(Optional.of(example));
    Mockito.when(repository.findById("737342")).thenReturn(Optional.empty());
    Mockito.when(lines.findById("2039")).thenReturn(Optional.of(line));
    Mockito.when(lines.findById("4834")).thenReturn(Optional.empty());

    Bus updated = service.update("923204", bus);

    assertThat(updated.getCode()).isEqualTo(example.getCode());
    assertThat(updated.getLocation()).isEqualTo(bus.getLocation());
    Assertions.assertThrows(ResourceNotFoundException.class, () -> service.update("737342", bus));
    Assertions.assertThrows(ForeignKeyConstraintException.class, () -> service.update("923204", exceptionBus));
  }

  @Test
  void delete() {
    Mockito.when(repository.findById("923204")).thenReturn(Optional.of(example));

    Assertions.assertDoesNotThrow(() -> service.delete("923204"));
    Assertions.assertThrows(ResourceNotFoundException.class, () -> service.delete("342842"));
  }
}
