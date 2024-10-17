package lk.ijse.possystembackendspring.Dto.Impl;

import lk.ijse.possystembackendspring.Dto.ControllerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDto implements ControllerStatus {
   private String id;
   private String name;
   private double price;
   private int qty;
}
