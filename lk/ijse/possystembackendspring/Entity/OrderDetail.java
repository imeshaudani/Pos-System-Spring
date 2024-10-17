package lk.ijse.possystembackendspring.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Many OrderDetails belong to one Order
    @ManyToOne
    @JoinColumn(name = "orderId",nullable = false)
    private Orders orders;

    // Many OrderDetails belong to one Item
    @ManyToOne
    @JoinColumn(name = "itemId",nullable = false)
    private Item item;

    private int qty;
    private double price;
}
