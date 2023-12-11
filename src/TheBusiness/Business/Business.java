/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness.Business;

import MarketingManagement.MarketingPersonDirectory;
import TheBusiness.MarketModel.ChannelCatalog;
import java.util.ArrayList;
import TheBusiness.CustomerManagement.CustomerDirectory;
import TheBusiness.MarketModel.Market;
import TheBusiness.MarketModel.MarketCatalog;
import TheBusiness.MarketModel.MarketChannelComboCatalog;
import TheBusiness.MarketModel.SolutionOffer;
import TheBusiness.OrderManagement.MasterOrderList;
import TheBusiness.Personnel.PersonDirectory;
import TheBusiness.ProductManagement.ProductSummary;
import TheBusiness.ProductManagement.ProductsReport;
import TheBusiness.MarketModel.SolutionOfferCatalog;
import TheBusiness.SolutionOrders.MasterSolutionOrderList;
import TheBusiness.SalesManagement.SalesPersonDirectory;
import TheBusiness.SolutionOrders.SolutionOrder;
import TheBusiness.Supplier.Supplier;
import TheBusiness.Supplier.SupplierDirectory;
import TheBusiness.UserAccountManagement.UserAccountDirectory;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kal bugrara
 */
public class Business {

    String name;
    PersonDirectory persondirectory; //all people profiles regardless of the role
    MasterOrderList masterorderlist;
    CustomerDirectory customers;
    SupplierDirectory suppliers;
    MarketCatalog marketcatalog;
    ChannelCatalog channelcatalog;
    MarketChannelComboCatalog marketChannelComboCatalog;
    SolutionOfferCatalog solutionoffercatalog;
    CustomerDirectory customerdirectory;
    // EmployeeDirectory employeedirectory;
    SalesPersonDirectory salespersondirectory;
    UserAccountDirectory useraccountdirectory;
    MarketingPersonDirectory marketingpersondirectory;
    MasterSolutionOrderList mastersolutionorderlist;


    public Business(String n) {
        name = n;
        masterorderlist = new MasterOrderList();
        suppliers = new SupplierDirectory();

        persondirectory = new PersonDirectory();
        customerdirectory = new CustomerDirectory(this);
        salespersondirectory = new SalesPersonDirectory(this);
        useraccountdirectory = new UserAccountDirectory();
        marketingpersondirectory = new MarketingPersonDirectory(this);

        marketcatalog = new MarketCatalog();

        channelcatalog = new ChannelCatalog();

//        Channel c = channelcatalog.newChannel("tv");
//        market.addValidChannel(c);
//        c = channelcatalog.newChannel("");

        marketChannelComboCatalog = new MarketChannelComboCatalog();
//        MarketChannelAssignment mca2 = marketChannelComboCatalog.newMarketChannelCombo(market, c);

        solutionoffercatalog = new SolutionOfferCatalog();
        mastersolutionorderlist  = new MasterSolutionOrderList();

    }
  
    public int getSalesVolume() {
        return masterorderlist.getSalesVolume();

    }

    public PersonDirectory getPersonDirectory() {
        return persondirectory;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return useraccountdirectory;
    }

    public MarketingPersonDirectory getMarketingPersonDirectory() {
        return marketingpersondirectory;
    }

    public SupplierDirectory getSupplierDirectory() {
        return suppliers;
    }

    public ProductsReport getSupplierPerformanceReport(String n) {
        Supplier supplier = suppliers.findSupplier(n);
        if (supplier == null) {
            return null;
        }
        return supplier.prepareProductsReport();

    }

    public ArrayList<ProductSummary> getSupplierProductsAlwaysAboveTarget(String n) {

        ProductsReport productsreport = getSupplierPerformanceReport(n);
        return productsreport.getProductsAlwaysAboveTarget();

    }

    public int getHowManySupplierProductsAlwaysAboveTarget(String n) {
        ProductsReport productsreport = getSupplierPerformanceReport(n); // see above
        int i = productsreport.getProductsAlwaysAboveTarget().size(); //return size of the arraylist
        return i;
    }

    public CustomerDirectory getCustomerDirectory() {
        return customerdirectory;
    }

    public SalesPersonDirectory getSalesPersonDirectory() {
        return salespersondirectory;
    }

    public MasterOrderList getMasterOrderList() {
        return masterorderlist;
    }

    public MarketCatalog getMarketCatalog() {
        return marketcatalog;
    }

    public ChannelCatalog getChannelCatalog() {
        return channelcatalog;
    }
    public SolutionOfferCatalog getSolutionOfferCatalog(){
        return solutionoffercatalog;
    }
    public MarketChannelComboCatalog getMarketChannelComboCatalog() {

        return marketChannelComboCatalog;
    }
    public MasterSolutionOrderList getMasterSolutionOrderList(){
        return mastersolutionorderlist;
    }
    //       public EmployeeDirectory getEmployeeDirectory() {
    //      return employeedirectory;
    //  }
    
