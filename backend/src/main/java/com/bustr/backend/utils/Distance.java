package com.bustr.backend.utils;

import org.springframework.data.geo.Point;

public class Distance {

  public static Double distanceBetweenCoordinates(Point ref, Point other) {
    Point refRadians = new Point(Math.toRadians(ref.getX()), Math.toRadians(ref.getY()));
    Point otherRadians = new Point(Math.toRadians(other.getX()), Math.toRadians(other.getY()));

    double deltaLat = otherRadians.getX() - refRadians.getX();
    double deltaLon = otherRadians.getY() - refRadians.getY();

    double r = 6371;
    double h = Math.pow(Math.sin(deltaLat / 2), 2)
        + Math.cos(refRadians.getX()) * Math.cos(otherRadians.getX())
        * Math.pow(Math.sin(deltaLon / 2), 2);

    return 2 * r * Math.asin(Math.sqrt(h));
  }
}
