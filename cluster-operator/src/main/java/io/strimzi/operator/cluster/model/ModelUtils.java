/*
 * Copyright 2018, Strimzi authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.strimzi.operator.cluster.model;

import io.strimzi.api.kafka.model.Kafka;
import io.strimzi.api.kafka.model.TlsCertificates;

public class ModelUtils {
    public static int getCertificateValidity(Kafka kafka) {
        int validity = AbstractModel.CERTS_EXPIRATION_DAYS;
        if (kafka.getSpec() != null) {
            validity = getCertificateValidity(kafka.getSpec().getTlsCertificates());
        }
        return validity;
    }

    public static int getCertificateValidity(TlsCertificates tlsCertificates) {
        int validity = AbstractModel.CERTS_EXPIRATION_DAYS;
        if (tlsCertificates != null
                && tlsCertificates.getValidityDays() > 0) {
            validity = tlsCertificates.getValidityDays();
        }
        return validity;
    }

    public static int getRenewalDays(TlsCertificates tlsCertificates) {
        return tlsCertificates != null ? tlsCertificates.getRenewalDays() : 30;
    }
}
