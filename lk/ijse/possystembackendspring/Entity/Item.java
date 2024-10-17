package lk.ijse.possystembackendspring.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "item")
public class Item implements SuperEntity{
    @Id
    private String id;
    private String name;
    private double price;
    private int qty;
    // Many-to-Many relationship with Order through OrderDetail
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetails;
}
