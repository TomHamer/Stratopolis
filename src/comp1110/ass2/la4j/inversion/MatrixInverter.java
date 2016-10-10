/*
 * Copyright 2011-2013, by Vladimir Kostyukov and Contributors.
 * 
 * This file is part of la4j project (http://la4j.org)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Contributor(s): -
 * 
 */

package comp1110.ass2.la4j.inversion;

import comp1110.ass2.la4j.Matrix;

import java.io.Serializable;

public interface MatrixInverter extends Serializable {

    /**
     * Inverse matrix.
     *
     * @return
     */
    Matrix inverse();

    /**
     * Returns the self matrix of this inverter.
     *
     * @return
     */
    Matrix self();
}
