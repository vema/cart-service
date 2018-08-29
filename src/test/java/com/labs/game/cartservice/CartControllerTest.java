package com.labs.game.cartservice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @Before
    public void setUp() {

        given(cartService.findByCustomer(eq("111"))).willReturn(
                new CartItem[]{
                        new CartItem(0, "111", "AAA", 10),
                        new CartItem(0, "111", "BBB", 100)
                });

        given(cartService.findByCustomer(eq("222"))).willReturn(
                new CartItem[]{
                        new CartItem(0, "222", "AAA", 10)
                });

        given(cartService.findByCustomer(eq("333"))).willReturn(new CartItem[0]);
    }

    @Test
    public void getItems_returnsOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cart-items?customer=111"))
                .andExpect(status().isOk());

    }

    @Test
    public void getItems_returnsCustomer() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/cart-items?customer=111"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customer").value("111"));

        mockMvc.perform(MockMvcRequestBuilders.get("/cart-items?customer=222"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customer").value("222"));

    }

    @Test
    public void getItems_returnsEmptyList_forUnknownCustomer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cart-items?customer=333"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }


}