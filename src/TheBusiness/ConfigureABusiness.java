/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness;

import MarketingManagement.MarketingPersonDirectory;
import MarketingManagement.MarketingPersonProfile;
import TheBusiness.Business.Business;
import TheBusiness.MarketModel.ChannelCatalog;
import TheBusiness.CustomerManagement.CustomerDirectory;
import TheBusiness.CustomerManagement.CustomerProfile;
import TheBusiness.Data.DataGenerator;
import TheBusiness.Data.DataReader;
import TheBusiness.MarketModel.Channel;
import TheBusiness.MarketModel.Market;
import TheBusiness.MarketModel.MarketCatalog;
import TheBusiness.MarketModel.MarketChannelAssignment;
import TheBusiness.MarketModel.MarketChannelComboCatalog;
import TheBusiness.MarketModel.SolutionOffer;
import TheBusiness.MarketModel.SolutionOfferCatalog;
import TheBusiness.OrderManagement.MasterOrderList;
import TheBusiness.SolutionOrders.MasterSolutionOrderList;
import TheBusiness.OrderManagement.Order;
import TheBusiness.OrderManagement.OrderItem;
import TheBusiness.Personnel.Person;
import TheBusiness.Personnel.PersonDirectory;
import TheBusiness.ProductManagement.Product;
import TheBusiness.ProductManagement.ProductSummary;
import TheBusiness.ProductManagement.ProductCatalog;
import static TheBusiness.RangePricingApplication.business;
import static TheBusiness.RangePricingApplication.customerReader;
import static TheBusiness.RangePricingApplication.personReader;
import static TheBusiness.RangePricingApplication.productReader;
import TheBusiness.SalesManagement.SalesPersonDirectory;
import TheBusiness.SalesManagement.SalesPersonProfile;
import TheBusiness.SolutionOrders.SolutionOrder;
import TheBusiness.Supplier.Supplier;
import TheBusiness.Supplier.SupplierDirectory;
import TheBusiness.UserAccountManagement.UserAccount;
import TheBusiness.UserAccountManagement.UserAccountDirectory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author kal bugrara
 */
class ConfigureABusiness {
    
