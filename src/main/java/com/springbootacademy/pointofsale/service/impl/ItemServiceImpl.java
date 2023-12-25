package com.springbootacademy.pointofsale.service.impl;

import com.springbootacademy.pointofsale.dto.CustomerDTO;
import com.springbootacademy.pointofsale.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.pointofsale.dto.response.ItemGetResponseDTO;
import com.springbootacademy.pointofsale.entity.Customer;
import com.springbootacademy.pointofsale.entity.Item;
import com.springbootacademy.pointofsale.repo.ItemRepo;
import com.springbootacademy.pointofsale.service.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String saveItem(ItemSaveRequestDTO itemSaveRequestDTO) {

//        Item item = new Item(
//                1,
//                itemSaveRequestDTO.getItemName(),
//                itemSaveRequestDTO.getMeasuringUnitType(),
//                itemSaveRequestDTO.getBalanceQty(),
//                itemSaveRequestDTO.getSupplierPrice(),
//                itemSaveRequestDTO.getSellingPrice(),
//                true
//        );
//        itemRepo.save(item);
//        return item.getItemName();

        Item item = modelMapper.map(itemSaveRequestDTO,Item.class);
        if(!itemRepo.existsById(item.getItemId())){
            itemRepo.save(item);
            return item.getItemId() + "Saved Successfully";

        }else{
            throw new DuplicateKeyException("Already Added:");
        }
    }

    //Get item without measuring unit variable, get by Item Name And Status (dto > response > itemGetResponseDTO)
    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName) {

        //service and repo has entity type data transferring
        boolean b = true; //the method contains 2 parameters,(getItemByNameAndStatus) so in here manually giving status = true

        List<Item> items = itemRepo.findAllByItemNameEqualsAndActiveEquals(itemName,b);
        if (items.size() > 0){
            //convert Item Entity type to return type of the method "List<ItemGetResponseDTO>"
            List<ItemGetResponseDTO> itemGetResponseDTOS =  modelMapper.map(items,new TypeToken<List<ItemGetResponseDTO>>(){}.getType());
            return itemGetResponseDTOS;

        }else{
            throw new RuntimeException("Item is Not Active !!!");
        }

    }
}
