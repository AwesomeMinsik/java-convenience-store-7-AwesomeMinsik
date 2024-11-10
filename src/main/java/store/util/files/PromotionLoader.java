package store.util.files;

import store.policy.PromotionPolicy;
import store.util.parser.ProductParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PromotionLoader {
    private static final String PATH = "src/main/resources/promotions.md";

    public static List<PromotionPolicy> getPromotionList() {
        List<PromotionPolicy> promotionPolicies = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(PATH), StandardCharsets.UTF_8))) {
            return getTextList(reader, promotionPolicies);
        } catch (IOException e) {
            throw new RuntimeException("[ERROR] 파일 읽기 중 오류가 발생했습니다.", e);
        }
    }

    public static List<PromotionPolicy> getTextList(BufferedReader reader, List<PromotionPolicy> promotionPolicies) throws IOException {
        String line;
        reader.readLine();
        while ((line = reader.readLine()) != null)
            promotionPolicies.add(ProductParser.parseStringToPromotionType(line));
        reader.close();
        return promotionPolicies;
    }
}
