/*
 * Copyright 2018, Strimzi authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.strimzi.operator.cluster.model;

import io.strimzi.api.kafka.model.Kafka;

public class ModelUtils {
    public static int getCertificateValidity(Kafka kafka) {
        int validity = AbstractModel.CERTS_EXPIRATION_DAYS;
        if (kafka.getSpec() != null
                && kafka.getSpec().getTlsCertificates() != null
                && kafka.getSpec().getTlsCertificates().getValidityDays() > 0) {
            validity = kafka.getSpec().getTlsCertificates().getValidityDays();
        }
        return validity;
    }
}
