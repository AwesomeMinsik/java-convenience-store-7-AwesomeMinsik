package store.factory;

import store.model.Product;

import java.util.List;

import static store.parser.ProductParser.fromString;

public abstract class ProductFactory {
    public static Product createProductObject(List<String> parts){
        return new Product(parts.get(0),
                Integer.parseInt(parts.get(1)),
                Integer.parseInt(parts.get(2)),
                fromString(parts.get(3)));
    }
}
