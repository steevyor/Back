package com.dant.entity.dto;

import com.dant.entity.Coordinate;

public class CoordinateDTO {
    public double xCoordinate;
    public double yCoordinate;
    public CoordinateDTO(Coordinate coordinate) {
        this.xCoordinate = coordinate.xCoordinate;
        this.yCoordinate = coordinate.yCoordinate;
    }
    public CoordinateDTO(double x, double y){
        this.yCoordinate = y;
        this.xCoordinate = x;
    }

    public double getxCoordinate() {
        return xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }
}
