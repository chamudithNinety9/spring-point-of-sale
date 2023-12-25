package com.springbootacademy.pointofsale.controller;

import com.springbootacademy.pointofsale.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.pointofsale.dto.response.ItemGetResponseDTO;
import com.springbootacademy.pointofsale.service.ItemService;
import com.springbootacademy.pointofsale.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin

public class ItemController {

    @Autowired
    private ItemService itemService;

//    @PostMapping(
//            path = "/save"
//    )
//    public String saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO){
//        itemService.saveItem(itemSaveRequestDTO);
//        return "Item Saved";
//    }

    //saveItem method with standard response type
    @PostMapping(
            path = "/save"
    )
    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO) {

        String message = itemService.saveItem(itemSaveRequestDTO);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "success", message),
                HttpStatus.CREATED
        );
    }

    /*Get item without measuring unit variable, get by Item Name And Status
        (dto > response > itemGetResponseDTO) */
    @GetMapping(
            path = "/get-by-name",
            params = "name"
    )

    public List<ItemGetResponseDTO> getItemByNameAndStatus(@RequestParam(value = "name") String itemName) {
        List<ItemGetResponseDTO> itemDTOS = itemService.getItemByNameAndStatus(itemName);
        return itemDTOS;
    }

}
