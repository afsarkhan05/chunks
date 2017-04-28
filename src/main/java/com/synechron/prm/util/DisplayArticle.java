package com.synechron.prm.util;

import java.io.File;  
import java.util.Scanner;  
  
public class DisplayArticle {  
      
    private String fileOutput; 

    
    public DisplayArticle(){
    	
    }

    
    public String getFileOutput() {  
        return fileOutput;  
    }  
      
    public void display() {  
        display("C:/jtts4_sapi5.log");  
    }  
      
    public void display(String path) {  
        Scanner scan = null;  
        String s2 = path;  
        StringBuffer buffer;  
        try {  
            buffer = new StringBuffer();  
  
            scan = new Scanner(new File(path), "UTF-8");  
  
            String readdata = "";  
            while (scan.hasNext() && (readdata = scan.nextLine()) != null) {  
  
                buffer.append(readdata).append('\n');  
            }  
            this.fileOutput = buffer.toString();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if(scan != null) {  
                scan.close();  
                scan = null;  
            }  
        }  
    }  
  
}
