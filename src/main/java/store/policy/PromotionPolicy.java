package store.policy;

public enum PromotionPolicy {
    NONE(""),
    MD_RECOMMENDED("MD추천상품"),
    BUY2GET1("탄산2+1"),
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
}
