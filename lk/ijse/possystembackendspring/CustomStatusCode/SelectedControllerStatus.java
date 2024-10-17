package lk.ijse.possystembackendspring.CustomStatusCode;


import lk.ijse.possystembackendspring.Dto.ControllerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedControllerStatus implements ControllerStatus {
    private int StatusCode;
    private String Status;

    private String StatusMassage;
}
