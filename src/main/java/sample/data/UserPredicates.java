/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sample.data;

import sample.domain.QUser;

import com.mysema.query.types.expr.BooleanExpression;

public class UserPredicates {

    // tag::firstNameLike[]
    public static BooleanExpression firstNameLike(String prefix) {
        QUser user = QUser.user;
        return user.firstName.startsWithIgnoreCase(prefix);
    }
    // end::firstNameLike[]

    // tag::lastNameLike[]
    public static BooleanExpression lastNameLike(String prefix) {
        QUser user = QUser.user;
        return user.lastName.startsWithIgnoreCase(prefix);
    }
    // end::lastNameLike[]
}
