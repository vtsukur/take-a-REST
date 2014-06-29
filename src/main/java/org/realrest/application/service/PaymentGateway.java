package org.realrest.application.service;

import org.realrest.domain.Payment;

/**
 * @author volodymyr.tsukur
 */
public interface PaymentGateway {

    void process(Payment payment);

}
