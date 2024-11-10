package store.util.validate;

import store.model.Product;

public class ProductValidater {
    public static void validateOrderQuantity(Product product, int orderQuantity) {
        if (product.getQuantity()<orderQuantity)
            throw new IllegalArgumentException();
    }
}
