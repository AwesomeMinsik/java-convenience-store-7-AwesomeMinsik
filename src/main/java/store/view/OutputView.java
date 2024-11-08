package store.view;

import store.model.Product;

import java.util.List;

public abstract class OutputView {
    public static List<Product> printProductList(List<Product> productList){
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다");
        System.out.println();
        productList.forEach(System.out::println);
        System.out.println();
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예 : [사이다-2],[감자칩-1])");
        return productList;
    }
    public static void printBillLetter(){
        System.out.println("==============W 편의점================");
        System.out.println("상품명\t\t수량\t금액");
    }
    public static void printProductStatus(Product product) {
        System.out.println(product.getName() + "의 남은 수량"+product.getQuantity()+"개");
    }
}
