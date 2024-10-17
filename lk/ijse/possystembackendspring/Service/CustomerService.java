package lk.ijse.possystembackendspring.Service;

import lk.ijse.possystembackendspring.Dto.ControllerStatus;
import lk.ijse.possystembackendspring.Dto.Impl.CustomerDto;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDto customerDto);
    ControllerStatus getSelectedCustomer(String id);
    List<CustomerDto> getAllCustomer();
    void deleteCustomer(String id);
    void updateCustomer(String id, CustomerDto customerDto);
}
