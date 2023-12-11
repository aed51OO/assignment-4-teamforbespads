/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness;

import TheBusiness.Business.Business;
import TheBusiness.CustomerManagement.CustomerProfile;
import TheBusiness.Data.DataGenerator;
import TheBusiness.Data.DataReader;
import TheBusiness.MarketModel.Channel;
import TheBusiness.MarketModel.ChannelCatalog;
import TheBusiness.MarketModel.Market;
import TheBusiness.MarketModel.MarketCatalog;
import TheBusiness.MarketModel.MarketChannelAssignment;
import TheBusiness.MarketModel.MarketChannelComboCatalog;
import TheBusiness.MarketModel.SolutionOffer;
import TheBusiness.MarketModel.SolutionOfferCatalog;
import TheBusiness.Personnel.Person;
import TheBusiness.ProductManagement.ProductCatalog;
import TheBusiness.SalesManagement.SalesPersonProfile;
import TheBusiness.SolutionOrders.MasterSolutionOrderList;
import TheBusiness.SolutionOrders.SolutionOrder;
import TheBusiness.Supplier.Supplier;
import TheBusiness.Supplier.SupplierDirectory;
import TheBusiness.UserAccountManagement.UserAccount;
import TheBusiness.UserAccountManagement.UserAccountDirectory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author kal bugrara
 */
