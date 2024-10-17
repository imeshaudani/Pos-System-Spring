package lk.ijse.possystembackendspring.Dto.Impl;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto {
   private String orderId;
   private String orderDate;
   private String custId;
   private List<ItemDto> items;
   private int orderQty;
   private double total;
   private double amount;
   private double Discount;
}
