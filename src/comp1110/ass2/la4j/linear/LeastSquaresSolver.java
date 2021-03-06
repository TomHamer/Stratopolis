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

package comp1110.ass2.la4j.linear;

import comp1110.ass2.la4j.LinearAlgebra;
import comp1110.ass2.la4j.Matrix;
import comp1110.ass2.la4j.Vector;
import comp1110.ass2.la4j.Vectors;
import comp1110.ass2.la4j.decomposition.MatrixDecompositor;

public class LeastSquaresSolver extends AbstractSolver implements LinearSystemSolver {

    private static final long serialVersionUID = 4071505L;

    // Matrices from RAW_QR decomposition
    private final Matrix qr;
    private final Matrix r;

    public LeastSquaresSolver(Matrix a) {
        super(a);

        // we use QR for this
        MatrixDecompositor decompositor = a.withDecompositor(LinearAlgebra.RAW_QR);
        Matrix[] qrr = decompositor.decompose();

        // TODO: Do something with it.
        this.qr = qrr[0];
        this.r = qrr[1];
    }

    @Override
    public Vector solve(Vector b) {
        ensureRHSIsCorrect(b);

        int n = unknowns();
        int m = equations();

        // check whether the matrix is full-rank or not
        for (int i = 0; i < r.rows(); i++) {
            if (r.get(i, i) == 0.0) {
                fail("This system can not be solved: coefficient matrix is rank deficient.");
            }
        }

        Vector x = b.copy();

        for (int j = 0; j < n; j++) {

            double acc = 0.0;

            for (int i = j; i < m; i++) {
                acc += qr.get(i, j) * x.get(i);
            }

            acc = -acc / qr.get(j, j);
            for (int i = j; i < m; i++) {
                x.updateAt(i, Vectors.asPlusFunction(acc * qr.get(i, j)));
            }
        }

        for (int j = n - 1; j >= 0; j--) {
            x.updateAt(j, Vectors.asDivFunction(r.get(j, j)));

            for (int i = 0; i < j; i++) {
                x.updateAt(i, Vectors.asMinusFunction(x.get(j) * qr.get(i, j)));
            }
        }

        return x.slice(0, n);
    }

    @Override
    public boolean applicableTo(Matrix matrix) {
        return  matrix.rows() >= matrix.columns();
    }
}
