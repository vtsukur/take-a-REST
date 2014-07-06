package org.letustakearest.domain.repository.impl;

import org.letustakearest.domain.Payment;
import org.letustakearest.domain.repository.PaymentRepository;

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
