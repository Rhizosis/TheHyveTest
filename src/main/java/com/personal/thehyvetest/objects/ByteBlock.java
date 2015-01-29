package com.personal.thehyvetest.objects;

import java.util.ArrayList;

/**
 *
 * @author Bernd van der Veen
 */
// Byte block which stores 
public class ByteBlock {
    // Variables
    private int indicator;
    private int indByte;
    private int value;
    private boolean isPlainChar;
    private String encodesForString; 
   
    // Getters and setters
    public void setIndicator(int indicator){
        this.indByte = indicator;
        this.indicator = Character.getNumericValue((char) indicator);
        
        if(this.indicator == 0){
            isPlainChar = true;
        }
    }
    
    public int getIndicator(){
        return indicator;
    }
   
    public void setValue(int value){
        this.value = value;
    }
    
    public int getValue(){
        return value;
    }
    
    // Return ByteBlock as normal byte[]
    public byte[] getByteArray(){
        return new byte[]{(byte) (indByte), (byte) value};
    }

    // In case this ByteBlock encodes for an offset
    public void setEncodesForString(String encodesForString){
        this.encodesForString = encodesForString;
    }
    
    // In case this ByteBlock encodes for an offset,
    // we should be able to retrieve the string it encodes for as a byte array.
    // This is used for the trivial re-encoding.
    public ArrayList<Byte> getEncodesForString(){
        ArrayList<Byte> byteList = new ArrayList<>();
        
        byte[] asBytes = encodesForString.getBytes();
        
        for(byte b : asBytes){
            // Always add the '0' indicator byte
            byteList.add((byte) 0x30);
            // Java int's are always signed, convert to unsigned and add.
            int uByteVal = b & 0xFF;
            byteList.add((byte) uByteVal);
        }
        
        return byteList;
    }
    
    public String getValueAsString(){
        if(isPlainChar){
            boolean isAscii = this.value < 128;
            if(isAscii){
                return Character.toString((char) this.value);
            }else{
                // Set value to value for '?' ascii = 63
                this.setValue(63);
                return "?";
            }
        }else{
            // Parse the value to get offset length value. Returns -1 if invalid.
            return Integer.toString(Character.getNumericValue((char) this.value));
        }
    }
    
    public boolean isPlainChar(){
        return isPlainChar;
    }
}
