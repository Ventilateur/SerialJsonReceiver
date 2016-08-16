package com.abz;

import com.abz.process.AbzData;
import com.abz.process.AbzDataFactory;
import com.abz.process.AbzUserMethod;
import com.abz.serial.SerialComm;

import static com.abz.Tools.getStringFromByteList;

/**
 * An API for JSON receiver via serial port.
 *
 * @param <T> A data set class which extends <code>AbzData</code> base class
 * @author Phan Vu Hoang
 */
public class JsonSerialReceiver<T extends AbzData> {

    private static final byte DEFAULT_END_OF_FRAME = 10; // aka new line character

    private SerialComm serialComm;
    private AbzDataFactory<T> dataFactory;

    /**
     * Constructor.
     *
     * @param dataSetClass a <code>Class</code> instance which must be passed as follow: <code>dataSet.class</code>
     *                     where <code>dataSet</code> is a class name.
     */
    public JsonSerialReceiver(Class<T> dataSetClass) {
        dataFactory = new AbzDataFactory<>(dataSetClass);
    }

    /**
     * Set end-of-frame character, it is set to EOL by default.
     *
     * @param character end-of-frame character
     */
    public void setEndOfFrame(char character) {
        serialComm.setBufferUntil((byte)character);
    }

    /**
     * Map a method to an ID.
     *
     * @param id the integer value of ID
     * @param method an object of <code>AbzUserMethod</code> interface
     */
    public void mapMethodToId(int id, AbzUserMethod<T> method) {
        dataFactory.put(id, method);
    }

    /**
     * Connect to a specific serial port.
     *
     * @param port name of the port to connect
     * @param baudRate baud rate value
     */
    public void connect(String port, int baudRate) {
        serialComm = new SerialComm(port);
        serialComm.openPort(baudRate);
        serialComm.setBufferUntil(DEFAULT_END_OF_FRAME);
        serialComm.addSerialEventHandler(data -> {
            String cmd = getStringFromByteList(data);
            dataFactory.perform(cmd);
        });
    }
}
