/*
 * Copyright 2017-2018, Strimzi authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.strimzi.operator.common.model;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LabelsTest {
    @Test
    public void testParseValidLabels()   {
        String validLabels = "key1=value1,key2=value2";

        Map sourceMap = new HashMap<String, String>(2);
        sourceMap.put("key1", "value1");
        sourceMap.put("key2", "value2");
        Labels expected = Labels.fromMap(sourceMap);

        Assert.assertEquals(expected, Labels.fromString(validLabels));
    }

    @Test
    public void testParseNullLabels()   {
        String validLabels = null;
        assertEquals(Labels.EMPTY, Labels.fromString(validLabels));
    }

    @Test
    public void testParseNullLabelsInFromMap()   {
        assertEquals(Labels.EMPTY, Labels.fromMap(null));
    }

    @Test
    public void testParseNullLabelsInUserLabels()   {
        assertEquals(Labels.EMPTY, Labels.userLabels(null));
    }

    @Test
    public void testParseEmptyLabels()   {
        String validLabels = "";
        assertEquals(Labels.EMPTY, Labels.fromString(validLabels));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseInvalidLabels1()   {
        String invalidLabels = ",key1=value1,key2=value2";

        Labels.fromString(invalidLabels);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseInvalidLabels2()   {
        String invalidLabels = "key1=value1,key2=";

        Labels.fromString(invalidLabels);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseInvalidLabels3()   {
        String invalidLabels = "key2";

        Labels.fromString(invalidLabels);
    }

    @Test
    public void testStrimziLabels()   {
        Map sourceMap = new HashMap<String, String>(5);
        sourceMap.put(Labels.STRIMZI_CLUSTER_LABEL, "my-cluster");
        sourceMap.put("key1", "value1");
        sourceMap.put(Labels.STRIMZI_KIND_LABEL, "Kafka");
        sourceMap.put("key2", "value2");
        sourceMap.put(Labels.STRIMZI_NAME_LABEL, "my-cluster-kafka");
        Labels labels = Labels.fromMap(sourceMap);

        Map expected = new HashMap<String, String>(2);
        expected.put(Labels.STRIMZI_CLUSTER_LABEL, "my-cluster");
        expected.put(Labels.STRIMZI_KIND_LABEL, "Kafka");
        expected.put(Labels.STRIMZI_NAME_LABEL, "my-cluster-kafka");

        assertEquals(expected, labels.strimziLabels().toMap());
    }
}
