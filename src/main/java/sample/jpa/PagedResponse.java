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
package sample.jpa;

import java.util.Collections;
import java.util.List;

public class PagedResponse<T> {

    private final List<T> results;

    private final PagedRequest pageRequest;

    private final long total;

    public PagedResponse(List<T> results, PagedRequest pageRequest, long total) {
        super();
        this.results = results;
        this.pageRequest = pageRequest;
        this.total = total;
    }

    public List<T> getContents() {
        return Collections.unmodifiableList(results);
    }

    public int getSize() {
        return pageRequest.getPageSize();
    }

    public int getNumber() {
        return pageRequest.getPageNumber();
    }

    public long getTotalElements() {
        return total;
    }
}
