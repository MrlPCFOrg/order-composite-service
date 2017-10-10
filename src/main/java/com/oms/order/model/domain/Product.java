package com.oms.order.model.domain;

import com.google.common.base.MoreObjects;

public class Product {
    String quantity;
    String title;
    String unitCost;

    public String getQuantity() {
        return quantity;
    }

    public Product setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Product setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUnitCost() {
        return unitCost;
    }

    public Product setUnitCost(String unitCost) {
        this.unitCost = unitCost;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("quantity", quantity)
                .add("title", title)
                .add("unitCost", unitCost)
                .toString();
    }
}
