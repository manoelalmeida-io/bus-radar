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

  @BeforeEach
  private void init() {
    service = new LineService(repository);
  }

  @Test
  void all() {
    Line line = new Line();
    line.setCode("0292");
    line.setName("TERM. PIRITUBA/TERM. LAPA");
    line.setForward("TERM. PIRITUBA");
    line.setBackward("TERM. LAPA");
    line.setColor("#FFFFFF");

    List<Line> all = new ArrayList<>();
    all.add(line);

    Mockito.when(repository.findAll()).thenReturn(all);

    List<Line> lines = service.all();
    assertThat(lines.get(0)).isEqualTo(line);
  }

  @Test
  void one() {
    Line line = new Line();
    line.setCode("0292");
    line.setName("TERM. PIRITUBA/TERM. LAPA");
    line.setForward("TERM. PIRITUBA");
    line.setBackward("TERM. LAPA");
    line.setColor("#FFFFFF");

    Mockito.when(repository.findById("0292")).thenReturn(Optional.of(line));
    Mockito.when(repository.findById("2932")).thenReturn(Optional.empty());

    Optional<Line> exists = service.one("0292");
    Optional<Line> notExists = service.one("2932");

    assertThat(exists.isPresent()).isTrue();
    assertThat(notExists.isPresent()).isFalse();
  }

  @Test
  void create() {
    Line line = new Line();
    line.setCode("0292");
    line.setName("TERM. PIRITUBA/TERM. LAPA");
    line.setForward("TERM. PIRITUBA");
    line.setBackward("TERM. LAPA");
    line.setColor("#FFFFFF");

    Mockito.when(repository.save(any(Line.class))).then(returnsFirstArg());

    Line created = service.create(line);

    assertThat(created.getCode()).isEqualTo("0292");
    assertThat(created.getName()).isEqualTo("TERM. PIRITUBA/TERM. LAPA");
  }

  @Test
  void update() {
    Line existent = new Line();
    existent.setCode("0292");
    existent.setName("TERM. PIRITUBA/TERM. LAPA");
    existent.setForward("TERM. PIRITUBA");
    existent.setBackward("TERM. LAPA");
    existent.setColor("#FFFFFF");

    Line line = new Line();
    line.setName("TERM. PIRITUBA/TERM. LAPA");
    line.setForward("TERM. PIRITUBA");
    line.setBackward("TERM. LAPA");
    line.setColor("#000000");

    Mockito.when(repository.findById("0292")).thenReturn(Optional.of(existent));
    Mockito.when(repository.save(any(Line.class))).then(returnsFirstArg());

    Line updated = service.update("0292", line);

    assertThat(updated.getCode()).isEqualTo("0292");
    assertThat(updated.getColor()).isEqualTo("#000000");
  }

  @Test
  void delete() {
    Line existent = new Line();
    existent.setCode("0292");
    existent.setName("TERM. PIRITUBA/TERM. LAPA");
    existent.setForward("TERM. PIRITUBA");
    existent.setBackward("TERM. LAPA");
    existent.setColor("#FFFFFF");

    Mockito.when(repository.findById("0292")).thenReturn(Optional.of(existent));

    Assertions.assertDoesNotThrow(() -> service.delete("0292"));
    Assertions.assertThrows(ResourceNotFoundException.class, () -> service.delete("2312"));
  }
}
