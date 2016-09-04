package com.tomkasp.fitnow.shop.domain;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "integration_id")
    private String integrationId;

    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "time_stamp")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime timeStamp;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "shop_order_id", nullable = false)
    @Fetch(FetchMode.JOIN)
    private Order order;


    public static Payment createNew(String paymentIntegrationId, BigDecimal amount, Order order) {
        Payment payment = new Payment();
        payment.amount = amount;
        payment.integrationId = paymentIntegrationId;
        payment.paymentStatus = PaymentStatus.NEW;
        payment.timeStamp = new DateTime();
        payment.order = order;
        return payment;
    }
}
