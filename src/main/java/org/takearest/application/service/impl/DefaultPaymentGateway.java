package org.takearest.application.service.impl;

import org.takearest.application.service.PaymentGateway;
import org.takearest.domain.Payment;

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
