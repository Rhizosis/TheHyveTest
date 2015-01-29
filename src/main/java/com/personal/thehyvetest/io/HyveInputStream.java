package com.personal.thehyvetest.io;

import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Bernd van der Veen
 */
public class HyveInputStream extends FilterInputStream {
  
  // Constructor  
  public HyveInputStream(InputStream in) {
    super(in);
  }

  // Read a single unsigned byte from InputStream
  public int readUnsignedByte() throws EOFException, IOException{
    try{
        int temp = in.read();
        return temp;
    }catch(EOFException ex){
        throw ex;
    }catch(IOException io){
        throw  io;
    }
  }
}
