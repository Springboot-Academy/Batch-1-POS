package com.springbootacademy.pointofsale.controller;

import com.springbootacademy.pointofsale.dto.ItemDTO;
import com.springbootacademy.pointofsale.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.pointofsale.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.pointofsale.service.ItemService;
import com.springbootacademy.pointofsale.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping(path = "/save")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO) {
        String id = itemService.addItem(itemSaveRequestDTO);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, id + "item successfully saved", id),
                HttpStatus.CREATED
        );
    }

    @GetMapping(
            path = {"/get-all-item"}
    )
    public ResponseEntity<StandardResponse> getAllItems() {
        List<ItemDTO> allItems = itemService.getAllItems();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "success", allItems),
                HttpStatus.OK
        );
    }

    @GetMapping(
            path = {"/get-all-item-by-state"},
            params = {"state"}
    )
    public ResponseEntity<StandardResponse> getAllItemsByState(@RequestParam(value = "state") String state) {
        if (state.equalsIgnoreCase("active") | state.equalsIgnoreCase("inactive")) {
//          boolean status=false;
//          if(state.equalsIgnoreCase("active")){
//              status=true;
//          }
            boolean status = state.equalsIgnoreCase("active") ? true : false;
            List<ItemDTO> allItems = itemService.getAllItemsByStateType(status);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, "success", allItems),
                    HttpStatus.OK
            );

        } else {
            List<ItemDTO> allItems = itemService.getAllItems();
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, "success", allItems),
                    HttpStatus.OK
            );

        }
    }
    @GetMapping(
            path = "count-all-item"
    )
    public ResponseEntity<StandardResponse>getAllItemCounts(){
           int itemCount = itemService.countAllItems();
           return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "success", itemCount),
                HttpStatus.OK
        );

    }

    @GetMapping(
            path = "get-all-items-paginated",
            params = {"page","size"}
    )
    public ResponseEntity<StandardResponse>getAllItemsPaginated(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size
    ){
        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getAllItemsPaginated(page,size);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "success", paginatedResponseItemDTO),
                HttpStatus.OK
        );

    }

    @GetMapping(
            path = "get-all-active-items-paginated",
            params = {"page","size","activeState"}
    )
    public ResponseEntity<StandardResponse>getAllActiveItemsPaginated(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size,
            @RequestParam(value = "activeState") boolean activeState
    ){
        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getAllActiveItemsPaginated(page,size,activeState);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "success", paginatedResponseItemDTO),
                HttpStatus.OK
        );

    }

}
