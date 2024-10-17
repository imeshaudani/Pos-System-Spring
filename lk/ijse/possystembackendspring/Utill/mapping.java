package lk.ijse.possystembackendspring.Utill;


import lk.ijse.possystembackendspring.Dto.Impl.CustomerDto;
import lk.ijse.possystembackendspring.Dto.Impl.ItemDto;
import lk.ijse.possystembackendspring.Entity.Customer;
import lk.ijse.possystembackendspring.Entity.Item;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class mapping {
    @Autowired
    private ModelMapper modelMapper;


    public Customer toCustomerEntity(CustomerDto customerDto){
        return modelMapper.map(customerDto, Customer.class);
    }
    public CustomerDto toCustomerDto(Customer customer){
        return modelMapper.map(customer, CustomerDto.class);
    }
    public List<CustomerDto> asCustomerDtoList(List<Customer> customerList){
        return modelMapper.map(customerList,new TypeToken<List<CustomerDto>>(){}.getType());
    }
    public Item toItemEntity(ItemDto itemDto){
        return modelMapper.map(itemDto, Item.class);
    }
    public ItemDto toItemDto(Item item){
        return modelMapper.map(item, ItemDto.class);
    }
    public List<ItemDto> asItemDtoList(List<Item> itemList){
        return modelMapper.map(itemList,new TypeToken<List<ItemDto>>(){}.getType());
    }
}
