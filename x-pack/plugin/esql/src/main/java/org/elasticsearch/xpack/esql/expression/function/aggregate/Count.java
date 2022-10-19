/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0; you may not use this file except in compliance with the Elastic License
 * 2.0.
 */

package org.elasticsearch.xpack.esql.expression.function.aggregate;

import org.elasticsearch.compute.Experimental;
import org.elasticsearch.xpack.ql.expression.Expression;
import org.elasticsearch.xpack.ql.expression.function.aggregate.AggregateFunction;
import org.elasticsearch.xpack.ql.expression.function.aggregate.EnclosedAgg;
import org.elasticsearch.xpack.ql.tree.NodeInfo;
import org.elasticsearch.xpack.ql.tree.Source;
import org.elasticsearch.xpack.ql.type.DataType;
import org.elasticsearch.xpack.ql.type.DataTypes;

import java.util.List;

@Experimental
public class Count extends AggregateFunction implements EnclosedAgg {

    public Count(Source source, Expression field) {
        super(source, field);
    }

    @Override
    protected NodeInfo<Count> info() {
        return NodeInfo.create(this, Count::new, field());
    }

    @Override
    public Count replaceChildren(List<Expression> newChildren) {
        return new Count(source(), newChildren.get(0));
    }

    @Override
    public String innerName() {
        return "count";
    }

    @Override
    public DataType dataType() {
        return DataTypes.LONG;
    }
}