    //1) 
    public Map sortedNegotiatedSolutionsList() {
        Map<Market, ArrayList<SolutionOrder>> marketBySOList = new HashMap<>();
        for (Market m: marketcatalog.getMarkets()){
            ArrayList<SolutionOrder> solutionOrderList = mastersolutionorderlist.getSolutionOrderByMarket(m);
            Collections.sort(solutionOrderList); 
            
            List<SolutionOrder> sublist = solutionOrderList.subList(0,3);
            ArrayList<SolutionOrder> newArrayList = new ArrayList<>(sublist);
            marketBySOList.put(m, newArrayList);
            System.out.println(m.getName());
            int i = 1;
            for (SolutionOrder s: sublist){
                System.out.println(s.getSelectedsolutionoffer().getSolutionName());
            }
            System.out.println("------------------------");
        }
        return marketBySOList;
    }
    
    // 2) 
    public void sortedBestCustomers(){
            int counter = 0;
            Collections.sort(mastersolutionorderlist.getSolutionorderlist()); //order list sorted now I think
            System.out.println("Top 3 Best Customers ");
            for(SolutionOrder so : mastersolutionorderlist.getSolutionorderlist()){
                if (so.getMargin() > 0 && counter < 3){
                    System.out.println("Customer Name: " + so.getCustomer().getCustomerId() + " : margin revenue collected: " + so.getMargin()); // show top 3 only ?
                    counter++;
                }
            }
            System.out.println("------------------------");
    }
    
//    3)
    public void sortedBestSalesPerson(){
        int counter = 0;
          System.out.println("Top 3 Best Salesmen: ");
        for(SolutionOrder so : mastersolutionorderlist.getSolutionorderlist()){
                if (so.getMargin() > 0 && counter < 3){
                    System.out.println("SalesPerson : " + so.getSalesPerson().getPerson().getPersonId() + " Margin revenue collected: " + so.getMargin()); // show top 3 only ?
                    counter++;
                }
            }
        
        System.out.println("------------------------");
    }
    
    
//    4) 
    public void marginalRevenueByMarket(){
        System.out.println("Margin revenue by markets: ");
        for (Market m: marketcatalog.getMarkets()){
            ArrayList<SolutionOrder> solutionOrderList = mastersolutionorderlist.getSolutionOrderByMarket(m);
            float totalMargin = 0;
            
            for (SolutionOrder so: solutionOrderList){
//                System.out.println(so.getMargin());
                totalMargin += so.getMargin();
            }
            System.out.println("Market Name: " + m.getName() +" " + totalMargin);
        }
        
        System.out.println("------------------------");
    }
    
//    5)
    public void optimizePrices() {
        // Map to store negotiated prices for each SolutionOffer
        System.out.println("Suggested Pricing for solutions");
        
        Map<SolutionOffer, List<Float>> negotiatedPricesMap = new HashMap<>();

        // Step 1: Populate negotiatedPricesMap
        for (SolutionOrder solutionOrder : mastersolutionorderlist.getSolutionorderlist()) {
            SolutionOffer solutionOffer = solutionOrder.getSelectedsolutionoffer();

            if (solutionOffer != null) {
                negotiatedPricesMap.computeIfAbsent(solutionOffer, k -> new ArrayList<>())
                        .add(solutionOrder.getSolutionPrice());
            }
        }
        


        // Step 2: Calculate new target and update prices
        for (Map.Entry<SolutionOffer, List<Float>> entry : negotiatedPricesMap.entrySet()) {
            SolutionOffer solutionOffer = entry.getKey();
            List<Float> negotiatedPrices = entry.getValue();

            // Calculate new target based on customer buying patterns
            double newTarget = calculateAverage(negotiatedPrices);

            // Update SolutionOffer prices
            solutionOffer.setCeiling((float) (newTarget * 1.1));  // Increase by 10%
            solutionOffer.setTarget((float) newTarget);
            solutionOffer.setFloor((float) (newTarget * 0.9));  // Decrease by 10%

            // Log the price adjustments
            System.out.println("SolutionOffer " + solutionOffer.getSolutionName() 
                    + ": Suggested Rates -> Ceil Price: " + (float)(newTarget * 1.1)
                    + ", Target Price: " + (float)newTarget
                    + ", Floor Price: " + (float)(newTarget * 0.9));
        }
    }

    // Calculate the average of prices that are closer to each other
    private static float calculateAverage(List<Float> prices) {
        // Sort the prices
        prices.sort(Float::compareTo);
//        System.out.println("prices---"+prices);

        // Calculate the average
        double sum = 0.0;
        int count = 0;

        for (int i = 0; i < prices.size(); i++) {
//            for (int j = i + 1; j < prices.size(); j++) {
//                if (prices.get(j) - prices.get(i) <= 1.0) {
                    sum += prices.get(i);
                    count++;
//                }
//            }

        }
        return (float)(count>0?(sum/count):0.0);
    
    }
}
