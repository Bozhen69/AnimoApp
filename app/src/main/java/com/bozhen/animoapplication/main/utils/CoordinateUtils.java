package com.bozhen.animoapplication.main.utils;

import com.yandex.mapkit.geometry.Point;

public class CoordinateUtils {
    public static Point getCoordinateFromString(String coord){
       String[] pos = coord.split(",");
       double lat = Double.valueOf(pos[0]);
       double lg = Double.valueOf(pos[1]);
       return new Point(lat,lg);
    }
}
