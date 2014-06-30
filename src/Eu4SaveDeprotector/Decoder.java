/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Eu4SaveDeprotector;

import java.io.IOException;

/**
 *
 * @author Dibujante
 */
public class Decoder {
    private static LoadFile _loadFile = new LoadFile();
    private static SaveFile _saveFile = new SaveFile();
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("You must specify a file to unprotect.");
            return;
        }
        byte[] input = _loadFile.Read("encoded.eu4");
        int streamPosition = 0;
        streamPosition = ReadUntil(input, new byte[] { (byte)155, (byte)44, (byte)1 }, streamPosition);
        streamPosition = ReadUntil(input, new byte[] { (byte)0, (byte)12, (byte)0 }, streamPosition+1);
        streamPosition = ReadUntil(input, new byte[] { (byte)0, (byte)12, (byte)0 }, streamPosition+1);
        streamPosition = ReadUntil(input, new byte[] { (byte)0, (byte)12, (byte)0 }, streamPosition+1);
        streamPosition = ReadUntil(input, new byte[] { (byte)0, (byte)12, (byte)0 }, streamPosition+1);
        streamPosition = ReadUntil(input, new byte[] { (byte)0, (byte)12, (byte)0 }, streamPosition+1);
        streamPosition = ReadUntil(input, new byte[] { (byte)0, (byte)12, (byte)0 }, streamPosition+1);
        streamPosition = ReadUntil(input, new byte[] { (byte)0, (byte)12, (byte)0 }, streamPosition+1);
        streamPosition = ReadUntil(input, new byte[] { (byte)0, (byte)12, (byte)0 }, streamPosition+1);
        streamPosition = ReadUntil(input, new byte[] { (byte)0, (byte)12, (byte)0 }, streamPosition+1);
        streamPosition = ReadUntil(input, new byte[] { (byte)0, (byte)12, (byte)0 }, streamPosition+1);
        input[streamPosition + 3] = 1;
        _saveFile.Save(input, args[1].substring(0, args[1].length()-4) + "_decoded.eu4");
    }
    private static int ReadUntil(byte[] stream, byte[] target, int start) {
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
