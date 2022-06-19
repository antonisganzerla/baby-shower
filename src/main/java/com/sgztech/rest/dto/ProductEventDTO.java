package com.sgztech.rest.dto;

import javax.validation.constraints.NotNull;

public class ProductEventDTO {

    @NotNull(message = "{field.productId.required}")
    private Integer productId;

    @NotNull(message = "{field.quantity.required}")
    private Integer quantity;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
