package com.lcwd.electronic.store.dtos;

import com.lcwd.electronic.store.entities.CartItem;
import com.lcwd.electronic.store.entities.User;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {
    private String cartId;
    private Date createdAt;
    private UserDto user;
    //mapping cart-items
    private List<CartItemDto> items=new ArrayList<>();
}
