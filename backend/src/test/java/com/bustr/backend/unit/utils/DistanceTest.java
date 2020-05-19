package com.bustr.backend.unit.utils;

import static com.bustr.backend.utils.Distance.distanceBetweenCoordinates;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

import org.junit.jupiter.api.Test;
import org.springframework.data.geo.Point;

public class DistanceTest {

  @Test
  void distance() {
    Point ref = new Point(53.32055555555556, -1.7297222222222221);
    Point other = new Point(53.31861111111111, -1.6997222222222223);

    Point ref2 = new Point(40.689202777778, -74.044219444444);
    Point other2 = new Point(38.889069444444, -77.034502777778);

    Point ref3 = new Point(-23.41839, -46.7173594);
    Point other3 = new Point(53.31861111111111, -1.6997222222222223);

    assertThat(distanceBetweenCoordinates(ref, other)).isCloseTo(2.0, offset(0.5));
    assertThat(distanceBetweenCoordinates(ref2, other2)).isCloseTo(324.52, offset(0.5));
    assertThat(distanceBetweenCoordinates(ref3, other3)).isCloseTo(9568.73, offset(0.5));
  }
}
