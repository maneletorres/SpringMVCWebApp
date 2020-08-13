package com.maneletorres.springmvc.domain;

import com.maneletorres.springmvc.enums.OrderStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDER_HEADER")
public class Order extends AbstractDomainClass {
    @OneToOne
    private Customer customer;

    @Embedded
    private Address shipToAddress;

    private OrderStatus orderStatus;

    private Date dateShipped;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    private List<OrderDetail> orderLines = new ArrayList<>();

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getShipToAddress() {
        return shipToAddress;
    }

    public void setShipToAddress(Address shipToAddress) {
        this.shipToAddress = shipToAddress;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderDetail> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderDetail> orderLines) {
        this.orderLines = orderLines;
    }

    public List<OrderDetail> getOrders() {
        return orderLines;
    }

    public void setOrders(List<OrderDetail> orders) {
        this.orderLines = orders;
    }

    public Date getDateShipped() {
        return dateShipped;
    }

    public void setDateShipped(Date dateShipped) {
        this.dateShipped = dateShipped;
    }

    public void addToOrderDetails(OrderDetail orderDetail) {
        orderDetail.setOrder(this);
        orderLines.add(orderDetail);
    }

    public void removeOrderDetail(OrderDetail orderDetail) {
        orderDetail.setOrder(null);
        this.orderLines.remove(orderDetail);
    }
}
