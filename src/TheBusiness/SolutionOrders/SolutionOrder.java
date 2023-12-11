/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness.SolutionOrders;

import TheBusiness.CustomerManagement.CustomerProfile;
import TheBusiness.MarketModel.MarketChannelAssignment;
import TheBusiness.MarketModel.SolutionOffer;
import TheBusiness.SalesManagement.SalesPersonProfile;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author kal bugrara
 */

public class SolutionOrder implements Comparable<SolutionOrder>{
    float negotiatedPrice;    
    SolutionOffer selectedsolutionoffer;
    SalesPersonProfile salesPerson;
    CustomerProfile customer;
    
    MarketChannelAssignment marketChannelAssignment; 
    public SolutionOrder(SolutionOffer so,  MarketChannelAssignment mca){
        selectedsolutionoffer = so;
        marketChannelAssignment = mca;

    }
    
    
    public SalesPersonProfile getSalesPerson() {
        return salesPerson;
    }

    public CustomerProfile getCustomer() {
        return customer;
    }

    
    public SolutionOrder(SolutionOffer so, float negotiatedPrice, CustomerProfile customer, SalesPersonProfile salesPerson){
        this.negotiatedPrice = negotiatedPrice;
        this.customer = customer;
        customer.setSolutionOrder(this);
        this.salesPerson = salesPerson;
        salesPerson.addSalesOrder(this);
        this.selectedsolutionoffer = so;
        so.addSolutionOrder(this);
        
        marketChannelAssignment = so.getMarketchannelcomb();
    }
    
    public float getSolutionPrice(){
        return this.negotiatedPrice;
    }
    
    public MarketChannelAssignment getMarketChannelCombo(){
        
        return this.selectedsolutionoffer.getMarketchannelcomb();
                
    }
    
    public float getMargin(){
        float margin = this.getSolutionPrice() - selectedsolutionoffer.getTargetPrice();
        return margin;
    }

    @Override
    public int compareTo(SolutionOrder solutionOrder) {
        return (int)(solutionOrder.getMargin()-this.getMargin());
    }

    public SolutionOffer getSelectedsolutionoffer() {
        return selectedsolutionoffer;
    }



   
}
