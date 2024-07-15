import models.Deal;
import service.DealManagerService;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args)  {
        DealManagerService dealManagerService = new DealManagerService();

        //Create a deal
        Deal deal = dealManagerService.createDeal("TestItem", 100.0, 2, LocalDateTime.now(),
                LocalDateTime.now().plusHours(2));
        System.out.println("Deal has been created with dealId: " + deal.getId());

        try {
            //claim a deal
            Deal claimDeal1 = dealManagerService.claimDeal(deal.getId(), "user1");
            System.out.println("User1 has claimed the deal " + claimDeal1);
            Deal claimDeal3 = dealManagerService.claimDeal(deal.getId(), "user1");
            System.out.println("User1 has claimed the deal " + claimDeal3);

            Deal claimDeal2 = dealManagerService.claimDeal(deal.getId(), "user2");
            System.out.println("User2 has claimed the deal" + claimDeal2);

            Deal claimDeal4 = dealManagerService.claimDeal(deal.getId(), "user3");
            System.out.println("User3 has claimed the deal" + claimDeal4);

        } catch (Exception e) {
            System.err.println("Error in claiming the deal due to " + e.getMessage());
        }

        try {
            //update a deal
            Deal updateDeal = dealManagerService.updateDeal(deal.getId(), 3, LocalDateTime.now().plusHours(1));
            System.out.println("Deal : "+updateDeal.getId() + "has been updated ");
        } catch (Exception e) {
            System.err.println("unable to update the deal due to " + e.getMessage());
        }

        try {
            //end a deal
            Deal endDeal = dealManagerService.endDeal(deal.getId());
            System.out.println("Deal : "+ endDeal.getId() + "has been ended");
        } catch (Exception e) {
            System.err.println("Failed to end the deal beacause " + e.getMessage());
        }


    }
}