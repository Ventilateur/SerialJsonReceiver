package com.abz;

import com.abz.process.AbzData;
import com.abz.process.AbzUserMethod;
import com.abz.serial.SerialComm;
import com.abz.process.AbzDataFactory;

import java.util.List;

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
    public static AbzDataFactory<DataSet> dataFactory = new AbzDataFactory<>(DataSet.class);

    public static String getStringFromByteList(List<Byte> byteList) {
        byte[] bytes = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++) bytes[i] = byteList.get(i);
        return new String(bytes);
    }

    public static void main(String[] args) {

        serial = new SerialComm("COM4");
        serial.setBufferUntil((byte)'}');
        serial.openPort(57600);

        dataFactory.put(20, dataSet -> {
            /* Do something here */
        });

        serial.addSerialEventHandler(data -> {
            String cmd = getStringFromByteList(data);
            System.out.println(cmd);
            dataFactory.perform(cmd);
        });

    }
}
