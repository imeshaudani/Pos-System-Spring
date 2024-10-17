package lk.ijse.possystembackendspring.Controller;

import lk.ijse.possystembackendspring.CustomStatusCode.SelectedControllerStatus;
import lk.ijse.possystembackendspring.Dto.ControllerStatus;
import lk.ijse.possystembackendspring.Dto.Impl.CustomerDto;
import lk.ijse.possystembackendspring.Dto.Impl.ItemDto;
import lk.ijse.possystembackendspring.Exception.DataPersistException;
import lk.ijse.possystembackendspring.Exception.UserNotFoundException;
import lk.ijse.possystembackendspring.Service.CustomerService;
import lk.ijse.possystembackendspring.Service.ItemService;
import lk.ijse.possystembackendspring.Utill.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;
@RestController
@RequestMapping("api/v1/item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @PostMapping("/saveItem")
    public ResponseEntity<Void> saveItem(@RequestBody ItemDto itemDto) {
        try {
      itemDto.setId(AppUtil.genereteItemId());
            System.out.println("C-"+itemDto);
            itemService.saveItem(itemDto);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }/*catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }*/
    }
    @GetMapping(value = "/{itemId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ControllerStatus getSelectedItem(@PathVariable("itemId") String itemId){
        String regexForUserID = "^ITEM-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForUserID);
        var regexMatcher = regexPattern.matcher(itemId);
        if (!regexMatcher.matches()) {
            return new SelectedControllerStatus(1,"Item ID is not valid","Error");
        }
        return itemService.getSelectedItem(itemId);

    }
    @GetMapping(value = "/getAllItem",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDto> getAllCustomer() {
        return itemService.getAllItem();
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{itemId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("itemId") String itemId) {
        String regexForUserID = "^ITEM-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForUserID);
        var regexMatcher = regexPattern.matcher(itemId);
        try {
            if(!regexMatcher.matches()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            itemService.deleteItem(itemId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (UserNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){

            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @PutMapping(value = "/{itemId}")
    public ResponseEntity<Void> updateCustomer(@PathVariable ("itemId") String itemId,
                                               @RequestBody ItemDto updatedItemDto){
        //validations
        String regexForUserID = "^ITEM-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForUserID);
        var regexMatcher = regexPattern.matcher(itemId);
        try {
            if(!regexMatcher.matches() || updatedItemDto == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            itemService.updateItem(itemId,updatedItemDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (UserNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
