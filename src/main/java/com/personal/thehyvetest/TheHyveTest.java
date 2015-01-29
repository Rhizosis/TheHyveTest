package com.personal.thehyvetest;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.personal.thehyvetest.parsers.HyveDecoder;

/**
 *
 * @author Bernd van der Veen
 */
public class TheHyveTest {

    public static void main(String[] args) {
        boolean useTrivial = false;
        
        if(System.getenv("USE_TRIVIAL_IMPLEMENTATION") != null){
            String value = System.getenv("USE_TRIVIAL_IMPLEMENTATION");
            if (value.equals("1")) {
                useTrivial = true;
            }
        }
            
        BufferedInputStream in = new BufferedInputStream(System.in);
        HyveDecoder decoder = new HyveDecoder(in);
        try{
            // Was trivial encoding argumented?
            if(useTrivial){
                // Use simple method
                decoder.setTrivialRecoding(true);
            }
            
            // Start decoding / recoding.
            decoder.decode();
        }catch(FileNotFoundException FNE){
            System.err.println("Not found "+ FNE.toString());
            System.exit(1);
        }catch(IOException Ex){
            System.out.println("Fatal IOException " + Ex.toString());
            System.exit(1);
        }
    }    
}
