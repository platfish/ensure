/*
 * Copyright 2017 Marc Ewert
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
package org.platfish.ensure;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
     *
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs   Arguments for the message format.
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
     *
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs   Arguments for the message format.
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
     *
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs   Arguments for the message format.
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
     *
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs   Arguments for the message format.
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
     *
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs   Arguments for the message format.
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
     *
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs   Arguments for the message format.
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
     *
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs   Arguments for the message format.
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
     *
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs   Arguments for the message format.
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
     *
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs   Arguments for the message format.
     */
    public static <V> List<V> ensureNotEmpty(List<V> value, String messageFormat, Object... messageArgs) {
        ensureTrue(value != null && !value.isEmpty(), messageFormat, messageArgs);
        return value;
    }

    /**
     * Throws {@link EnsureFailedException} if the given value is empty. The null value is also considered to be empty.
     */
    public static <K, V> Map<K, V> ensureNotEmpty(Map<K, V> value) {
        return ensureNotEmpty(value, "Given map must not be empty");
    }

    /**
     * Throws {@link EnsureFailedException} if the given value is empty. The null value is also considered to be empty.
     *
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs   Arguments for the message format.
     */
    public static <K, V> Map<K, V> ensureNotEmpty(Map<K, V> value, String messageFormat, Object... messageArgs) {
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
     *
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs   Arguments for the message format.
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
     *
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs   Arguments for the message format.
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
     *
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs   Arguments for the message format.
     */
    public static <V> List<V> ensureEmpty(List<V> value, String messageFormat, Object... messageArgs) {
        ensureTrue(value == null || value.isEmpty(), messageFormat, messageArgs);
        return value;
    }

    /**
     * Throws {@link EnsureFailedException} if the given value is not empty. The null value is also considered to be empty.
     */
    public static <K, V> Map<K, V> ensureEmpty(Map<K, V> value) {
        return ensureEmpty(value, "Given map must be empty");
    }

    /**
     * Throws {@link EnsureFailedException} if the given value is not empty. The null value is also considered to be empty.
     *
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs   Arguments for the message format.
     */
    public static <K, V> Map<K, V> ensureEmpty(Map<K, V> value, String messageFormat, Object... messageArgs) {
        ensureTrue(value == null || value.isEmpty(), messageFormat, messageArgs);
        return value;
    }

    /**
     * Throws {@link EnsureFailedException} if the given value is not an instance of the specified class.
     */
    public static <V> V ensureInstanceOf(Class<V> clazz, Object value) {
        return ensureInstanceOf(clazz, value, "Given value must be of type \"%s\" but found \"%s\"", clazz, value == null ? null : value.getClass());
    }

    /**
     * Throws {@link EnsureFailedException} if the given value is not an instance of the specified class.
     *
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs   Arguments for the message format.
     */
    public static <V> V ensureInstanceOf(Class<V> clazz, Object value, String messageFormat, Object... messageArgs) {
        ensureNotNull(clazz, "clazz must not be null");
        ensureTrue(value != null && clazz.isAssignableFrom(value.getClass()), messageFormat, messageArgs);
        return (V) value;
    }

    /**
     * Throws {@link EnsureFailedException} if the given Path does not exist.
     */
    public static Path ensureExists(Path value) {
        return ensureExists(value, "Path \"%s\" doesn't exist", value);
    }

    /**
     * Throws {@link EnsureFailedException} if the given Path does not exist.
     *
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs   Arguments for the message format.
     */
    public static Path ensureExists(Path value, String messageFormat, Object... messageArgs) {
        ensureNotNull(value, "value must not be null");
        ensureTrue(Files.exists(value), messageFormat, messageArgs);
        return value;
    }

    /**
     * Throws {@link EnsureFailedException} if the given Path does exist.
     */
    public static Path ensureNotExists(Path value) {
        return ensureNotExists(value, "Path \"%s\" already exists", value);
    }

    /**
     * Throws {@link EnsureFailedException} if the given Path does exist.
     *
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs   Arguments for the message format.
     */
    public static Path ensureNotExists(Path value, String messageFormat, Object... messageArgs) {
        ensureNotNull(value, "value must not be null");
        ensureFalse(Files.exists(value), messageFormat, messageArgs);
        return value;
    }

    /**
     * Throws {@link EnsureFailedException} if the given Path is not a directory.
     */
    public static Path ensureDirectory(Path value) {
        return ensureDirectory(value, "Path \"%s\" is not a directory", value);
    }

    /**
     * Throws {@link EnsureFailedException} if the given Path is not a directory.
     *
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs   Arguments for the message format.
     */
    public static Path ensureDirectory(Path value, String messageFormat, Object... messageArgs) {
        ensureNotNull(value, "value must not be null");
        ensureTrue(Files.isDirectory(value), messageFormat, messageArgs);
        return value;
    }

    /**
     * Throws {@link EnsureFailedException} if the given Path does not exist.
     */
    public static File ensureExists(File value) {
        return ensureExists(value, "File \"%s\" doesn't exist");
    }

    /**
     * Throws {@link EnsureFailedException} if the given Path does not exist.
     *
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs   Arguments for the message format.
     */
    public static File ensureExists(File value, String messageFormat, Object... messageArgs) {
        ensureNotNull(value, "value must not be null");
        ensureTrue(value.exists(), messageFormat, messageArgs);
        return value;
    }

    /**
     * Throws {@link EnsureFailedException} if the given Path does exist.
     */
    public static File ensureNotExists(File value) {
        return ensureNotExists(value, "File \"%s\" already exists", value);
    }

    /**
     * Throws {@link EnsureFailedException} if the given Path does exist.
     *
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs   Arguments for the message format.
     */
    public static File ensureNotExists(File value, String messageFormat, Object... messageArgs) {
        ensureNotNull(value, "value must not be null");
        ensureFalse(value.exists(), messageFormat, messageArgs);
        return value;
    }

    /**
     * Throws {@link EnsureFailedException} if the given Path is not a directory.
     */
    public static File ensureDirectory(File value) {
        return ensureDirectory(value, "File \"%s\" isn't a directory", value);
    }

    /**
     * Throws {@link EnsureFailedException} if the given Path is not a directory.
     *
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs   Arguments for the message format.
     */
    public static File ensureDirectory(File value, String messageFormat, Object... messageArgs) {
        ensureNotNull(value, "value must not be null");
        ensureTrue(value.isDirectory(), messageFormat, messageArgs);
        return value;
    }

    /**
     * Throws {@link EnsureFailedException} if the given Optional has no value.
     */
    public static <T> T ensureOptional(Optional<T> value) {
        return ensureOptional(value, "Optional has no value");
    }

    /**
     * Throws {@link EnsureFailedException} if the given Optional has no value.
     *
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs   Arguments for the message format.
     */
    public static <T> T ensureOptional(Optional<T> value, String messageFormat, Object... messageArgs) {
        ensureTrue(value.isPresent(), messageFormat, messageArgs);
        return value.get();
    }

    /**
     * Throws {@link EnsureFailedException} with the given message. This should be used in code blocks which shouldn't be reached.
     * For example in a final else statement or the default block of a switch statement.
     *
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs   Arguments for the message format.
     */
    public static void fail(String messageFormat, Object... messageArgs) {
        throw new EnsureFailedException(String.format(messageFormat, messageArgs));
    }

    /**
     * Throws {@link EnsureFailedException} if the given collection does not contain exactly one value.
     *
     * @return The single value of the collection.
     */
    public static <V> V ensureOne(Collection<V> value) {
        ensureEquals(1, value.size(), "Given collection must contain exactly one element");
        return value.iterator().next();
    }

    /**
     * Throws {@link EnsureFailedException} if the given collection does not contain exactly one value.
     *
     * @param messageFormat Format for the exception message according to {@link String#format(String, Object...)}.
     * @param messageArgs   Arguments for the message format.
     * @return The single value of the collection.
     */
    public static <V> V ensureOne(Collection<V> value, String messageFormat, Object... messageArgs) {
        ensureEquals(1, value.size(), messageFormat, messageArgs);
        return value.iterator().next();
    }
}
