package com.abz;

import com.abz.process.AbzData;

/**
 * An example of data set, note that all data structures must extend the base class <code>AbzData</code>.
 *
 * @author Phan Vu Hoang
 */
public class DataSetNo1 extends AbzData {
    private int[] ypr;
    private int[] xyz;
    private int[] imr;
    DataSetNo1() {
        super();
        ypr = xyz = imr = new int[3];
    }
    public int[] getYpr() { return ypr; }
    public int[] getXyz() { return xyz; }
    public int[] getImr() { return imr; }
}
