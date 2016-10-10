package org.freelectron.winline;

import java.io.Serializable;

/**
 * Created by leobel on 7/12/16.
 */
public class Checker implements Serializable {
    LogicWinLine.Color color;
    MPoint posicion;

    public Checker(MPoint p, LogicWinLine.Color color) {
        this.posicion = p;
        this.color = color;
    }

    public Checker(LogicWinLine.Color color) {
        this.color = color;
    }

    public boolean isAllocated() {
        return this.posicion != null;
    }

    public LogicWinLine.Color Color() {
        return this.color;
    }

    public MPoint getPosition() {
        return this.posicion;
    }

    public void setPosition(MPoint p) {
        this.posicion = p;
    }

    public boolean equals(Object other) {
        return this.color == ((Checker) other).color;
    }

}
