package com.oms.order.repository;



import java.util.List;

public interface OrderRepositoryCustom {
//    void updateProductsInInventory(List<InventoryUpdate> inventoryUpdate);

    void deleteProductsFromInventory(List<String> productIds);

//    List<InventoryProduct> findAllInventoryProducts(List<String> productIdList);
}
