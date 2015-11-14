/*
 * This file is part of ensure.
 *
 * ensure is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ensure is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with ensure.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.plattfish.support.ensure;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.*;

/**
 * Tests the {@link Ensure} class.
 */
public class EnsureTest extends Assert {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void ensureNotNullWithoutMessage() {
        Ensure.ensureNotNull("");
        Ensure.ensureNotNull(new ArrayList<String>());
    }

    @Test
    public void ensureNotNull() {
        Ensure.ensureNotNull("", "failed %s", 1);
        Ensure.ensureNotNull(new ArrayList<String>(), "failed %s", 1);
    }

    @Test
    public void ensureNotNullWithoutMessageFail() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("must not be null");
        Ensure.ensureNotNull(null);
    }

    @Test
    public void ensureNotNullFail() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("failed 1");
        Ensure.ensureNotNull(null, "failed %s", 1);
    }

    @Test
    public void ensureNullWithoutMessage() {
        Ensure.ensureNull(null);
    }

    @Test
    public void ensureNull() {
        Ensure.ensureNull(null, "failed %s", 1);
    }

    @Test
    public void ensureNullWithoutMessageFail() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("must be null");
        Ensure.ensureNull("");
    }

    @Test
    public void ensureNullFail() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("failed 1");
        Ensure.ensureNull("", "failed %s", 1);
    }

    @Test
    public void ensureEqualsWithoutMessage() {
        Ensure.ensureEquals(1, 1);
    }

    @Test
    public void ensureEquals() {
        Ensure.ensureEquals(1, 1, "failed %s", 1);
    }

    @Test
    public void ensureEqualsWithoutMessageFail() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("must match");
        Ensure.ensureEquals(1, 2);
    }

    @Test
    public void ensureEqualsFail() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("failed 1");
        Ensure.ensureEquals(1, 2, "failed %s", 1);
    }

    @Test
    public void ensureNotEqualsWithoutMessage() {
        Ensure.ensureNotEquals(1, 2);
    }

    @Test
    public void ensureNotEquals() {
        Ensure.ensureNotEquals(1, 2, "failed %s", 1);
    }

    @Test
    public void ensureNotEqualsWithoutMessageFail() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("must differ");
        Ensure.ensureNotEquals(1, 1);
    }

    @Test
    public void ensureNotEqualsFail() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("failed 1");
        Ensure.ensureNotEquals(1, 1, "failed %s", 1);
    }

    @Test
    public void ensureTrueWithoutMessage() {
        Ensure.ensureTrue(1 == 1);
    }

    @Test
    public void ensureTrue() {
        Ensure.ensureTrue(1 == 1, "failed %s", 1);
    }

    @Test
    public void ensureTrueWithoutMessageFail() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("must be true");
        Ensure.ensureTrue(1 == 2);
    }

    @Test
    public void ensureTrueFail() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("failed 1");
        Ensure.ensureTrue(1 == 2, "failed %s", 1);
    }

    @Test
    public void ensureFalseWithoutMessage() {
        Ensure.ensureFalse(1 == 2);
    }

    @Test
    public void ensureFalse() {
        Ensure.ensureFalse(1 == 2, "failed %s", 1);
    }

    @Test
    public void ensureFalseWithoutMessageFail() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("must be false");
        Ensure.ensureFalse(1 == 1);
    }

    @Test
    public void ensureFalseFail() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("failed 1");
        Ensure.ensureFalse(1 == 1, "failed %s", 1);
    }

    @Test
    public void ensureNotEmptyStringWithoutMessage() {
        Ensure.ensureNotEmpty("hello");
    }

    @Test
    public void ensureNotEmptyString() {
        Ensure.ensureNotEmpty("hello", "failed %s", 1);
    }

    @Test
    public void ensureNotEmptyStringWithoutMessageFail1() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("string must not be empty");
        Ensure.ensureNotEmpty("");
    }

    @Test
    public void ensureNotEmptyStringWithoutMessageFail2() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("string must not be empty");
        Ensure.ensureNotEmpty((String) null);
    }

    @Test
    public void ensureNotEmptyStringFail1() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("failed 1");
        Ensure.ensureNotEmpty("", "failed %s", 1);
    }

    @Test
    public void ensureNotEmptyStringFail2() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("failed 1");
        Ensure.ensureNotEmpty((String)null, "failed %s", 1);
    }

    @Test
    public void ensureNotEmptyCollectionWithoutMessage() {
        Ensure.ensureNotEmpty(Arrays.asList(1, 2));
    }

    @Test
    public void ensureNotCollectionEmpty() {
        Ensure.ensureNotEmpty(Arrays.asList(1, 2), "failed %s", 1);
    }

    @Test
    public void ensureNotEmptyCollectionWithoutMessageFail1() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("collection must not be empty");
        Ensure.ensureNotEmpty(new ArrayList());
    }

    @Test
    public void ensureNotCollectionEmptyFail1() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("failed 1");
        Ensure.ensureNotEmpty(new ArrayList(), "failed %s", 1);
    }

    @Test
    public void ensureNotEmptyCollectionWithoutMessageFail2() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("collection must not be empty");
        Ensure.ensureNotEmpty((Collection)null);
    }

    @Test
    public void ensureNotCollectionEmptyFail2() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("failed 1");
        Ensure.ensureNotEmpty((Collection)null, "failed %s", 1);
    }

    @Test
    public void ensureNotEmptyMapWithoutMessage() {
        Map<String, String> value = new HashMap<String, String>();
        value.put("hello", "world");
        value = Ensure.ensureNotEmpty(value);
    }

    @Test
    public void ensureNotEmptyMap() {
        Map<String, String> value = new HashMap<String, String>();
        value.put("hello", "world");
        value = Ensure.ensureNotEmpty(value, "failed %s", 1);
    }

    @Test
    public void ensureNotEmptyMapWithoutMessageFail1() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("map must not be empty");
        Ensure.ensureNotEmpty(new HashMap());
    }

    @Test
    public void ensureNotMapEmptyFail1() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("failed 1");
        Ensure.ensureNotEmpty(new HashMap(), "failed %s", 1);
    }

    @Test
    public void ensureNotEmptyMapWithoutMessageFail2() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("map must not be empty");
        Ensure.ensureNotEmpty((Map)null);
    }

    @Test
    public void ensureNotMapEmptyFail2() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("failed 1");
        Ensure.ensureNotEmpty((Map)null, "failed %s", 1);
    }

    @Test
    public void ensureEmptyStringWithoutMessage() {
        Ensure.ensureEmpty((String) null);
        Ensure.ensureEmpty("");
    }

    @Test
    public void ensureEmptyString() {
        Ensure.ensureEmpty((String) null, "failed %s", 1);
        Ensure.ensureEmpty("", "failed %s", 1);
    }

    @Test
    public void ensureEmptyStringWithoutMessageFail() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("string must be empty");
        Ensure.ensureEmpty("hello");
    }

    @Test
    public void ensureEmptyStringFail() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("failed 1");
        Ensure.ensureEmpty("hello", "failed %s", 1);
    }

    @Test
    public void ensureEmptyCollectionWithoutMessage() {
        Ensure.ensureEmpty((Collection) null);
        Ensure.ensureEmpty(new ArrayList());
    }

    @Test
    public void ensureEmptyCollection() {
        Ensure.ensureEmpty((Collection) null, "failed %s", 1);
        Ensure.ensureEmpty(new ArrayList(), "failed %s", 1);
    }

    @Test
    public void ensureEmptyCollectionWithoutMessageFail() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("collection must be empty");
        Ensure.ensureEmpty(Arrays.asList(1, 2));
    }

    @Test
    public void ensureEmptyCollectionFail() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("failed 1");
        Ensure.ensureEmpty(Arrays.asList(1, 2), "failed %s", 1);
    }

    @Test
    public void ensureEmptyMapWithoutMessage() {
        Ensure.ensureEmpty((Map) null);
        Ensure.ensureEmpty(new HashMap());
    }

    @Test
    public void ensureEmptyMap() {
        Ensure.ensureEmpty((Map) null, "failed %s", 1);
        Ensure.ensureEmpty(new HashMap(), "failed %s", 1);
    }

    @Test
    public void ensureEmptyMapWithoutMessageFail() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("map must be empty");
        Map<String, String> value = new HashMap<String, String>();
        value.put("hello", "world");
        Ensure.ensureEmpty(value);
    }

    @Test
    public void ensureEmptyMapFail() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("failed 1");
        Map<String, String> value = new HashMap<String, String>();
        value.put("hello", "world");
        Ensure.ensureEmpty(value, "failed %s", 1);
    }

    @Test
    public void failWithMessage() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("failed 1");
        Ensure.fail("failed %s", 1);
    }

    @Test
    public void ensureInstanceOf() {
        Object value = "hello world";
        String str = Ensure.ensureInstanceOf(String.class, value, "failed %s", 1);
        Ensure.ensureEquals("hello world", str);
    }

    @Test
    public void ensureInstanceOfWithoutMessageFail() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("String");
        Object value = Collections.emptyList();
        Ensure.ensureInstanceOf(String.class, value);
    }

    @Test
    public void ensureInstanceOfFail() {
        exception.expect(EnsureFailedException.class);
        exception.expectMessage("failed 1");
        Object value = Collections.emptyList();
        Ensure.ensureInstanceOf(String.class, value, "failed %s", 1);
    }
}
