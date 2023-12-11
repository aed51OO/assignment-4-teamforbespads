/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TheBusiness.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author kunaltibe
 */
public class DataReader {
    
     private BufferedReader reader;
    private String[] header;
    
    public DataReader(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if(!file.exists())
            throw new FileNotFoundException("File not found at the path specified: "+fileName);
        reader = new BufferedReader(new FileReader(file));
    }
    
    public String[] getNextRow() throws IOException{
        if (header == null)
            header = getFileHeader();
        String line = "";
        if((line = reader.readLine()) != null){
            String[] rows = line.split(",");
            return rows;
        }
        return null;
    }
    
    public String[] getFileHeader() throws IOException{
        if(header == null){
            String line = "";
            if((line = reader.readLine()) != null){
                String[] rows = line.split(",");
                header = rows;
            }
        }
        return header;
    }
    
}
