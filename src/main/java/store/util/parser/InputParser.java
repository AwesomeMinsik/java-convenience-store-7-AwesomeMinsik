package store.util.parser;

import store.util.validate.StringValidator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InputParser {
    public static List<Map<String, Integer>> parseOrder(String order) {
        return Arrays.stream(order.split(","))
                .map(item -> parseProduct(item.trim()))
                .collect(Collectors.toList());
    }

    private static Map<String, Integer> parseProduct(String productStr) {
        String cleaned = productStr.replace("[", "").replace("]", "");
        String[] parts = cleaned.split("-");

        Map<String, Integer> productMap = new HashMap<>();
        productMap.put(parts[0].trim(),StringValidator.validateNumber(parts[1].trim()));
        return productMap;
    }
}
