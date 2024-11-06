package store.policy;

public enum PromotionPolicy {
    NONE("일반 상품"),
    MD_RECOMMENDED("추천 상품"),
    BUY2GET1("2+1 프로모션"),
    LIMITED_DISCOUNT("반짝 할인");

    private final String description;

    // 생성자
    PromotionPolicy(String description) {
        this.description = description;
    }

    // 설명 반환 메서드
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }

    public static PromotionPolicy fromString(String promotion) {
        if (promotion == null || promotion.equalsIgnoreCase("null")) {
            return NONE;
        }
        return switch (promotion) {
            case "MD추천상품" -> MD_RECOMMENDED;
            case "탄산2+1" -> BUY2GET1;
            case "반짝할인" -> LIMITED_DISCOUNT;
            default -> NONE;
        };
    }
    public static String toString(PromotionPolicy promotionPolicy) {
        if (promotionPolicy == null || promotionPolicy == NONE) {
            return "null";
        }

        return switch (promotionPolicy) {
            case MD_RECOMMENDED -> "MD추천상품";
            case BUY2GET1 -> "탄산2+1";
            case LIMITED_DISCOUNT -> "반짝할인";
            default -> "null";
        };
    }
}
