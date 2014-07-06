package org.letustakearest.application.service;

import org.letustakearest.domain.Payment;

/**
 * @author volodymyr.tsukur
 */
public interface PaymentGateway {

    void process(Payment payment);

}
