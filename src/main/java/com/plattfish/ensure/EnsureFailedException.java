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
package com.plattfish.ensure;

/**
 * <p>Unchecked exception indicating that an assumption has been violated.</p>
 */
public class EnsureFailedException extends RuntimeException{

    /**
     * Creates a new instance with the given message.
     */
    public EnsureFailedException(String message) {
        super(message);
    }
}
