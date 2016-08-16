package com.abz;

import java.util.List;

/**
 * This class contains additional methods to aid the data processing.
 *
 * @author Phan Vu Hoang
 */
public class Tools {
    public static String getStringFromByteList(List<Byte> byteList) {
        byte[] bytes = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++) bytes[i] = byteList.get(i);
        return new String(bytes);
    }
}
