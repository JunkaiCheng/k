// Copyright (c) 2015 K Team. All Rights Reserved.

package org.kframework.backend.java.kil;

import org.kframework.backend.java.symbolic.Transformer;
import org.kframework.backend.java.symbolic.Visitor;
import org.kframework.kil.ASTNode;

import java.util.Arrays;

/**
 * Holds the RHS of multiple rewrites (from different rules) which has not been bubbled up to the top.
 * Used in conjunction with the {@link org.kframework.backend.java.symbolic.FastRuleMatcher}.
 */
public class InnerRHSRewrite extends Term {
    public final Term[] theRHS;

    public InnerRHSRewrite(Term[] theRHS) {
        super(Kind.KITEM);
        this.theRHS = theRHS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnerRHSRewrite that = (InnerRHSRewrite) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(theRHS, that.theRHS);

    }

    @Override
    public int computeHash() {
        return Arrays.hashCode(theRHS);
    }

    @Override
    public boolean isExactSort() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isSymbolic() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Sort sort() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected boolean computeMutability() {
        return false;
    }

    @Override
    public ASTNode accept(Transformer transformer) {
        return transformer.transform(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
