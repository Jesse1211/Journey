
/*
初始化矩阵：首先，创建一个二维数组或矩阵，用来表示装有1-10000编号的气球的位置。
检查并爆炸：遍历矩阵中的每个气球，对于每个气球，检查其上下左右四个方向的气球。
如果在这四个方向中，有至少两个气球与当前气球编号相同，则这几个相同编号的气球都需要被标记为爆炸。
更新矩阵：将所有标记为爆炸的气球在矩阵中设置为0。
重力效应：对于矩阵中的每一列，从下往上遍历，把所有的非0数字向下移动到最下面的位置，空出的位置用0填充。
重复检查：重复步骤2到步骤4，直到没有更多的气球可以爆炸为止。
*/

class Solution {
    public int[][] bubbles(int[][] bubbles) {
        int rows = bubbles.length;
        int cols = bubbles[0].length;
        boolean hasBubbles = true;
        while (hasBubbles) {
            hasBubbles = false;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (bubbles[i][j] == 0)
                        continue;

                    // check if the bubble should be exploded
                    int count = 0;
                    int val = bubbles[i][j];
                    if (i > 0 && bubbles[i - 1][j] == val)
                        count++;
                    if (i < rows - 1 && bubbles[i + 1][j] == val)
                        count++;
                    if (j > 0 && bubbles[i][j - 1] == val)
                        count++;
                    if (j < cols - 1 && bubbles[i][j + 1] == val)
                        count++;

                    if (count >= 2) {
                        bubbles[i][j] = 0;
                        if (i > 0 && bubbles[i - 1][j] == val)
                            bubbles[i - 1][j] = 0;
                        if (i < rows - 1 && bubbles[i + 1][j] == val)
                            bubbles[i + 1][j] = 0;
                        if (j < cols - 1 && bubbles[i][j + 1] == val)
                            bubbles[i][j + 1] = 0;
                        if (j > 0 && bubbles[i][j - 1] == val)
                            bubbles[i][j - 1] = 0;

                        hasBubbles = true;
                    }
                }
            }
            applyGravity(bubbles);
        }
        return bubbles;
    }

    public void applyGravity(int[][] bubbles) {
        int rows = bubbles.length;
        int cols = bubbles[0].length;
        for (int j = 0; j < cols; j++) {
            int idx = rows - 1;
            for (int i = rows - 1; i >= 0; i--) {
                if (bubbles[i][j] != 0) {
                    bubbles[idx][j] = bubbles[i][j];
                    if (idx != i)
                        bubbles[i][j] = 0;
                    idx--;
                }
            }
        }
    }
}
