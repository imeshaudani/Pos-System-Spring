package lk.ijse.possystembackendspring.dao;

import lk.ijse.possystembackendspring.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<Customer,String> {
}
