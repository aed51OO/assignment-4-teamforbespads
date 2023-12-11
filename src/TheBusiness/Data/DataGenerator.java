/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TheBusiness.Data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author sangramshinde
 */
public class DataGenerator {
    
     private static DataGenerator instance;
    
    private FileWriter writer;
    private File file;
    
    private final Random rand;
    
    private final int userIdsRange;
    private final int commentIdsRange;
    
    private final int likeRange;
    private final int postIdsRange;
    private final int ratingRange;

    private final String COMMENT_HEADER = "Person_id, Type";
    private final String USER_HEADER = "User-Id,First-Name,Last-Name,Rating";
    private final String LINE_BREAK = "\n";
    
    private final String USER_FILE_PATH = "./PersonCatalogue.csv";
    private final String COMMENT_FILE_PATH = "./CommentData.csv";
    
    //New Code
    private final int customerIdsRange;
    private final int salesIdsRange;
    private final int employeeIdRange;
    private final int productRange;
    private final int floorPrice;
    private final int floorPriceBound;
    private final int cpRange;
    
    
    private final String PERSON_HEADER = "srno, Person_id, Type";
    private final String PRODUCT_HEADER = "srno, Supplier, Product, Floor Price, Ceil Price, Target Price";
    private final String CUSTOMER_HEADER = "srno, Customer_Name, Market, Channel";
    private final String PERSON_DIR_PATH = "./PersonDirectory.csv";
    private final String CUSTOMER_DIR_PATH = "./CustomerDirectory.csv";
    private final String PRODUCT_DIR_PATH = "./ProductDirectory.csv";
    
    
    
    
    
    private DataGenerator() throws IOException {
                
        rand = new Random();
        
        userIdsRange = 10;
        commentIdsRange = 1000;
        likeRange = 200;
        postIdsRange = 40;
        ratingRange = 300;
        productRange = 400;
        customerIdsRange = 10;
        salesIdsRange = 5;
        employeeIdRange = 10;
        
        
        floorPrice = 12000;
        cpRange = 25000;
        floorPriceBound = 16000;
        
        
        generatePersonFile();
        generateCustomerFile();
        generateProductFile();
        
      
        
    }
    
    public static DataGenerator getInstance() throws IOException{
        if(instance == null)
        {
            instance = new DataGenerator();
        }
        return instance;
    }
    
    
    private void generatePersonFile() throws IOException{
         //generate Order file
        try {
            file = new File(PERSON_DIR_PATH);
            if(file.exists()){
                file.delete();
            }
            file.createNewFile();
            System.out.println("New Person File Created");
            writer = new FileWriter(file);
        
            writer.append(PERSON_HEADER);
            writer.append(LINE_BREAK);
            
            generatePersonColumns();   
            
        }finally{
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
    
    
    }
    }
    
    
    private void generateCustomerFile() throws IOException{
         //generate Order file
        try {
            file = new File(CUSTOMER_DIR_PATH);
            if(file.exists()){
                file.delete();
            }
            file.createNewFile();
            System.out.println("New Customer File Created");
            writer = new FileWriter(file);
        
            writer.append(CUSTOMER_HEADER);
            writer.append(LINE_BREAK);
            
            generateCustomerColumns();   
            
        }finally{
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
    
    
    }
    }
    
    
     private void generateProductFile() throws IOException{
         //generate Order file
        try {
            file = new File(PRODUCT_DIR_PATH);
            if(file.exists()){
                file.delete();
            }
            file.createNewFile();
            System.out.println("New Product File Created");
            writer = new FileWriter(file);
        
            writer.append(PRODUCT_HEADER);
            writer.append(LINE_BREAK);
            
            generateProductColumns();   
            
        }finally{
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
    
    
    }
    }
    
    
    
    
    
    

    
    
      private void generatePersonColumns() throws IOException{
          String[] customers = {"Dell", "IBM", "Microsoft", "Northeastern", "Mass General", "Boston University"};
          String [] employees = {"Sales", "Marketing"};
          
   
          int srNo = 1;
          
          int iterationsNew = 10;
          while(iterationsNew > 0){
              
              String currentEmployee = "Employee" + iterationsNew;
              String column = srNo+ "," + currentEmployee + "," + employees[rand.nextInt(employees.length)];
              
              writer.append(column);
               writer.append(LINE_BREAK);
              srNo++;
              iterationsNew --;
          }
          
          
      
      }
      
      private void generateCustomerColumns() throws IOException{
          String[] customers = {"Dell", "IBM", "Microsoft", "JP Morgan", "Google", "Accenture", "Northeastern University", "Boston University" , "Umass Boston" , "Brigham and Women's", "Mass General"};
          String[] markets = {"Information Technology", "Medicare", "Education"};
          String[] channel = {"Website", "Social Media", "Television"};
          
        
          
          int iterations = 0;
          int i = 0;
          int srNo = 1;
          
          while(iterations < customers.length){
              
             
              String currentCustomer = customers[iterations];
              String currentMarket = markets[rand.nextInt(markets.length)];
              String currentChannel = channel[rand.nextInt(channel.length)];

              String column = srNo + "," + currentCustomer + "," + currentMarket +","+ currentChannel;
              
              writer.append(column);
              writer.append(LINE_BREAK);

              srNo++;
              iterations++;
          }
             
          
      
      }
      
      
      private void generateProductColumns() throws IOException{
          
          String Products[] = {"Printer", "Scanner", "Router", "Low Tonner Scanner", "Colour Scanner", "Colour Copier", "Colour Printer"};
          String Suppliers[] = {"Lenovo", "Epson"};
          int iterations = 1;
          int srNo = 1;
          
          while(iterations < 100){
          
              String currenProduct = Products[rand.nextInt(Products.length)] +" " + iterations;
              String currentSupplier = Suppliers[rand.nextInt(Suppliers.length)];
              int fp = rand.nextInt(floorPrice, floorPriceBound);
              int cp = rand.nextInt(fp, cpRange + 100);
              int tp = rand.nextInt(fp, cp);
              
              
                String column = srNo + "," + currentSupplier + "," + currenProduct +","+ fp +"," +cp +","+tp;
              
              writer.append(column);
              writer.append(LINE_BREAK);
              srNo ++;
              iterations ++;

              
          
          
          }
          
         
          
          
          
      
      
      
      }
      
      
      
      
      
      
      
    public String getPersonDirectoryPath(){
        return PERSON_DIR_PATH;
    }
    
    
    public String getCustomerDirectoryPath(){
        return CUSTOMER_DIR_PATH;
    }
    
    public String getProductDirectoryPath(){
        return PRODUCT_DIR_PATH;
    }
    
   

}
