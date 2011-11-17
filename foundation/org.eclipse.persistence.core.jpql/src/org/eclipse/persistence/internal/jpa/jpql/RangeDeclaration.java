/*******************************************************************************
 * Copyright (c) 2011 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Oracle - initial API and implementation
 *
 ******************************************************************************/
package org.eclipse.persistence.internal.jpa.jpql;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.expressions.Expression;
import org.eclipse.persistence.expressions.ExpressionBuilder;
import org.eclipse.persistence.jpa.jpql.parser.JoinFetch;
import org.eclipse.persistence.mappings.DatabaseMapping;

/**
 * This <code>RangeDeclaration</code> represents an identification variable declaration that was
 * declared in the <code><b>FROM</b></code> clause of a <code><b>SELECT</b></code> top-level query
 * or subquery.
 *
 * @see IdentificationVariableDeclaration
 *
 * @version 2.4
 * @since 2.4
 * @author Pascal Filion
 */
final class RangeDeclaration extends AbstractRangeDeclaration {

	/**
	 * The list of <b>JOIN FETCH</b> expressions that are declared in the same declaration than
	 * the range variable declaration.
	 */
	private List<JoinFetch> joinFetches;

	/**
	 * Creates a new <code>RangeDeclaration</code>.
	 *
	 * @param queryContext The context used to query information about the application metadata and
	 * cached information
	 */
	RangeDeclaration(JPQLQueryContext queryContext) {
		super(queryContext);
	}

	/**
	 * Adds the given {@link JoinFetch} to this range declaration.
	 *
	 * @param joinFetch The {@link JoinFetch} that was found in the list of joins/join fetches
	 */
	void addJoinFetch(JoinFetch joinFetch) {
		if (joinFetches == null) {
			joinFetches = new ArrayList<JoinFetch>();
		}
		joinFetches.add(joinFetch);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	Expression buildExpression() {
		return new ExpressionBuilder(getDescriptor().getJavaClass());
	}

	/**
	 * Returns the <b>JOIN FETCH</b> expressions that were part of the range variable declaration
	 * in the ordered they were parsed.
	 *
	 * @return The ordered list of <b>JOIN FETCH</b> expressions or an empty collection if none
	 * was present
	 */
	List<JoinFetch> getJoinFetches() {
		return (joinFetches == null) ? Collections.<JoinFetch>emptyList() : joinFetches;
	}

	/**
	 * Determines whether the declaration contains <b>JOIN FETCH</b> expressions. This can be
	 * <code>true</code> only when {@link #isRange()} returns <code>true</code>. A collection
	 * member declaration does not have <b>JOIN FETCH</b> expressions.
	 *
	 * @return <code>true</code> if at least one <b>JOIN FETCH</b> expression was parsed;
	 * otherwise <code>false</code>
	 */
	boolean hasJoinFetches() {
		return (joinFetches != null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	boolean isDerived() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	boolean isRange() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	ClassDescriptor resolveDescriptor() {
		return queryContext.getDescriptor(rootPath);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	DatabaseMapping resolveMapping() {
		// A range declaration does not have a mapping, only a descriptor
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	Class<?> resolveType() {
		return getDescriptor().getJavaClass();
	}
}