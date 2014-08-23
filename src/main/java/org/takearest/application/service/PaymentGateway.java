package org.takearest.application.service;

import org.takearest.domain.Payment;

/**
 * @author volodymyr.tsukur
 */
public interface PaymentGateway {

    void process(Payment payment);

}
