package com.abz;

import com.abz.process.AbzData;
import com.abz.process.AbzUserMethod;
import com.abz.serial.SerialComm;
import com.abz.process.AbzDataFactory;

public class Main {

    class DataSet extends AbzData {
        private int[] ypr;
        private int[] xyz;
        private int[] imr;
        DataSet() {
            super();
            ypr = xyz = imr = new int[3];
        }
        public int[] getYpr() { return ypr; }
        public int[] getXyz() { return xyz; }
        public int[] getImr() { return imr; }
    }

    public static SerialComm serial;
    public static AbzDataFactory dataFactory = new AbzDataFactory<>(DataSet.class);

    public static void main(String[] args) {

        AbzUserMethod<DataSet> user = dataSet -> {

        };

        serial = new SerialComm("COM4");
        serial.setBufferUntil((byte)'}');
        serial.openPort(57600);
        dataFactory.put(20, user);

        serial.addSerialEventHandler(data -> {
            byte[] bytes = new byte[data.size()];
            for (int i = 0; i < data.size(); i++) bytes[i] = data.get(i);
            String cmd = new String(bytes);
            System.out.println(cmd);
            dataFactory.perform(cmd);
        });

    }
}
