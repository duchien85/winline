package org.freelectron.winline;

import java.io.Serializable;

/**
 * Created by leobel on 7/12/16.
 */
public class MPoint implements Serializable{
    public int x;
    public int y;

    public MPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x){this.x = x;}

    public int getY() {
        return this.y;
    }

    public void setY(int y){this.y = y;}

    @Override
    public boolean equals(Object other){ return x == ((MPoint) other).getX() && y == ((MPoint) other).getY();}
}
