package com.bustr.backend.service;

import static com.bustr.backend.utils.Distance.distanceBetweenCoordinates;

import com.bustr.backend.dto.UserDto;
import com.bustr.backend.model.Bus;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final BusService service;

  public UserService(BusService service) {
    this.service = service;
  }

  public List<Bus> nearby(UserDto user) {
    List<Bus> buses = service.all();

    return buses
        .stream()
        .filter(bus -> {
          Point ref = new Point(user.getLatitude(), user.getLongitude());
          Point other = new Point(bus.getLatitude(), bus.getLongitude());

          return distanceBetweenCoordinates(ref, other) <= 2;
        })
        .collect(Collectors.toList());
  }
}
