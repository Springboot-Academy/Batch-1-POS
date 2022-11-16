package com.springbootacademy.pointofsale.service;

import com.springbootacademy.pointofsale.dto.ItemDTO;
import com.springbootacademy.pointofsale.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.pointofsale.dto.request.ItemSaveRequestDTO;

import java.util.List;

public interface ItemService {
    String addItem(ItemSaveRequestDTO itemSaveRequestDTO);

    List<ItemDTO> getAllItems();

    List<ItemDTO> getAllItemsByStateType(boolean status);

    int countAllItems();

    PaginatedResponseItemDTO getAllItemsPaginated(int page, int size);

    PaginatedResponseItemDTO getAllActiveItemsPaginated(int page, int size, boolean activeState);
}
