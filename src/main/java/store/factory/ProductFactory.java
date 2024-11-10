package store.factory;

import store.model.Product;
import store.policy.PromotionPolicy;
import store.util.parser.ProductParser;

import java.util.List;


public abstract class ProductFactory {
    public static Product createProductObject(List<String> parts, List<PromotionPolicy> promotionList){
        return new Product(parts.get(0),
                Integer.parseInt(parts.get(1)),
                Integer.parseInt(parts.get(2)),
                ProductParser.getPromotionType(parts.get(3),promotionList));
    }
}
