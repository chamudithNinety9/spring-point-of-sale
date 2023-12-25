package com.springbootacademy.pointofsale.service;

import com.springbootacademy.pointofsale.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.pointofsale.dto.response.ItemGetResponseDTO;

import java.util.List;

public interface ItemService {
    public String saveItem(ItemSaveRequestDTO itemSaveRequestDTO);

    List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName);
}
