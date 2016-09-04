package com.tomkasp.fitnow.shop.domain;


import com.tomkasp.domain.User;
import com.tomkasp.fitnow.shop.application.paymentproviders.OrderType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "shop_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "order_type")
    private OrderType orderType;

    @Column(name = "time_stamp")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime timeStamp;

    @OneToOne(optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    @Fetch(FetchMode.JOIN)
    private User user;

    @OneToOne(cascade = CascadeType.ALL, optional = false, mappedBy = "order")
    @Fetch(FetchMode.JOIN)
    private Payment payment;

    public static Order createNew(User user, OrderType orderType, String paymentIntegrationId, BigDecimal amount) {
        Order order = new Order();
        order.orderType = orderType;
        order.timeStamp = new DateTime();
        order.user = user;
        order.payment = Payment.createNew(paymentIntegrationId, amount, order);
        return order;
    }

}
