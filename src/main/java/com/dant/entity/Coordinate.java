package com.dant.entity;

public class Coordinate {
    public double xCoordinate;
    public double yCoordinate;

    public Coordinate(){

    }

    public Coordinate(double x, double y){
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public double getXCoordinate(){ return this.xCoordinate; }

    public double getYCoordinate(){ return this.yCoordinate; }

    public void setXCoordinate(double x){
        this.xCoordinate = x;
    }
    public void setYCoordinate(double y){
        this.yCoordinate = y;
    }

}
