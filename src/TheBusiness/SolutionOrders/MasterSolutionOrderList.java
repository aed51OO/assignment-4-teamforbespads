/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness.SolutionOrders;

import TheBusiness.CustomerManagement.CustomerProfile;
import TheBusiness.MarketModel.Channel;
import TheBusiness.MarketModel.Market;
import TheBusiness.MarketModel.MarketChannelAssignment;
import TheBusiness.MarketModel.SolutionOffer;
import TheBusiness.SalesManagement.SalesPersonProfile;
import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class MasterSolutionOrderList {
        ArrayList<SolutionOrder> solutionorderlist;

    public ArrayList<SolutionOrder> getSolutionorderlist() {
        return solutionorderlist;
    }

    public MasterSolutionOrderList() {
        solutionorderlist = new ArrayList();
    }

    public SolutionOrder newSolutionOrder(SolutionOffer soloffer,  MarketChannelAssignment mca) {

        SolutionOrder so = new SolutionOrder(soloffer,   mca);
        solutionorderlist.add(so);
        soloffer.addSolutionOrder(so);
        return so;

    }
    
    public SolutionOrder newSolutionOrder(SolutionOffer solOffer, float price, CustomerProfile customer, SalesPersonProfile salesPerson){
        SolutionOrder so = new SolutionOrder(solOffer, price, customer, salesPerson);
        solutionorderlist.add(so);
        return so;
    }

    public float getRevenueByMarket(Market m) {
        float sum = 0;
        for(SolutionOrder so: solutionorderlist){
         
         MarketChannelAssignment mcc =   so.getMarketChannelCombo();
         if(mcc.getMarket()==m) sum = sum +so.getSolutionPrice();
           
        }

        return sum;

    }
    public float getRevenueByChannel(Channel c) {
        float sum = 0;
        for(SolutionOrder so: solutionorderlist){
         
         MarketChannelAssignment mcc =   so.getMarketChannelCombo();
         if(mcc.getChannel()==c) sum = sum +so.getSolutionPrice();
           
        }

        return sum;

    }
    public float getRevenueByMarketChannelCombo(MarketChannelAssignment mca) {
        float sum = 0;
        for(SolutionOrder so: solutionorderlist){
         
         MarketChannelAssignment mcc =   so.getMarketChannelCombo();
         if(mcc==mca) sum = sum +so.getSolutionPrice(); 
           
        }
        return sum;

    }
    
    public ArrayList<SolutionOrder> getSolutionOrderByMarket(Market m){
        ArrayList<SolutionOrder> solutionOrderListForMarket = new ArrayList<>();
        
            for (SolutionOrder solutionOrder: solutionorderlist){
                if (solutionOrder.getMarketChannelCombo().getMarket().equals(m)){
                    solutionOrderListForMarket.add(solutionOrder);
                }
            }
         return solutionOrderListForMarket;   
    }

}