public class RangePricingApplication {

    
    static DataReader personReader;
    static DataReader customerReader;
    static DataReader productReader;
    static Business business;
    private static Random rand;
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
////        business = ConfigureABusiness.readData();
//        business = new Business("Xerox");
//       //business = ConfigureABusiness.initalizeMarketAssign4();
//        DataGenerator generator = DataGenerator.getInstance();
//        personReader = new DataReader(generator.getPersonDirectoryPath());
//        
//        customerReader = new DataReader(generator.getCustomerDirectoryPath());
//        productReader = new DataReader(generator.getProductDirectoryPath());
//        rand = new Random();
//        
//       readData();
//        
//        
//       //Map<Market,ArrayList<SolutionOrder>> a = business.sortedNegotiatedSolutionsList();
////        System.out.println("output::::::");
//       business.sortedNegotiatedSolutionsList();
//       business.sortedBestCustomers();
//       business.sortedBestSalesPerson();
//       business.marginalRevenueByMarket();
//       business.optimizePrices();
    }
    
    
    public static Business initializeBusiness() throws IOException{
            business = new Business("Xerox");
               //business = ConfigureABusiness.initalizeMarketAssign4();
                DataGenerator generator = DataGenerator.getInstance();
                personReader = new DataReader(generator.getPersonDirectoryPath());

                customerReader = new DataReader(generator.getCustomerDirectoryPath());
                productReader = new DataReader(generator.getProductDirectoryPath());
                rand = new Random();

               readData();


               //Map<Market,ArrayList<SolutionOrder>> a = business.sortedNegotiatedSolutionsList();
        //        System.out.println("output::::::");
               business.sortedNegotiatedSolutionsList();
               business.sortedBestCustomers();
               business.sortedBestSalesPerson();
               business.marginalRevenueByMarket();
               business.optimizePrices();
               return business;
        }
    
   
    
     private static void readData() throws IOException{
       //Create Markets
        MarketCatalog mc = business.getMarketCatalog();
        Market market1 = mc.newMarket("Information Technology");
        Market market2 = mc.newMarket("Medicare");
        Market market3 = mc.newMarket("Education");

        //Create Channels
        ChannelCatalog channelCatalog = business.getChannelCatalog();
        

        Channel channel1 = channelCatalog.newChannel("Website");
        Channel channel2 = channelCatalog.newChannel("Social Media");
        Channel channe13 = channelCatalog.newChannel("Television");
        


        market1.addValidChannel(channel1);
        market1.addValidChannel(channel2);
        market1.addValidChannel(channe13);
        market1.addValidChannel(channel2);
        market2.addValidChannel(channel1);
        market2.addValidChannel(channel2);
        market2.addValidChannel(channe13);
        market3.addValidChannel(channel1);
        market3.addValidChannel(channel2);
        market3.addValidChannel(channe13);
        
        //Create Market Channel Combos
        MarketChannelComboCatalog mccc = business.getMarketChannelComboCatalog();

        MarketChannelAssignment mca1 = mccc.newMarketChannelCombo(market1, channel1);
        MarketChannelAssignment mca2 = mccc.newMarketChannelCombo(market1, channel2);
        MarketChannelAssignment mca3 = mccc.newMarketChannelCombo(market1, channe13);
        MarketChannelAssignment mca4 = mccc.newMarketChannelCombo(market2, channel1);
        MarketChannelAssignment mca5 = mccc.newMarketChannelCombo(market2, channel2);
        MarketChannelAssignment mca6 = mccc.newMarketChannelCombo(market2, channe13);
        MarketChannelAssignment mca7 = mccc.newMarketChannelCombo(market3, channel1);
        MarketChannelAssignment mca8 = mccc.newMarketChannelCombo(market3, channel2);
        MarketChannelAssignment mca9 = mccc.newMarketChannelCombo(market3, channe13);                 
         
        //Create Suppliers
        SupplierDirectory suplierdirectory = business.getSupplierDirectory();
        Supplier supplier1 = suplierdirectory.newSupplier("Lenovo");
        Supplier supplier2 = suplierdirectory.newSupplier("Epson");
        
        
        //Create Employees
         String[] row;
        while((row = personReader.getNextRow()) != null ){
            recordUser(row);
        }
        
         UserAccountDirectory uadirectory = business.getUserAccountDirectory();
        UserAccount ua1 = uadirectory.newUserAccount(business.getSalesPersonDirectory().getSalespersonlist().get(0), "Sales", "XXXX"); /// order products for one of the customers and performed by a sales person

        
        //Create Customers
        while((row = customerReader.getNextRow()) != null ){
            recordCustomer(row);
        }
         
        //Create Products
        while((row = productReader.getNextRow()) != null ){
            recordProduct(row);
        }
        
        //Create Solutions
        
        generateSolutions();
        generateOrders();
        
          
        
      
    }
     
     private static void recordUser(String[] row){
        int userId = Integer.parseInt(row[0]);  
        Person person = business.getPersonDirectory().newPerson(row[1]);
        if(row[2].equals("Sales")){
            business.getSalesPersonDirectory().newSalesPersonProfile(person);
        }else{
            business.getMarketingPersonDirectory().newMarketingPersonProfile(person);
        }
        
//        System.out.println("Employee " + person.toString());
        
    }
     
     
      private static void recordCustomer(String[] row){
        int userId = Integer.parseInt(row[0]);  
        Person person = business.getPersonDirectory().newPerson(row[1]);
   
       CustomerProfile customer = business.getCustomerDirectory().newCustomerProfile(person);
       customer.setMarket(business.getMarketCatalog().getMarket(row[2]));
       customer.setChannel(business.getChannelCatalog().findChannel(row[3]));
        
//        System.out.println("Customer " + person.toString());
        
    }
      
      private static void recordProduct(String[] row){
          SupplierDirectory suplierdirectory = business.getSupplierDirectory();
          Supplier supplier1 = suplierdirectory.findSupplier("Lenovo");
          Supplier supplier2 = suplierdirectory.findSupplier("Epson");
          ProductCatalog productcatalogLenevo = supplier1.getProductCatalog();
          ProductCatalog productCatalogEpson = supplier2.getProductCatalog();
          int fp = Integer.parseInt(row[3]);
          int cp = Integer.parseInt(row[4]);
          int tp = Integer.parseInt(row[5]);
          
          if(row[1].equalsIgnoreCase(supplier1.getName())){
             productcatalogLenevo.newProduct(row[2], fp, cp, tp);
//             System.out.println("Product Lenevo  " + productcatalogLenevo.findProduct(row[2]).toString());
          }else{
              productCatalogEpson.newProduct(row[2], fp, cp, tp);
//              System.out.println("Product Epson  " + productCatalogEpson.findProduct(row[2]).toString());
          
          }
          
           
        
    }
      
       private static void generateSolutions(){
        SupplierDirectory suplierdirectory = business.getSupplierDirectory();
        Supplier supplier1 = suplierdirectory.findSupplier("Lenovo");
        Supplier supplier2 = suplierdirectory.findSupplier("Epson");
        ProductCatalog productcatalogLenevo = supplier1.getProductCatalog();
        ProductCatalog productCatalogEpson = supplier2.getProductCatalog();
        SolutionOfferCatalog solutionOfferCatalog = business.getSolutionOfferCatalog();
        
        MarketChannelComboCatalog mccc = business.getMarketChannelComboCatalog();
        for (int i = 0; i < mccc.getMcalist().size(); i++) {
            MarketChannelAssignment mca = mccc.getMcalist().get(i);

            int j = 0;
            while (j < 5) {
              String solName = "Solution "+ mca.getChannel().getChannelType() + " " + j;
                SolutionOffer solutionOffer = solutionOfferCatalog.newSolutionOffer(mca, solName);
                solutionOffer.addProduct(productcatalogLenevo.getProductList().get(rand.nextInt(0,productcatalogLenevo.getProductList().size())));
                solutionOffer.addProduct(productCatalogEpson.getProductList().get(rand.nextInt(0,productCatalogEpson.getProductList().size())));
                solutionOffer.addProduct(productcatalogLenevo.getProductList().get(rand.nextInt(0,productcatalogLenevo.getProductList().size())));
                
                j++;
            }
        }

    }
       
   private static void generateOrders() {
        MasterSolutionOrderList masterSolution = business.getMasterSolutionOrderList();
        ArrayList<CustomerProfile> customerList = business.getCustomerDirectory().getCustomerlist();
        ArrayList<SolutionOffer> solutionList = business.getSolutionOfferCatalog().getSolutionoffers();
        ArrayList<SalesPersonProfile> salesPersonList = business.getSalesPersonDirectory().getSalespersonlist();

        for (CustomerProfile c : customerList) {
            for (int i = 0; i < solutionList.size(); i++) {
                SolutionOffer sl = solutionList.get(i);
                if (c.getMarket().equals(sl.getMarketchannelcomb().getMarket())) {
//                    System.out.println(c.getMarket());
                   SolutionOrder solOrder1 = masterSolution.newSolutionOrder(sl, rand.nextInt(40000, 80000), c, salesPersonList.get(rand.nextInt(0, salesPersonList.size()-1)));
                }
            }
        }
   }
   
   static Business initialize() {
      
   return business;
   }


}

