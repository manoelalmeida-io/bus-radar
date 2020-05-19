package com.bustr.backend.unit.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.bustr.backend.dto.UserDto;
import com.bustr.backend.model.Bus;
import com.bustr.backend.service.BusService;
import com.bustr.backend.service.UserService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserServiceTest {

  private final BusService busService = Mockito.mock(BusService.class);
  private UserService service;
  private UserDto user;

  @BeforeEach
  private void init() {
    service = new UserService(busService);
    user = new UserDto();
    user.setLatitude(-23.418666);
    user.setLongitude(-46.7185226);
  }

  @Test
  void nearby() {
    Bus near = new Bus();
    near.setCode("1");
    near.setLatitude(-23.428274);
    near.setLongitude(-46.714708);

    Bus far = new Bus();
    far.setCode("2");
    far.setLatitude(-23.447793);
    far.setLongitude(-46.722144);

    Mockito.when(busService.all()).thenReturn(List.of(near, far));

    List<Bus> nearBuses = service.nearby(user);

    assertThat(nearBuses).isEqualTo(List.of(near));
  }
}
