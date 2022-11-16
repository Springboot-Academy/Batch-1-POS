package com.springbootacademy.pointofsale.util.mappers;

import com.springbootacademy.pointofsale.dto.ItemDTO;
import com.springbootacademy.pointofsale.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.pointofsale.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item RequestDtoToEntity(ItemSaveRequestDTO itemSaveRequestDTO);
    List<ItemDTO> pageToList(Page<Item>page);

}
