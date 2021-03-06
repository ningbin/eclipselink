/*
 * Copyright (c) 2011, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */

// Contributors:
//     Matt MacIvor - 2.5 - initial implementation
package org.eclipse.persistence.testing.jaxb.objectgraph;

public class PhoneNumberInh extends ContactInfo {

    String number;

    public boolean equals(Object obj) {
        PhoneNumberInh a = (PhoneNumberInh)obj;
        boolean equals = contactType == a.contactType || contactType.equals(a.contactType);
        equals = equals && number == a.number || number.equals(a.number);
        return equals;
    }
}
