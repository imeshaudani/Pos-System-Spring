package lk.ijse.possystembackendspring.Service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.possystembackendspring.CustomStatusCode.SelectedControllerStatus;
import lk.ijse.possystembackendspring.Dto.ControllerStatus;
import lk.ijse.possystembackendspring.Dto.Impl.CustomerDto;
import lk.ijse.possystembackendspring.Entity.Customer;
import lk.ijse.possystembackendspring.Exception.DataPersistException;
import lk.ijse.possystembackendspring.Exception.UserNotFoundException;
import lk.ijse.possystembackendspring.Service.CustomerService;
import lk.ijse.possystembackendspring.Utill.mapping;
import lk.ijse.possystembackendspring.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private mapping mapping;

    @Override
    public void saveCustomer(CustomerDto customerDto) {
        System.out.println(customerDto);

        Customer savedCustomer =
                customerDao.save(mapping.toCustomerEntity(customerDto));
        if (savedCustomer == null) {
            throw new DataPersistException("User not saved");
        }
    }

    @Override
    public ControllerStatus getSelectedCustomer(String id) {
        if (customerDao.existsById(id)) {
            var selectedCustomer = customerDao.getReferenceById(id);
            return mapping.toCustomerDto(selectedCustomer);


        } else {
            return new SelectedControllerStatus(1, "Error", "Customer Not Found");

        }
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        List<Customer> alluser=customerDao.findAll();
        return mapping.asCustomerDtoList(alluser);

    }

    @Override
    public void deleteCustomer(String id) {
        Optional<Customer> existUser=customerDao.findById(id);
        if(!existUser.isPresent()){
            throw  new UserNotFoundException("Customer does not exist");
        }else {
            customerDao.deleteById(id);
        }
    }

    @Override
    public void updateCustomer(String id, CustomerDto customerDto) {
        Optional<Customer> tempCust= customerDao.findById(id);
        if(tempCust.isPresent()){
            tempCust.get().setAddress(customerDto.getAddress());
            tempCust.get().setName(customerDto.getName());
            tempCust.get().setEmail(customerDto.getEmail());
            tempCust.get().setContact(customerDto.getContact());


        }
    }
}