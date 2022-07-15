package online.jutter.smartcity.common.math

class Matrix(
    private var m: MutableList<MutableList<Double>>
) {

    companion object {

        fun create(w: Int, h: Int ,def: Double): Matrix {
            val m = mutableListOf<MutableList<Double>>()
            for (i in 0 until h) {
                m.add(mutableListOf())
                for (j in 0 until w) {
                    m.last().add(def)
                }
            }
            return Matrix(m)
        }
    }

    fun copy(): Matrix {
        val mt = mutableListOf<MutableList<Double>>()
        for (i in 0 until m.size) {
            mt.add(mutableListOf())
            for (j in 0 until m[0].size) {
                mt.last().add(this[i][j])
            }
        }
        return Matrix(mt)
    }

    fun w() = m[0].size
    fun h() = m.size

    operator fun get(i: Int) = m[i]

    override fun toString(): String {
        var str = "["
        m.forEachIndexed { j, line ->
            str += "["
            line.forEachIndexed { i, c ->
                str += c.toString() + if (i != line.lastIndex) ", " else ""
            }
            str += "]" + if (j != m.lastIndex) ", " else ""
        }
        str += "]"
        return str
    }

    fun t(): Matrix {
        val mt = mutableListOf<MutableList<Double>>()
        for (i in 0 until m[0].size) {
            mt.add(mutableListOf())
            for (j in 0 until m.size) {
                mt.last().add(this[j][i])
            }
        }
        return Matrix(mt)
    }

    operator fun times(matrix: Matrix): Matrix {
        val result = create(matrix.w(), this.h(), 0.0)

        for (row in 0 until result.h()) {
            for (col in 0 until result.w()) {
                result[row][col] = multiplyMatricesCell(this, matrix, row, col)
            }
        }

        return result
    }

    private fun multiplyMatricesCell(
        firstMatrix: Matrix,
        secondMatrix: Matrix,
        row: Int,
        col: Int
    ): Double {
        var cell = 0.0
        for (i in 0 until secondMatrix.h()) {
            cell += firstMatrix[row][i] * secondMatrix[i][col]
        }
        return cell
    }
}