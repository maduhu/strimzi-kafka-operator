/*
 * Copyright 2018, Strimzi authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.strimzi.api.kafka.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.strimzi.crdgenerator.annotations.Description;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.sundr.builder.annotations.Buildable;

import java.util.List;

/**
 * Configures the broker authorization
 */
@Buildable(
        editableEnabled = false,
        generateBuilderPackage = false,
        builderPackage = "io.fabric8.kubernetes.api.builder"
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KafkaUserAuthorizationSimple extends KafkaUserAuthorization {
    private static final long serialVersionUID = 1L;

    public static final String TYPE_SIMPLE = "simple";

    private List<AclRule> acls;

    @Description("Must be `" + TYPE_SIMPLE + "`")
    @Override
    public String getType() {
        return TYPE_SIMPLE;
    }

    @Description("List of ACL rules which should be applied to this user.")
    @JsonProperty(required = true)
    public List<AclRule> getAcls() {
        return acls;
    }

    public void setAcls(List<AclRule> acls) {
        this.acls = acls;
    }
}
