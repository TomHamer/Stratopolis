package comp1110.ass2.la4j.operation.ooplace;

import comp1110.ass2.la4j.Matrix;
import comp1110.ass2.la4j.operation.CommonMatrixMatrixOperation;

public class OoPlaceKroneckerProduct extends CommonMatrixMatrixOperation<Matrix> {

    // TODO: It should not be common.

    @Override
    public Matrix applyCommon(Matrix a, Matrix b) {
        int n = a.rows() * b.rows();
        int m = a.columns() * b.columns();
        int p = b.rows();
        int q = b.columns();

        Matrix result = a.blankOfShape(n, m);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result.set(i, j, a.get(i / p, j / q) * b.get(i % p, j % q));
            }
        }

        return result;
    }
}
