package com.oms.order.repository;

import com.oms.order.model.domain.Invoice;
import com.oms.order.model.domain.Payment;
import com.oms.order.model.domain.Product;
import com.oms.order.model.entity.OrderEntity;
import com.oms.order.model.request.OrderUpdate;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

    public static final String ORDER_ID = "_id";
    public static final String ORDER_STATUS = "orderStatus";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void updateOrderStatus(String orderId, String status) {
        BulkOperations bulk = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, OrderEntity.class);
        bulk.updateMulti(updateOrderStatusQuery(orderId, status)).execute();
    }

    @Override
    public void updateOrder(OrderUpdate orderUpdate, String orderId) {
        BulkOperations bulk = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, OrderEntity.class);
        List<Pair<Query, Update>> operations = new ArrayList<>();
        buildProductUpdateQuery(operations,orderUpdate.getProducts(), orderId);
        buildInvoiceUpdateQuery(operations,orderUpdate.getInvoiceList(), orderId);
        buildPaymentUpdateQuery(operations,orderUpdate.getPaymentList(), orderId);
        updateTotalCostAndOrderStatus(operations,orderUpdate.getTotalCost(),orderId, orderUpdate.getOrderStatus());
        bulk.updateMulti(operations).execute();
    }

    private List<Pair<Query, Update>> updateTotalCostAndOrderStatus(List<Pair<Query, Update>> operations, String totalCost,
                                                                        String orderId, String orderStatus) {
        Criteria costCriteria = new Criteria().andOperator(
                where(ORDER_ID).is(new ObjectId(orderId)));
        Query updateQuery = new Query();
        updateQuery.addCriteria(costCriteria);
        Update update = new Update();
        update.set("totalCost", totalCost);
        update.set("orderStatus", orderStatus);
        operations.add(Pair.of(updateQuery, update));
        return operations;
    }

    private List<Pair<Query, Update>> buildPaymentUpdateQuery(List<Pair<Query, Update>> operations, List<Payment> paymentList, String orderId) {
        paymentList.stream().forEach(payment -> {
        Criteria paymentCriteria = new Criteria().andOperator(
                where(ORDER_ID).is(new ObjectId(orderId)));
        Query updateQuery = new Query();
        updateQuery.addCriteria(paymentCriteria);
        Update update = new Update();
        update.set("paymentList.0.paymentMode", payment.getPaymentMode());
        update.set("paymentList.0.paymentStatus", payment.getPaymentStatus());
        operations.add(Pair.of(updateQuery, update));
        });
        return operations;
    }

    private List<Pair<Query, Update>> buildProductUpdateQuery(List<Pair<Query, Update>> operations, List<Product> products, String orderId) {

        products.stream().forEach(product -> {
            Criteria prodIdCriteria = new Criteria().andOperator(
                    where(ORDER_ID).is(new ObjectId(orderId)));
            Query updateQuery = new Query();
            updateQuery.addCriteria(prodIdCriteria);
            Update update = new Update();
            update.set("products.quantity", product.getQuantity());
            update.set("products.title", product.getTitle());
            update.set("products.unitCost", product.getUnitCost());
            operations.add(Pair.of(updateQuery, update));
        });
        return operations;
    }

    private List<Pair<Query, Update>> buildInvoiceUpdateQuery(List<Pair<Query, Update>> operations, List<Invoice> invoices, String orderId) {
        invoices.stream().forEach(invoice -> {
            Criteria invoiceConsumer = new Criteria().andOperator(
                    where(ORDER_ID).is(new ObjectId(orderId)));
            Query updateQuery = new Query();
            updateQuery.addCriteria(invoiceConsumer);
            Update update = new Update();
            update.set("invoiceList.0.invoiceNumber", invoice.getInvoiceNumber());
            update.set("invoiceList.0.invoiceDate", invoice.getInvoiceDate());
            operations.add(Pair.of(updateQuery, update));
        });
        return operations;
    }

    private List<Pair<Query, Update>> updateOrderStatusQuery(String orderId, String status) {
        List<Pair<Query, Update>> operations = new ArrayList<>();
        Criteria prodIdCriteria = new Criteria().andOperator(
                where(ORDER_ID).is(new ObjectId(orderId)));
        Query updateQuery = new Query();
        updateQuery.addCriteria(prodIdCriteria);
        Update update = new Update();
        update.set(ORDER_STATUS, status);
        operations.add(Pair.of(updateQuery, update));
        return operations;
    }
}
