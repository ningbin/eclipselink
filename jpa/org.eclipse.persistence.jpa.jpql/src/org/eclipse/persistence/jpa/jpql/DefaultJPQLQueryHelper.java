/*******************************************************************************
 * Copyright (c) 2006, 2011 Oracle. All rights reserved.
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
package org.eclipse.persistence.jpa.jpql;

import org.eclipse.persistence.jpa.jpql.parser.JPQLGrammar;


/**
 * This helper can perform the following operations over a JPQL query:
 * <ul>
 * <li>Calculates the result type of a query: {@link #getResultType()};</li>
 * <li>Calculates the type of an input parameter: {@link #getParameterType(String)}.</li>
 * <li>Calculates the possible choices to complete the query from a given
 *     position (used for content assist): {@link #buildContentAssistItems(int)}.</li>
 * <li>Validates the query by introspecting it grammatically and semantically:
 *     <ul>
 *     <li>{@link #validate()},</li>
 *     <li>{@link #validateGrammar()},</li>
 *     <li>{@link #validateSemantic()}.</li>
 *     </ul></li>
 * </ul>
 *
 * This helper should be used when the JPQL query is written using the JPQL grammar defined in the
 * Java Persistence functional specification 1.0 or 2.0.<p>
 *
 * @version 2.4
 * @since 2.3
 * @author Pascal Filion
 */
public class DefaultJPQLQueryHelper extends AbstractJPQLQueryHelper {

	/**
	 * Creates a new <code>DefaultJPQLQueryHelper</code>.
	 *
	 * @param jpqlGrammar
	 */
	public DefaultJPQLQueryHelper(JPQLGrammar jpqlGrammar) {
		super(jpqlGrammar);
	}

	/**
	 * Creates a new <code>JPQLQueryHelper</code>.
	 *
	 * @param queryContext The context used to query information about the JPQL query
	 */
	public DefaultJPQLQueryHelper(JPQLQueryContext queryContext) {
		super(queryContext);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AbstractContentAssistVisitor buildContentAssistVisitor(JPQLQueryContext queryContext) {
		return new DefaultContentAssistVisitor(queryContext);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DefaultGrammarValidator buildGrammarValidator(JPQLQueryContext queryContext) {
		return new DefaultGrammarValidator(queryContext);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected JPQLQueryContext buildJPQLQueryContext(JPQLGrammar jpqlGrammar) {
		return new DefaultJPQLQueryContext(jpqlGrammar);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DefaultSemanticValidator buildSemanticValidator(JPQLQueryContext queryContext) {
		return new DefaultSemanticValidator(queryContext);
	}
}