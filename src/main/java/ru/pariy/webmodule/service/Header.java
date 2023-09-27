package ru.pariy.webmodule.service;

import ru.pariy.webmodule.utils.HexUtils;

public class Header {
    private final byte[] magicByte = HexUtils.hexStringToByteArray("FFBBCCDD");
    private byte[] length;

    public void setLength(byte[] length) {
        this.length = length;
    }

    public byte[] getMagicByte() {
        return magicByte;
    }

    public byte[] getLength() {
        return length;
    }
}
