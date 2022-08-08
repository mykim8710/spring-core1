package com.example.springcore1.order;

public class Order {
    private Long memberId;
    private String itemName;    // 주문한 상품명
    private int itemPrice;      // 주문한 원상품가격
    private int discountPrice;
    private int finalItemPrice;

    // Constructor
    public Order(Long memberId, String itemName, int itemPrice, int discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
        this.finalItemPrice = calculatorItemPrice();
    }

    // 할인상품가격 계산 method
    public int calculatorItemPrice() {
        return itemPrice - discountPrice;
    }

    // Getter
    public Long getMemberId() {
        return memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    // Setter
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountPrice +
                ", finalItemPrice=" + finalItemPrice +
                '}';
    }
}
