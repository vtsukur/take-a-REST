package org.takearest.domain.repository.impl;

import org.takearest.domain.Payment;
import org.takearest.domain.repository.PaymentRepository;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author volodymyr.tsukur
 */
@ApplicationScoped
public class InMemoryPaymentRepository extends BaseInMemoryRepository<Payment> implements PaymentRepository {

    public InMemoryPaymentRepository() {
        super("Payment");
    }

}
