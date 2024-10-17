package lk.ijse.possystembackendspring.Service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.possystembackendspring.Dto.ControllerStatus;
import lk.ijse.possystembackendspring.Dto.Impl.ItemDto;
import lk.ijse.possystembackendspring.Dto.Impl.OrderDto;
import lk.ijse.possystembackendspring.Entity.Customer;
import lk.ijse.possystembackendspring.Entity.Item;
import lk.ijse.possystembackendspring.Entity.OrderDetail;
import lk.ijse.possystembackendspring.Entity.Orders;
import lk.ijse.possystembackendspring.Exception.DataPersistException;
import lk.ijse.possystembackendspring.Service.OrderService;
import lk.ijse.possystembackendspring.Utill.AppUtil;
import lk.ijse.possystembackendspring.dao.CustomerDao;
import lk.ijse.possystembackendspring.dao.ItemDao;
import lk.ijse.possystembackendspring.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private ItemDao itemDao;

    @Override
    public void saveOrder(OrderDto orderDto) {
        // Create a new order and set basic details
        Orders order = new Orders();
        order.setOrderId(AppUtil.genereteOrderId());
        order.setOrderDate(orderDto.getOrderDate());
        order.setTotal(orderDto.getTotal());
        order.setAmount(orderDto.getAmount());
        order.setDiscount(orderDto.getDiscount());
        order.setOrderQty(orderDto.getOrderQty());

        // Fetch customer or throw an exception if not found
        Customer customer = getCustomerById(orderDto.getCustId());
        order.setCustomer(customer);

        // Add order details (items) to the order
        List<OrderDetail> orderDetailsList = createOrderDetailsList(order, orderDto.getItems());
        order.setOrderDetails(orderDetailsList);

        // Save the order and check if it was successful
        Orders savedOrder = orderDao.save(order);
        if (savedOrder == null) {
            throw new DataPersistException("Error saving the order");
        }
    }

    // Helper method to retrieve customer by ID
    private Customer getCustomerById(String custId) {
        return customerDao.findById(custId)
                .orElseThrow(() -> new DataPersistException("Customer not found with ID: " + custId));
    }

    // Helper method to create a list of OrderDetails based on the items in the order
    private List<OrderDetail> createOrderDetailsList(Orders order, List<ItemDto> itemDtos) {
        List<OrderDetail> orderDetailsList = new ArrayList<>();

        if (itemDtos == null || itemDtos.isEmpty()) {
            throw new DataPersistException("Order must contain at least one item");
        }

        for (ItemDto itemDto : itemDtos) {
            Item item = getItemById(itemDto.getId());

            // Create a new OrderDetail object for each item
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrders(order);
            orderDetail.setItem(item);
            orderDetail.setQty(itemDto.getQty());
            orderDetail.setPrice(itemDto.getPrice());

            orderDetailsList.add(orderDetail);
        }
        return orderDetailsList;
    }

    // Helper method to retrieve an item by its ID
    private Item getItemById(String itemId) {
        return itemDao.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + itemId));
    }

    @Override
    public ControllerStatus getSelectedOrder(String id) {
        // To be implemented
        return null;
    }

    @Override
    public List<ItemDto> getAllOrder() {
        // To be implemented
        return null;
    }

    @Override
    public void deleteOrder(String id) {
        // To be implemented
    }

    @Override
    public void updateOrder(String id, OrderDto orderDto) {
        // To be implemented
    }
}
