package com.personal.thehyvetest.parsers;

import com.personal.thehyvetest.io.HyveInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import com.personal.thehyvetest.objects.ByteBlock;

/**
 *
 * @author Bernd van der Veen
 */
public class HyveDecoder{
     protected HyveInputStream his = null;
     protected final InputStream is = null;
     private String decodedMsg = "";
     private boolean trivialRecoding = false;
     
     public HyveDecoder(InputStream is){
         if(is == null){
             System.err.println("InputStream is null.");
             System.exit(1);
         }
         his = new HyveInputStream(is);
     }
     
     public void decode() throws IOException, EOFException{
         while(his.available() > 1){
            // Instantiate new byteblock holding the pairs.
            ByteBlock bb = new ByteBlock();
            bb.setIndicator(his.readUnsignedByte());
            bb.setValue(his.readUnsignedByte());
            
            // Indicates a character
            if(bb.isPlainChar()){
                decodedMsg += bb.getValueAsString();
                // Output string value to System.out (stdout)
                System.out.print(bb.getValueAsString());
                // Always write this string as bytes to System.err (stderr) regardless
                // of recoding method.
                System.err.write(bb.getByteArray());
            }else{
                // Indicates an offset byteblock
                int startIdx = (decodedMsg.length() - bb.getIndicator());
                int startValue = bb.getIndicator();
                int endValue = Integer.parseInt(bb.getValueAsString());
                int endIdx = startIdx + Integer.parseInt(bb.getValueAsString());
                
                // Some validity checks.
                if(startValue > decodedMsg.length() || startValue == -1){
                    System.out.println("\nInvalid offset value ("+startValue+"). Exiting...");
                    System.exit(1);                    
                }
                if(endValue < 1){
                    System.out.println("\nInvalid offset length value ("+endValue+"). Exiting...");
                    System.exit(1);
                }
                
                if(endIdx > decodedMsg.length()){
                    System.out.println("\nRepeat offset ("+ endIdx +") is greater than current decoded message length ("+decodedMsg.length()+"). Exiting...");
                    System.exit(1);
                }

                String append = decodedMsg.substring(startIdx, endIdx);
                bb.setEncodesForString(append);
                decodedMsg += append;
                
                // Append normal string representation to output.
                System.out.print(append);

                // In case we use trivial encoding, write the byte array it encodes 
                // for to System.err (stderr)
                // Otherwise just write the byte array to System.err (stderr)
                if(trivialRecoding){
                    for(byte b : bb.getEncodesForString()){
                        System.err.write(new byte[]{(byte) b});
                    }
                }else{
                    System.err.write(bb.getByteArray());
                }
            }
         }
     }
     
     public void setTrivialRecoding(boolean trivialRecoding){
         this.trivialRecoding = trivialRecoding;
     }
}
