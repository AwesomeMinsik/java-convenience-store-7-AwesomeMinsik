package store.model;

import store.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class ProductManager {
    public static List<Product> findProductByOrderInput(List<Product> products, List<Map<String, Integer>> orderedProductList) {
        List<Product> matchedProducts = new ArrayList<>();
        for (Map<String, Integer> orderedProduct : orderedProductList) {
            matchedProducts.add(getProductByName(products, orderedProduct));
        }
        return matchedProducts;
    }

    private static Product getProductByName(List<Product> products, Map<String, Integer> orderedProduct) {
        for (Product product : products) {
            if (orderedProduct.containsKey(product.getName())) {
                setProductPriceByQuantity(orderedProduct, product);
                compareProductQuantity(product, orderedProduct.get(product.getName()));
                return product;
            }
        }
        return null;
    }

    private static void setProductPriceByQuantity(Map<String, Integer> orderedProduct, Product product) {
        int totalValue = product.getPrice() * orderedProduct.get(product.getName());
        product.setTotalPrice(totalValue);
    }

    private static void compareProductQuantity(Product product, int quantity) {
        if (product.getQuantity() < quantity) {
            OutputView.printProductStatus(product);
        }
        product.deductStockByQuantity(quantity);
    }


}