package lk.ijse.possystembackendspring.Service;

import lk.ijse.possystembackendspring.Dto.ControllerStatus;
import lk.ijse.possystembackendspring.Dto.Impl.CustomerDto;
import lk.ijse.possystembackendspring.Dto.Impl.ItemDto;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDto itemDto);
    ControllerStatus getSelectedItem(String id);
    List<ItemDto> getAllItem();
    void deleteItem(String id);
    void updateItem(String id, ItemDto itemDto);
}
