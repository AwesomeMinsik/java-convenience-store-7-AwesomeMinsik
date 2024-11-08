package store.factory;

import store.model.OrderedProduct;
import store.model.Product;

public abstract class OrderItemFactory {
    public static OrderedProduct createProductObject(Product product,int quantity){
        return new OrderedProduct(product.getName(),product.getPrice(),quantity, product.getTotalPrice());
    }
}
