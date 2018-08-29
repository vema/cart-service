package com.labs.game.cartservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CartRepositoryTest {
    @Autowired
    private CartRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findByCustomer_ReturnsCartItems() {
        CartItem savedCartItem = entityManager.persistFlushFind(new CartItem(10, "555","AAA", 10));
        CartItem[] cartItems = repository.findByCustomer("555");
        assertThat(cartItems.length).isEqualTo(1);
        final CartItem cartItem = cartItems[0];
        assertThat(cartItem.getCustomer()).isEqualTo(savedCartItem.getCustomer());
        assertThat(cartItem.getProduct()).isEqualTo(savedCartItem.getProduct());
        assertThat(cartItem.getQuantity()).isEqualTo(savedCartItem.getQuantity() );


    }
}