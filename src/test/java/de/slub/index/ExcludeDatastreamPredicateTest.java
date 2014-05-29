/*
 * Copyright 2014 SLUB Dresden
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.slub.index;


import de.slub.util.Predicate;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ExcludeDatastreamPredicateTest {

    private final Predicate<IndexJob> predicate = new ExcludeDatastreamPredicate(
            new ArrayList<String>() {{
                add("DC");
                add("RELS-EXT");
            }}
    );

    @Test
    public void evaluatesToFalseForGivenDatastreamIds() {
        assertFalse(predicate.evaluate(new DatastreamIndexJob(IndexJob.Type.CREATE, "test:1234", "DC")));
        assertFalse(predicate.evaluate(new DatastreamIndexJob(IndexJob.Type.CREATE, "test:1234", "RELS-EXT")));
    }

    @Test
    public void evaluatesToTrueForNotGivenDatastreamIds() {
        assertTrue(predicate.evaluate(new DatastreamIndexJob(IndexJob.Type.CREATE, "test:1234", "MODS")));
        assertTrue(predicate.evaluate(new DatastreamIndexJob(IndexJob.Type.CREATE, "test:1234", "RELS-INT")));
    }

}