package org.letustakearest.application.service.impl;

import org.letustakearest.application.service.PaymentGateway;
import org.letustakearest.domain.Payment;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author volodymyr.tsukur
 */
@ApplicationScoped
public class DefaultPaymentGateway implements PaymentGateway {

    @Override
    public void process(Payment payment) {
        // Always accepting payment.
    }

}
