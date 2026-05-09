package com.example.stock.domain.model.stock;

public class Stock {
    private final ProductId productId;
    private int quantity;
    private int reservedQuantity;

    public Stock(ProductId productId, int quantity, int reservedQuantity) {
        this.productId = productId;
        this.quantity = quantity;
        this.reservedQuantity = reservedQuantity;
    }

    public void reserve(int amount) {
        if (this.quantity - this.reservedQuantity < amount) {
            throw new IllegalStateException("在庫がありません");
        }

        this.reservedQuantity += amount;
    }

    public void release(int amount) {
        if (this.reservedQuantity < amount) {
            throw new AssertionError("予約済み数量が不整合です。データ整合性を確認してください: reservedQuantity=" + reservedQuantity + ", amount=" + amount);
        }
        this.reservedQuantity -= amount;
    }

    public ProductId getProductId() {
        return this.productId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getReservedQuantity() {
        return this.reservedQuantity;
    }

}
