package lk.ijse.possystembackendspring.Service;

import lk.ijse.possystembackendspring.Dto.ControllerStatus;
import lk.ijse.possystembackendspring.Dto.Impl.ItemDto;
import lk.ijse.possystembackendspring.Dto.Impl.OrderDto;

import java.util.List;

public interface OrderService {
    void saveOrder(OrderDto orderDto);
    ControllerStatus getSelectedOrder(String id);
    List<ItemDto> getAllOrder();
    void deleteOrder(String id);
    void updateOrder(String id, OrderDto orderDto);
}
