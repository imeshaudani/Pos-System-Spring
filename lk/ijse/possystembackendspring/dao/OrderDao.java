package lk.ijse.possystembackendspring.dao;

import lk.ijse.possystembackendspring.Entity.Item;
import lk.ijse.possystembackendspring.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao  extends JpaRepository<Orders,String> {
}
