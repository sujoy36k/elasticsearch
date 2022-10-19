/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0 and the Server Side Public License, v 1; you may not use this file except
 * in compliance with, at your election, the Elastic License 2.0 or the Server
 * Side Public License, v 1.
 */

package org.elasticsearch.compute.aggregation;

import org.elasticsearch.compute.Experimental;
import org.elasticsearch.compute.data.Block;
import org.elasticsearch.compute.data.Page;

import java.util.function.BiFunction;

@Experimental
public interface GroupingAggregatorFunction {

    void addRawInput(Block groupIdBlock, Page page);

    void addIntermediateInput(Block groupIdBlock, Block block);

    Block evaluateIntermediate();

    Block evaluateFinal();

    BiFunction<AggregatorMode, Integer, GroupingAggregatorFunction> avg = (AggregatorMode mode, Integer inputChannel) -> {
        if (mode.isInputPartial()) {
            return GroupingAvgAggregator.createIntermediate();
        } else {
            return GroupingAvgAggregator.create(inputChannel);
        }
    };

    BiFunction<AggregatorMode, Integer, GroupingAggregatorFunction> count = (AggregatorMode mode, Integer inputChannel) -> {
        if (mode.isInputPartial()) {
            return GroupingCountAggregator.createIntermediate();
        } else {
            return GroupingCountAggregator.create(inputChannel);
        }
    };

    BiFunction<AggregatorMode, Integer, GroupingAggregatorFunction> min = (AggregatorMode mode, Integer inputChannel) -> {
        if (mode.isInputPartial()) {
            return GroupingMinAggregator.createIntermediate();
        } else {
            return GroupingMinAggregator.create(inputChannel);
        }
    };

    BiFunction<AggregatorMode, Integer, GroupingAggregatorFunction> max = (AggregatorMode mode, Integer inputChannel) -> {
        if (mode.isInputPartial()) {
            return GroupingMaxAggregator.createIntermediate();
        } else {
            return GroupingMaxAggregator.create(inputChannel);
        }
    };

    BiFunction<AggregatorMode, Integer, GroupingAggregatorFunction> sum = (AggregatorMode mode, Integer inputChannel) -> {
        if (mode.isInputPartial()) {
            return GroupingSumAggregator.createIntermediate();
        } else {
            return GroupingSumAggregator.create(inputChannel);
        }
    };
}
