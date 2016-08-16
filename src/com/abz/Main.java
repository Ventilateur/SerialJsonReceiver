package com.abz;

import com.abz.serial.SerialComm;
import com.abz.process.AbzDataFactory;

import java.util.Arrays;

import static com.abz.Tools.getStringFromByteList;

/**
 * This class is an example of how to combine <code>SerialComm</code> and <code>AbzDataFactory</code> to make a serial
 * JSON receiver.
 *
 * @author Phan Vu Hoang
 */
public class Main {

    public static final byte END_OF_FRAME = 10;
    public static final int BAUD_RATE = 57600;
    public static final String PORT = "COM7";

    public static SerialComm serial;
    public static AbzDataFactory<DataSetNo1> dataFactory = new AbzDataFactory<>(DataSetNo1.class);

    public static void main(String[] args) {

        serial = new SerialComm(PORT);
        serial.setBufferUntil(END_OF_FRAME);
        serial.openPort(BAUD_RATE);

        dataFactory.put(20, dataSet -> {
            System.out.println("ID = " + dataSet.getId());
            System.out.println("YPR = " + Arrays.toString(dataSet.getYpr()));
            System.out.println("XYZ = " + Arrays.toString(dataSet.getXyz()));
            System.out.println("IMR = " + Arrays.toString(dataSet.getImr()));
            System.out.println();
        });

        serial.addSerialEventHandler(data -> {
            String cmd = getStringFromByteList(data);
            dataFactory.perform(cmd);
        });
    }
}
