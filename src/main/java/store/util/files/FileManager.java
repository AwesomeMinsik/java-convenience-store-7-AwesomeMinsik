package store.util.files;


import store.model.Product;
import store.util.parser.ProductParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public abstract class FileManager {

    private static final String PATH = "src/main/resources/products.md";

    public static List<Product> getProductList() {
        List<String> products = new ArrayList<>();
        List<Product> productList;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(PATH), StandardCharsets.UTF_8))) {
            productList = ProductParser.getProductList(getTextList(reader, products));
        } catch (IOException e) {
            throw new RuntimeException("[ERROR] 파일 읽기 중 오류가 발생했습니다.", e);
        }
        return productList;
    }

    public static List<String> getTextList(BufferedReader reader, List<String> products) throws IOException {
        String line;
        reader.readLine();
        while ((line = reader.readLine()) != null)
            products.add(line);
        reader.close();
        return products;
    }

    public static void updateFileText(List<Product> products) {
        List<String> strings = ProductParser.parseProductToString(products);
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(PATH, true), StandardCharsets.UTF_8))) {
            for (String string : strings) {
                writer.write(string);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("[ERROR] 파일 쓰기 중 오류가 발생했습니다.", e);
        }
    }
}
