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

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>Helper for finding programmatic errors as early as possible. Existing
 * assumptions should be ensured by using a corresponding ensure method.
 * Violations against this assumptions will lead to an {@link EnsureFailedException}
 * with an optional message.</p>
 * <p>The ensure methods are not meant for validating user input, this should
 * be done by other mechanics.</p>
 */
public final class Ensure {

    /**
     * Throws {@link EnsureFailedException} if the given value is null.
     */
    public static <T> T ensureNotNull(T value) {
        return ensureNotNull(value, "Given value must not be null");
    }

    /**
     * Throws {@link EnsureFailedException} if the given value is null.
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs Arguments for the message format.
     */
    public static <T> T ensureNotNull(T value, String messageFormat, Object... messageArgs) {
        ensureTrue(value != null, messageFormat, messageArgs);
        return value;
    }

    /**
     * Throws {@link EnsureFailedException} if the given value is not null.
     */
    public static <T> void ensureNull(T value) {
        ensureNull(value, "Given value must be null");
    }

    /**
     * Throws {@link EnsureFailedException} if the given value is not null.
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs Arguments for the message format.
     */
    public static <T> void ensureNull(T value, String messageFormat, Object... messageArgs) {
        ensureTrue(value == null, messageFormat, messageArgs);
    }

    /**
     * Throws {@link EnsureFailedException} if the given value doesn't match expected. {@link Object#equals(Object)}
     * will be used for comparing the two values.
     */
    public static <T> T ensureEquals(T expected, T value) {
        return ensureEquals(expected, value, "Given value must match the expected value");
    }

    /**
     * Throws {@link EnsureFailedException} if the given value doesn't match expected. {@link Object#equals(Object)}
     * will be used for comparing the two values.
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs Arguments for the message format.
     */
    public static <T> T ensureEquals(T expected, T value, String messageFormat, Object... messageArgs) {
        if (expected == null) {
            ensureTrue(value == null, messageFormat, messageArgs);
        } else {
            ensureTrue(expected.equals(value), messageFormat, messageArgs);
        }
        return value;
    }

    /**
     * Throws {@link EnsureFailedException} if the given value does match expected. {@link Object#equals(Object)}
     * will be used for comparing the two values.
     */
    public static <T> T ensureNotEquals(T expected, T value) {
        return ensureNotEquals(expected, value, "Given value must differ from expected value");
    }

    /**
     * Throws {@link EnsureFailedException} if the given value does match expected. {@link Object#equals(Object)}
     * will be used for comparing the two values.
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs Arguments for the message format.
     */
    public static <T> T ensureNotEquals(T expected, T value, String messageFormat, Object... messageArgs) {
        if (expected == null) {
            ensureFalse(value == null, messageFormat, messageArgs);
        } else {
            ensureFalse(expected.equals(value), messageFormat, messageArgs);
        }
        return value;
    }

    /**
     * Throws {@link EnsureFailedException} if the given condition isn't true.
     */
    public static void ensureTrue(boolean condition) {
        ensureTrue(condition, "Given condition must be true");
    }

    /**
     * Throws {@link EnsureFailedException} if the given condition isn't true.
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs Arguments for the message format.
     */
    public static void ensureTrue(boolean condition, String messageFormat, Object... messageArgs) {
        if (!condition) {
            fail(messageFormat, messageArgs);
        }
    }

    /**
     * Throws {@link EnsureFailedException} if the given condition isn't false.
     */
    public static void ensureFalse(boolean condition) {
        ensureFalse(condition, "Given condition must be false");
    }

    /**
     * Throws {@link EnsureFailedException} if the given condition isn't false.
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs Arguments for the message format.
     */
    public static void ensureFalse(boolean condition, String messageFormat, Object... messageArgs) {
        if (condition) {
            fail(messageFormat, messageArgs);
        }
    }

    /**
     * Throws {@link EnsureFailedException} if the given value is empty. The null value is also considered to be empty.
     */
    public static String ensureNotEmpty(String value) {
        return ensureNotEmpty(value, "Given string must not be empty");
    }

    /**
     * Throws {@link EnsureFailedException} if the given value is empty. The null value is also considered to be empty.
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs Arguments for the message format.
     */
    public static String ensureNotEmpty(String value, String messageFormat, Object... messageArgs) {
        ensureTrue(value != null && !value.isEmpty(), messageFormat, messageArgs);
        return value;
    }

    /**
     * Throws {@link EnsureFailedException} if the given value is empty. The null value is also considered to be empty.
     */
    public static <V> Collection<V> ensureNotEmpty(Collection<V> value) {
        return ensureNotEmpty(value, "Given collection must not be empty");
    }

