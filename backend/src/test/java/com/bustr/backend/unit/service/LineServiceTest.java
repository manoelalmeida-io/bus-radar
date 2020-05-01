package com.bustr.backend.unit.service;

import com.bustr.backend.exception.ResourceNotFoundException;
import com.bustr.backend.model.Line;
import com.bustr.backend.repository.LineRepository;
import com.bustr.backend.service.LineService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;

public class LineServiceTest {

  private final LineRepository repository = Mockito.mock(LineRepository.class);
  private LineService service;
  private Line example;

  @BeforeEach
  private void init() {
    service = new LineService(repository);
    example = new Line(
        "0292",
        "TERM. PIRITUBA/TERM. LAPA",
        "TERM. PIRITUBA",
        "TERM. LAPA",
        "#FFFFFF"
    );
  }

  @Test
  void all() {
    List<Line> all = new ArrayList<>();
    all.add(example);

    Mockito.when(repository.findAll()).thenReturn(all);

    List<Line> lines = service.all();
    assertThat(lines.get(0)).isEqualTo(example);
  }

  @Test
  void one() {
    Mockito.when(repository.findById("0292")).thenReturn(Optional.of(example));
    Mockito.when(repository.findById("2932")).thenReturn(Optional.empty());

    Optional<Line> exists = service.one("0292");
    Optional<Line> notExists = service.one("2932");

    assertThat(exists.isPresent()).isTrue();
    assertThat(notExists.isPresent()).isFalse();
  }

  @Test
  void create() {
    Mockito.when(repository.save(any(Line.class))).then(returnsFirstArg());

    Line created = service.create(example);

    assertThat(created.getCode()).isEqualTo("0292");
    assertThat(created.getName()).isEqualTo("TERM. PIRITUBA/TERM. LAPA");
  }

  @Test
  void update() {
    Line line = new Line(
        "",
        "TERM. PIRITUBA/TERM. LAPA",
        "TERM. PIRITUBA",
        "TERM. LAPA",
        "#000000"
    );

    Mockito.when(repository.findById("0292")).thenReturn(Optional.of(example));
    Mockito.when(repository.save(any(Line.class))).then(returnsFirstArg());

    Line updated = service.update("0292", line);

    assertThat(updated.getCode()).isEqualTo("0292");
    assertThat(updated.getColor()).isEqualTo("#000000");
  }

  @Test
  void delete() {
    Mockito.when(repository.findById("0292")).thenReturn(Optional.of(example));

    Assertions.assertDoesNotThrow(() -> service.delete("0292"));
    Assertions.assertThrows(ResourceNotFoundException.class, () -> service.delete("2312"));
  }
}
