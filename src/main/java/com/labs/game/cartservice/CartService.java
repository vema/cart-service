package com.labs.game.cartservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartService {

    @Autowired
    private CartRepository repository;

    public CartItem[] findByCustomer(String customer) {
        final CartItem[] cartItems = repository.findByCustomer(customer);
        if (cartItems == null)
            return new CartItem[0];
        else
            return cartItems;
    }
}