     static DataReader personReader;
    static DataReader customerReader;
    static DataReader productReader;
    private static Random rand;
    
    
    

    
    static Business initalizeMarketAssign4(){
        Business business = new Business("Xerox");
        
        PersonDirectory persondirectory = business.getPersonDirectory();
// person representing sales organization        
        Person person1 = persondirectory.newPerson("person1");
        Person person2 = persondirectory.newPerson("person2");
        Person person3 = persondirectory.newPerson("person3");
        Person person4 = persondirectory.newPerson("person4");
        Person person5 = persondirectory.newPerson("person5");
        Person person6 = persondirectory.newPerson("person6");
        Person person7 = persondirectory.newPerson("person7");
        Person person8 = persondirectory.newPerson("person8");


// Create Customers
        CustomerDirectory customedirectory = business.getCustomerDirectory();
        CustomerProfile customer1 = customedirectory.newCustomerProfile(person1);
        CustomerProfile customer2 = customedirectory.newCustomerProfile(person2);
        CustomerProfile customer3 = customedirectory.newCustomerProfile(person3);
        CustomerProfile customer4 = customedirectory.newCustomerProfile(person4);


// Create Sales people
        SalesPersonDirectory salespersondirectory = business.getSalesPersonDirectory();
        SalesPersonProfile salesPerson1 = salespersondirectory.newSalesPersonProfile(person5);
        SalesPersonProfile salesPerson2 = salespersondirectory.newSalesPersonProfile(person6);
        SalesPersonProfile salesPerson3 = salespersondirectory.newSalesPersonProfile(person7);

        MarketCatalog mc = business.getMarketCatalog();
        Market market1 = mc.newMarket("market1");
        Market market2 = mc.newMarket("market2");


        ChannelCatalog channelCatalog = business.getChannelCatalog();

        Channel channel1 = channelCatalog.newChannel("channel1");
        Channel channel2 = channelCatalog.newChannel("channel2");


        market1.addValidChannel(channel1);
        market1.addValidChannel(channel2);
        market2.addValidChannel(channel1);
        market2.addValidChannel(channel2);


        MarketChannelComboCatalog mccc = business.getMarketChannelComboCatalog();

        MarketChannelAssignment mca1 = mccc.newMarketChannelCombo(market1, channel1);
        MarketChannelAssignment mca2 = mccc.newMarketChannelCombo(market1, channel2);
        MarketChannelAssignment mca3 = mccc.newMarketChannelCombo(market2, channel1);
        MarketChannelAssignment mca4 = mccc.newMarketChannelCombo(market2, channel2);

        
        SupplierDirectory suplierdirectory = business.getSupplierDirectory();

        Supplier supplier1 = suplierdirectory.newSupplier("Lenovo");
        ProductCatalog productcatalog = supplier1.getProductCatalog();
        Product p1 = productcatalog.newProduct("Scanner 3  1", 2000, 16500, 10000);
        Product p2 = productcatalog.newProduct("Scanner 4", 10000, 25000, 16500);
        Product p3 = productcatalog.newProduct("Printer 2", 22000, 40000, 36500);
        Product p4 = productcatalog.newProduct("Photocopier 2 ", 30000, 70000, 50000);
        
        SolutionOfferCatalog solutionOfferCatalog = business.getSolutionOfferCatalog();
        SolutionOffer solOffer1 = solutionOfferCatalog.newSolutionOffer(mca1);
        solOffer1.addProduct(p1);
        solOffer1.addProduct(p2);
        solOffer1.addProduct(p3);
        
        SolutionOffer solOffer2 = solutionOfferCatalog.newSolutionOffer(mca2);
        solOffer2.addProduct(p1);
        solOffer2.addProduct(p3);
        
        SolutionOffer solOffer3 = solutionOfferCatalog.newSolutionOffer(mca3);
        solOffer3.addProduct(p1);
        solOffer3.addProduct(p2);
        solOffer3.addProduct(p4);
        
        SolutionOffer solOffer4 = solutionOfferCatalog.newSolutionOffer(mca4);
        solOffer4.addProduct(p1);
        solOffer4.addProduct(p2);
        solOffer4.addProduct(p3);
        solOffer4.addProduct(p4);
        
        MasterSolutionOrderList masterSolution = business.getMasterSolutionOrderList();
        SolutionOrder solOrder1 = masterSolution.newSolutionOrder(solOffer1, 20000, customer1, salesPerson1);
        
        SolutionOrder solOrder2 = masterSolution.newSolutionOrder(solOffer2, 30000, customer2, salesPerson1);
        
        SolutionOrder solOrder3 = masterSolution.newSolutionOrder(solOffer3, 100000, customer3, salesPerson2);
        
        SolutionOrder solOrder4 = masterSolution.newSolutionOrder(solOffer4, 66000, customer4, salesPerson3);


        return business;
    } 
    
    
      static Business readData() throws IOException{
          
          Business business = new Business("Xerox");
          
           DataGenerator generator = DataGenerator.getInstance();
        personReader = new DataReader(generator.getPersonDirectoryPath());
        
        customerReader = new DataReader(generator.getCustomerDirectoryPath());
        productReader = new DataReader(generator.getProductDirectoryPath());
        rand = new Random();
          
          
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
        
        
        
        return business;
    
      
    }
     
     private static void recordUser(String[] row){
        int userId = Integer.parseInt(row[0]);  
        Person person = business.getPersonDirectory().newPerson(row[1]);
        if(row[2].equals("Sales")){
            business.getSalesPersonDirectory().newSalesPersonProfile(person);
        }else{
            business.getMarketingPersonDirectory().newMarketingPersonProfile(person);
        }
        
        System.out.println("Employee " + person.toString());
        
    }
     
     
      private static void recordCustomer(String[] row){
        int userId = Integer.parseInt(row[0]);  
        Person person = business.getPersonDirectory().newPerson(row[1]);
   
       CustomerProfile customer = business.getCustomerDirectory().newCustomerProfile(person);
       customer.setMarket(business.getMarketCatalog().getMarket(row[2]));
       customer.setChannel(business.getChannelCatalog().findChannel(row[3]));
        
        System.out.println("Customer " + person.toString());
        
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
             System.out.println("Product Lenevo  " + productcatalogLenevo.findProduct(row[2]).toString());
          }else{
              productCatalogEpson.newProduct(row[2], fp, cp, tp);
              System.out.println("Product Epson  " + productCatalogEpson.findProduct(row[2]).toString());
          
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
                String solName = "Solution " + j;
                SolutionOffer solutionOffer = solutionOfferCatalog.newSolutionOffer(mca, solName);
                solutionOffer.addProduct(productcatalogLenevo.getProductList().get(rand.nextInt(0,productcatalogLenevo.getProductList().size())));
                solutionOffer.addProduct(productcatalogLenevo.getProductList().get(rand.nextInt(0,productCatalogEpson.getProductList().size())));
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
                    SolutionOrder solOrder1 = masterSolution.newSolutionOrder(sl, rand.nextInt(40000, 80000), c, salesPersonList.get(rand.nextInt(0, salesPersonList.size() - 1)));
                }
            }
        }}
    
    
    
}
