package lk.ijse.possystembackendspring.Service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.possystembackendspring.CustomStatusCode.SelectedControllerStatus;
import lk.ijse.possystembackendspring.Dto.ControllerStatus;
import lk.ijse.possystembackendspring.Dto.Impl.CustomerDto;
import lk.ijse.possystembackendspring.Dto.Impl.ItemDto;
import lk.ijse.possystembackendspring.Entity.Customer;
import lk.ijse.possystembackendspring.Entity.Item;
import lk.ijse.possystembackendspring.Exception.DataPersistException;
import lk.ijse.possystembackendspring.Exception.UserNotFoundException;
import lk.ijse.possystembackendspring.Service.ItemService;
import lk.ijse.possystembackendspring.Utill.mapping;
import lk.ijse.possystembackendspring.dao.CustomerDao;
import lk.ijse.possystembackendspring.dao.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private mapping mapping;

    @Override
    public void saveItem(ItemDto itemDto) {
        System.out.println(itemDto);

        Item saveItem =
                itemDao.save(mapping.toItemEntity(itemDto));
        if (saveItem == null) {
            throw new DataPersistException("User not saved");
        }
    }

    @Override
    public ControllerStatus getSelectedItem(String id) {
        if (itemDao.existsById(id)) {
            var selectedItem = itemDao.getReferenceById(id);
            return mapping.toItemDto(selectedItem);


        } else {
            return new SelectedControllerStatus(1, "Error", "Item Not Found");

        }
    }

    @Override
    public List<ItemDto> getAllItem() {
        List<Item> alluser=itemDao.findAll();
        return mapping.asItemDtoList(alluser);

    }

    @Override
    public void deleteItem(String id) {
        Optional<Item> existUser=itemDao.findById(id);
        if(!existUser.isPresent()){
            throw  new UserNotFoundException("Customer does not exist");
        }else {
            itemDao.deleteById(id);
        }
    }

    @Override
    public void updateItem(String id, ItemDto itemDto) {
        Optional<Item> tempCust= itemDao.findById(id);
        if(tempCust.isPresent()){
            tempCust.get().setName(itemDto.getName());
            tempCust.get().setQty(itemDto.getQty());
            tempCust.get().setPrice(itemDto.getPrice());

        }
    }
}
