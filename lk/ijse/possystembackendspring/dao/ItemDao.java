package lk.ijse.possystembackendspring.dao;

import lk.ijse.possystembackendspring.Entity.Customer;
import lk.ijse.possystembackendspring.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDao  extends JpaRepository<Item,String> {
}
