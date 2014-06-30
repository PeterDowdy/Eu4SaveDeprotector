package Eu4SaveDeprotector;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Decoder {
    private static final LoadFile _loadFile = new LoadFile();
    public byte[] Deprotect(File file) throws IOException, Exception {
        byte[] input = _loadFile.Read(file);
        byte[] fileType = Arrays.copyOfRange(input, 0, 6);
        if (!"EU4bin".equals(new String(fileType))) throw new Exception("This file does not seem to be protected.");
        try {
        int streamPosition = 0;
        streamPosition = ReadUntil(input, new byte[] { (byte)155, (byte)44, (byte)1 }, streamPosition);
        if (streamPosition == -1) throw new Exception();
        streamPosition = ReadUntil(input, new byte[] { (byte)0, (byte)12, (byte)0 }, streamPosition+1);
        if (streamPosition == -1) throw new Exception();
        streamPosition = ReadUntil(input, new byte[] { (byte)0, (byte)12, (byte)0 }, streamPosition+1);
        if (streamPosition == -1) throw new Exception();
        streamPosition = ReadUntil(input, new byte[] { (byte)0, (byte)12, (byte)0 }, streamPosition+1);
        if (streamPosition == -1) throw new Exception();
        streamPosition = ReadUntil(input, new byte[] { (byte)0, (byte)12, (byte)0 }, streamPosition+1);
        if (streamPosition == -1) throw new Exception();
        streamPosition = ReadUntil(input, new byte[] { (byte)0, (byte)12, (byte)0 }, streamPosition+1);
        if (streamPosition == -1) throw new Exception();
        streamPosition = ReadUntil(input, new byte[] { (byte)0, (byte)12, (byte)0 }, streamPosition+1);
        if (streamPosition == -1) throw new Exception();
        streamPosition = ReadUntil(input, new byte[] { (byte)0, (byte)12, (byte)0 }, streamPosition+1);
        if (streamPosition == -1) throw new Exception();
        streamPosition = ReadUntil(input, new byte[] { (byte)0, (byte)12, (byte)0 }, streamPosition+1);
        if (streamPosition == -1) throw new Exception();
        streamPosition = ReadUntil(input, new byte[] { (byte)0, (byte)12, (byte)0 }, streamPosition+1);
        if (streamPosition == -1) throw new Exception();
        streamPosition = ReadUntil(input, new byte[] { (byte)0, (byte)12, (byte)0 }, streamPosition+1);
        if (streamPosition == -1) throw new Exception();
        input[streamPosition + 3] = 1;
        return input;
        } catch (Exception ex) {
            throw new Exception("The gamplay options section could not be found.\nThis file may be corrupt, or a newer version.");
        }
    }
    private int ReadUntil(byte[] stream, byte[] target, int start) {
        for (int i = start; i < stream.length; i++) {
            Boolean found = true;
            for (int j = 0; j < target.length; j++) {
                if ((i + j) > stream.length) return -1;
                if (stream[i + j] != target[j]) {
                    found = false;
                    break;
                }
            }
            if (found) return i;
        }
        return -1;
    }
}
