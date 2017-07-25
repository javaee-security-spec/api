/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2015, 2017 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package javax.security.enterprise.identitystore;

import java.util.Map;

public interface PasswordHash {

    /**
     * Initialize the instance with the parameters it should use to
     * generate and verify password hashes. The parameters are the
     * name/value pairs specified with the
     * {@link DatabaseIdentityStoreDefinition#hashAlgorithmParameters()}
     * attribute.
     * <p>
     * An implementation is not required to support parameters, and may
     * ignore parameters passed to it. It is also possible that an implementation
     * will use the specified parameters when generating a new password hash,
     * but ignore them in favor of parameters stored with an existing password
     * hash when verifying.
     * <p>
     * If not parameters were provided, the argument is an empty {@link Map}.
     *
     * @param parameters A {@link Map} of the provided parameters, empty if no parameters were supplied.
     */
    default void initialize(Map<String, String> parameters) {
    }

    /**
     * Generate an encoded password hash value for storage in a user's account.
     * <p>
     * This method should not be used to generate a password hash for verification purposes;
     * use {@link #verify(char[], String)} for that purpose. Use this method only to generate
     * hash values when processing a new or changed password.
     * <p>
     * The returned hash value should be fully encoded such that it can be directly stored, as is,
     * with no additional format or encoding changes.
     * 
     * @param password The password to generate a hash for.
     * @return The generated password hash value.
     */
    String generate(char[] password);

    /**
     * Verify a user's password against the corresponding password hash value.
     * <p>
     * The password hash value should be provided exactly as retrieved from the identity store,
     * with no decoding or formatting applied. The provided password value will be hashed and
     * compared to the decoded hashed password value.
     * 
     * @param password The password to verify.
     * @param hashedPassword The hashed password value to compare against.
     * @return True if the password matched the hashed password, false otherwise.
     */
    boolean verify(char[] password, String hashedPassword);

}
