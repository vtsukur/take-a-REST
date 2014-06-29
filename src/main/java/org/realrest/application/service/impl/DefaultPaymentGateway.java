package org.realrest.application.service.impl;

import org.realrest.application.service.PaymentGateway;
import org.realrest.domain.Payment;

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
