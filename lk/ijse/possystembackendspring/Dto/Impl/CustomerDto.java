package lk.ijse.possystembackendspring.Dto.Impl;

import lk.ijse.possystembackendspring.Dto.ControllerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto implements ControllerStatus {
   private String id;
   private String name;
   private String address;
   private String contact;
   private String email;
}
