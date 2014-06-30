/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Eu4SaveDeprotector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Dibujante
 */
public class LoadFile {

    byte[] Read(String encodedeu4) throws IOException {
        return Files.readAllBytes(Paths.get(encodedeu4));
    }
    
}
