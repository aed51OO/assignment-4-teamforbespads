/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBusiness.SalesManagement;

import java.util.ArrayList;
import TheBusiness.OrderManagement.Order;
import TheBusiness.Personnel.Person;
import TheBusiness.Personnel.Profile;
import TheBusiness.SolutionOrders.SolutionOrder;

/**
 *
 * @author kal bugrara
 */
public class SalesPersonProfile extends Profile {
    ArrayList<SolutionOrder> solutionOrderList;


    public SalesPersonProfile(Person p) {

        super(p); 
        solutionOrderList = new ArrayList();

    }
    public void addSalesOrder(SolutionOrder so){
        solutionOrderList.add(so);
    }
    @Override
    public String getRole(){
        return  "Sales";
    }

}
