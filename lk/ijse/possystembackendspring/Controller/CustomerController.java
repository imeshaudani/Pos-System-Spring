package lk.ijse.possystembackendspring.Controller;

import lk.ijse.possystembackendspring.CustomStatusCode.SelectedControllerStatus;
import lk.ijse.possystembackendspring.Dto.ControllerStatus;
import lk.ijse.possystembackendspring.Dto.Impl.CustomerDto;
import lk.ijse.possystembackendspring.Exception.DataPersistException;
import lk.ijse.possystembackendspring.Exception.UserNotFoundException;
import lk.ijse.possystembackendspring.Service.CustomerService;
import lk.ijse.possystembackendspring.Utill.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    private Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @PostMapping("/saveCustomer")
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDto customerDto) {
        try {
            logger.info("Request received to save customer: {}", customerDto);
            customerDto.setId(AppUtil.genereteCustomerId());
            logger.info("Generated customer ID: {}", customerDto.getId());

            customerService.saveCustomer(customerDto);
            logger.info("Customer saved successfully with ID: {}", customerDto.getId());

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            logger.error("DataPersistException: Unable to save customer", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Exception: Internal server error while saving customer", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{custId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ControllerStatus getSelectedCustomer(@PathVariable("custId") String custId) {
        logger.info("Request received to get customer with ID: {}", custId);
        String regexForUserID = "^CUST-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForUserID);
        var regexMatcher = regexPattern.matcher(custId);

        if (!regexMatcher.matches()) {
            logger.warn("Invalid customer ID format: {}", custId);
            return new SelectedControllerStatus(1, "Note ID is not valid", "Error");
        }

        logger.info("Valid customer ID format: {}", custId);
        return customerService.getSelectedCustomer(custId);
    }

    @GetMapping(value = "/getAllCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDto> getAllCustomer() {
        logger.info("Request received to get all customers");
        return customerService.getAllCustomer();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{custId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("custId") String custId) {
        logger.info("Request received to delete customer with ID: {}", custId);
        String regexForUserID = "^CUST-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForUserID);
        var regexMatcher = regexPattern.matcher(custId);
        try {
            if (!regexMatcher.matches()) {
                logger.warn("Invalid customer ID format: {}", custId);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            customerService.deleteCustomer(custId);
            logger.info("Customer deleted successfully with ID: {}", custId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            logger.error("UserNotFoundException: Customer not found with ID: {}", custId, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Exception: Internal server error while deleting customer with ID: {}", custId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{custId}")
    public ResponseEntity<Void> updateCustomer(@PathVariable("custId") String custId, @RequestBody CustomerDto updatedCustomerDTO) {
        logger.info("Request received to update customer with ID: {}", custId);
        String regexForUserID = "^CUST-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForUserID);
        var regexMatcher = regexPattern.matcher(custId);
        try {
            if (!regexMatcher.matches() || updatedCustomerDTO == null) {
                logger.warn("Invalid input: Customer ID or DTO is not valid");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            customerService.updateCustomer(custId, updatedCustomerDTO);
            logger.info("Customer updated successfully with ID: {}", custId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            logger.error("UserNotFoundException: Customer not found with ID: {}", custId, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Exception: Internal server error while updating customer with ID: {}", custId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