    /**
     * Throws {@link EnsureFailedException} if the given value is empty. The null value is also considered to be empty.
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs Arguments for the message format.
     */
    public static <V> Collection<V> ensureNotEmpty(Collection<V> value, String messageFormat, Object... messageArgs) {
        ensureTrue(value != null && !value.isEmpty(), messageFormat, messageArgs);
        return value;
    }

    /**
     * Throws {@link EnsureFailedException} if the given value is empty. The null value is also considered to be empty.
     */
    public static <V> List<V> ensureNotEmpty(List<V> value) {
        return ensureNotEmpty(value, "Given collection must not be empty");
    }

    /**
     * Throws {@link EnsureFailedException} if the given value is empty. The null value is also considered to be empty.
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs Arguments for the message format.
     */
    public static <V> List<V> ensureNotEmpty(List<V> value, String messageFormat, Object... messageArgs) {
        ensureTrue(value != null && !value.isEmpty(), messageFormat, messageArgs);
        return value;
    }

    /**
     * Throws {@link EnsureFailedException} if the given value is empty. The null value is also considered to be empty.
     */
    public static <K,V> Map<K,V> ensureNotEmpty(Map<K,V> value) {
        return ensureNotEmpty(value, "Given map must not be empty");
    }

    /**
     * Throws {@link EnsureFailedException} if the given value is empty. The null value is also considered to be empty.
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs Arguments for the message format.
     */
    public static <K,V> Map<K,V> ensureNotEmpty(Map<K,V> value, String messageFormat, Object... messageArgs) {
        ensureTrue(value != null && !value.isEmpty(), messageFormat, messageArgs);
        return value;
    }

    /**
     * Throws {@link EnsureFailedException} if the given value is not empty. The null value is also considered to be empty.
     */
    public static String ensureEmpty(String value) {
        return ensureEmpty(value, "Given string must be empty");
    }

    /**
     * Throws {@link EnsureFailedException} if the given value is not empty. The null value is also considered to be empty.
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs Arguments for the message format.
     */
    public static String ensureEmpty(String value, String messageFormat, Object... messageArgs) {
        ensureTrue(value == null || value.isEmpty(), messageFormat, messageArgs);
        return value;
    }

    /**
     * Throws {@link EnsureFailedException} if the given value is not empty. The null value is also considered to be empty.
     */
    public static <V> Collection<V> ensureEmpty(Collection<V> value) {
        return ensureEmpty(value, "Given collection must be empty");
    }

    /**
     * Throws {@link EnsureFailedException} if the given value is not empty. The null value is also considered to be empty.
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs Arguments for the message format.
     */
    public static <V> Collection<V> ensureEmpty(Collection<V> value, String messageFormat, Object... messageArgs) {
        ensureTrue(value == null || value.isEmpty(), messageFormat, messageArgs);
        return value;
    }

    /**
     * Throws {@link EnsureFailedException} if the given value is not empty. The null value is also considered to be empty.
     */
    public static <V> List<V> ensureEmpty(List<V> value) {
        return ensureEmpty(value, "Given collection must be empty");
    }

    /**
     * Throws {@link EnsureFailedException} if the given value is not empty. The null value is also considered to be empty.
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs Arguments for the message format.
     */
    public static <V> List<V> ensureEmpty(List<V> value, String messageFormat, Object... messageArgs) {
        ensureTrue(value == null || value.isEmpty(), messageFormat, messageArgs);
        return value;
    }

    /**
     * Throws {@link EnsureFailedException} if the given value is not empty. The null value is also considered to be empty.
     */
    public static <K,V> Map<K,V> ensureEmpty(Map<K,V> value) {
        return ensureEmpty(value, "Given map must be empty");
    }

    /**
     * Throws {@link EnsureFailedException} if the given value is not empty. The null value is also considered to be empty.
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs Arguments for the message format.
     */
    public static <K,V> Map<K,V> ensureEmpty(Map<K,V> value, String messageFormat, Object... messageArgs) {
        ensureTrue(value == null || value.isEmpty(), messageFormat, messageArgs);
        return value;
    }

    /**
     * Throws {@link EnsureFailedException} with the given message. This should be used in code blocks which shouldn't be reached.
     * For example in a final else statement or the default block of a switch statement.
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs Arguments for the message format.
     */
    public static void fail(String messageFormat, Object... messageArgs) {
        throw new EnsureFailedException(String.format(messageFormat, messageArgs));
    }
}
