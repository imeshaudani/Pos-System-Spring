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
@Table(name = "orders")
public class Orders {
    @Id
    private String orderId;
    private String orderDate;
    @ManyToOne
    @JoinColumn(name = "custId", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetails;
    private int orderQty;
    private double total;
    private double amount;
    private double Discount;
}
