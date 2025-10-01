package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BasketServiceTest {

    private StorageService storageService;
    private ProductBasket productBasket;
    private BasketService basketService;

    @BeforeEach
    void setUp() {
        storageService = mock(StorageService.class);
        productBasket = mock(ProductBasket.class);
        basketService = new BasketService(productBasket, storageService);
    }

    @Test
    void addNonExistingProductShouldThrowException() {
        UUID id = UUID.randomUUID();
        when(storageService.getProductById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> basketService.addProductToBasket(id));
    }

    @Test
    void addExistingProductCallsBasketAdd() {
        UUID id = UUID.randomUUID();
        Product product = mock(Product.class);
        when(storageService.getProductById(id)).thenReturn(Optional.of(product));

        basketService.addProductToBasket(id);

        verify(productBasket).addProduct(id);
    }

    @Test
    void getUserBasketReturnsEmptyWhenBasketEmpty() {
        when(productBasket.getProducts()).thenReturn(Map.of());

        UserBasket basket = basketService.getUserBasket();

        assertNotNull(basket);
        assertTrue(basket.getItems().isEmpty());
        assertEquals(0, basket.getTotal());
    }

    @Test
    void getUserBasketReturnsCorrectBasket() {
        UUID id = UUID.randomUUID();
        Product product = mock(Product.class);
        when(product.getPrice()).thenReturn(100);
        when(storageService.getProductById(id)).thenReturn(Optional.of(product));
        when(productBasket.getProducts()).thenReturn(Map.of(id, 2));

        UserBasket basket = basketService.getUserBasket();

        List<BasketItem> items = basket.getItems();
        assertEquals(1, items.size());
        assertEquals(product, items.get(0).getProduct());
        assertEquals(2, items.get(0).getQuantity());
        assertEquals(200, basket.getTotal());
    }
}