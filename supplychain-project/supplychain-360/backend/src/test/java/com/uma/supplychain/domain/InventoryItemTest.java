package com.uma.supplychain.domain;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class InventoryItemTest {
 @Test void reservationReducesAvailableQuantity(){var item=new InventoryItem("SKU-1","Test",10,0,2,"WH1");item.reserve(4);assertEquals(6,item.getAvailable());}
 @Test void reservationCannotExceedAvailable(){var item=new InventoryItem("SKU-1","Test",3,0,1,"WH1");assertThrows(IllegalArgumentException.class,()->item.reserve(4));}
 @Test void adjustmentCannotGoBelowReserved(){var item=new InventoryItem("SKU-1","Test",10,5,1,"WH1");assertThrows(IllegalArgumentException.class,()->item.adjustOnHand(-6));}
}
