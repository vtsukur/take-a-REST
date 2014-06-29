package org.realrest.domain.repository.impl;

import org.realrest.domain.Payment;
import org.realrest.domain.repository.PaymentRepository;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author volodymyr.tsukur
 */
@ApplicationScoped
public class InMemoryPaymentRepository extends BaseInMemoryBookingRepository<Payment> implements PaymentRepository {

    public InMemoryPaymentRepository() {
        super("Payment");
    }

}
