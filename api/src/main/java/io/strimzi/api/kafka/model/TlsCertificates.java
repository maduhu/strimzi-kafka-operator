/*
 * Copyright 2018, Strimzi authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.strimzi.api.kafka.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.strimzi.crdgenerator.annotations.Description;
import io.sundr.builder.annotations.Buildable;

@Description("Configuration of how TLS certificates are used within the cluster." +
        "This applies to certificates used for both internal communication within the cluster and to certificates " +
        "used for client access via `Kafka.spec.kafka.listeners.tls`.")
@Buildable(
        editableEnabled = false,
        generateBuilderPackage = true,
        builderPackage = "io.strimzi.api.kafka.model"
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TlsCertificates {
    int validityDays;
    Boolean generateCertificateAuthority;
    long renewalDays;

    @Description("The number of days generated certificates should be valid for. Default is 365.")
    public int getValidityDays() {
        return validityDays;
    }

    public void setValidityDays(int validityDays) {
        this.validityDays = validityDays;
    }

    @Description("If true then Certificate Authority certificates will be generated automatically. " +
            "Otherwise the user will need to provide a Secret with the CA certificate. " +
            "Default is true.")
    public Boolean getGenerateCertificateAuthority() {
        return generateCertificateAuthority;
    }

    public void setGenerateCertificateAuthority(Boolean generateCertificateAuthority) {
        this.generateCertificateAuthority = generateCertificateAuthority;
    }

    @Description("The number of days in the certificate renewal period. " +
            "This is the number of days before the a certificate expires during which renewal actions may be performed." +
            "When `generateCertificateAuthority` is true, this will cause the generation of a new certificate. " +
            "When `generateCertificateAuthority` is true, this will cause extra logging at WARN level about the pending certificate expiry. " +
            "Default is 30.")
    public long getRenewalDays() {
        return renewalDays;
    }

    public void setRenewalDays(long renewalDays) {
        this.renewalDays = renewalDays;
    }
}
