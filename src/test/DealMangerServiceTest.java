package test;

import models.Deal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.DealManagerService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DealMangerServiceTest {
    DealManagerService dealManagerService;

    @BeforeEach
    void setUp() {
        dealManagerService = new DealManagerService();
    }

    @Test
    void testCreateDeal() {
        Deal deal = dealManagerService.createDeal("test1", 100.0, 2, LocalDateTime.now(),
                LocalDateTime.now().plusHours(2));

        assertNotNull(deal);
        assertEquals("test1", deal.getItemName());
        assertEquals(100.0, deal.getPrice());
        assertEquals(2, deal.getMaxQuantity());
    }

    @Test
    void testEndDeal() throws Exception {
        Deal deal = dealManagerService.createDeal("test1", 100.0, 2, LocalDateTime.now(),
                LocalDateTime.now().plusHours(2));

        dealManagerService.endDeal(deal.getId());
        System.out.println("local time" + LocalDateTime.now());
        Thread.sleep(1000);
        assertTrue(LocalDateTime.now().isAfter(deal.getEndTime()));
    }

    @Test
    void updateDeal() throws Exception {
        Deal deal = dealManagerService.createDeal("test1", 100.0, 2, LocalDateTime.now(),
                LocalDateTime.now().plusHours(2));
        dealManagerService.updateDeal(deal.getId(), 3, LocalDateTime.now().plusHours(3));
        assertEquals(3, deal.getMaxQuantity());
        assertTrue(LocalDateTime.now().plusHours(2).isBefore(deal.getEndTime()));
    }

    //Happy case
    @Test
    void testClaimDeal() throws Exception {
        Deal deal = dealManagerService.createDeal("test1", 100.0, 2, LocalDateTime.now(),
                LocalDateTime.now().plusHours(2));
        Deal claimedDeal1 = dealManagerService.claimDeal(deal.getId(), "user1");
        assertEquals(1, claimedDeal1.getClaimedQuantity());
        assertTrue(claimedDeal1.getClaimedUserIds().contains("user1"));

    }

    //Not happy cases
    @Test
    void testClaimDealAfterExpiry() throws Exception {
        Deal deal = dealManagerService.createDeal("test1", 100.0, 2, LocalDateTime.now(),
                LocalDateTime.now().minusHours(1));

        Exception exception = assertThrows(Exception.class, () -> {
            dealManagerService.claimDeal(deal.getId(), "user1");
        });
        assertEquals("Deal has ended", exception.getMessage());

    }

    @Test
    void testClaimedDealTwice() throws Exception {
        Deal deal = dealManagerService.createDeal("test1", 100.0, 2, LocalDateTime.now(),
                LocalDateTime.now().plusHours(2));
        dealManagerService.claimDeal(deal.getId(), "user1");
        Exception exception = assertThrows(Exception.class, ()->{
            dealManagerService.claimDeal(deal.getId(), "user1");
        });
        assertEquals("user has already claimed", exception.getMessage());
    }

    @Test
    void claimDealExceedMaxQuantity() throws Exception {
        Deal deal = dealManagerService.createDeal("test1", 100.0, 1, LocalDateTime.now(),
                LocalDateTime.now().plusHours(2));
        dealManagerService.claimDeal(deal.getId(), "user1");
        Exception exception = assertThrows(Exception.class, ()->{
            dealManagerService.claimDeal(deal.getId(), "user2");
        });
        assertEquals("stock not available", exception.getMessage());
    }

}
