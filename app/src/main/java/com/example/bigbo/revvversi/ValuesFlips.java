package com.example.bigbo.revvversi;

import static com.example.bigbo.revvversi.ActivityGame.clickedTimes;

class ValuesFlips {

    boolean valuesSouth(int[][] valueOfCell, int size, int vR, int vC, boolean isTesting) {
        int value = ((clickedTimes % 2) == 0) ? 1 : 2;
        boolean isFlippable = false;

        //проверка на возможность хода
        ActivityGame.SOUTH = false;
        for (int i = vR + 1; i < size; i++) {
            if (valueOfCell[vR + 1][vC] == 0) break;
            if (i == vR + 1 && (value + valueOfCell[i][vC] != 3)) break;
            if (i > vR + 1 && valueOfCell[i][vC] == 0) break;
            if (valueOfCell[i][vC] == value) {
                isFlippable = true;
                if (isTesting) return true;
                break;
            }
        }

        //переворачивание фишек
        //(если не делается проверка на возможность хода и заполнения поля)
        if (!isTesting) {
            int i = vR + 1;
            while (isFlippable && i < size) {
                if (valueOfCell[i][vC] == value) isFlippable = false;
                else {
                    valueOfCell[i][vC] = value;
                    ActivityGame.SOUTH = true;
                }
                i++;
            }
        }

        return isFlippable;
    }

    boolean valuesNorth(int[][] valueOfCell, int vR, int vC, boolean isTesting) {
        int value = ((clickedTimes % 2) == 0) ? 1 : 2;
        boolean isFlippable = false;

        //логика аналогична предыдуещему методу
        //(то же самое для всех остальных методов)
        ActivityGame.NORTH = false;
        for (int i = vR - 1; i >= 0; i--) {
            if (valueOfCell[vR - 1][vC] == 0) break;
            if (i == vR - 1 && (value + valueOfCell[i][vC] != 3)) break;
            if (i < vR - 1 && valueOfCell[i][vC] == 0) break;
            if (valueOfCell[i][vC] == value) {
                isFlippable = true;
                if (isTesting) return true;
                break;
            }
        }


        if (!isTesting) {
            int i = vR - 1;
            while (isFlippable && i > 0) {
                if (valueOfCell[i][vC] == value) isFlippable = false;
                else {
                    valueOfCell[i][vC] = value;
                    ActivityGame.NORTH = true;
                }
                i--;
            }
        }
        return isFlippable;
    }

    boolean valuesWest(int[][] valueOfCell, int vR, int vC, boolean isTesting) {
        boolean isFlippable = false;
        int value = ((clickedTimes % 2) == 0) ? 1 : 2;

        ActivityGame.WEST = false;
        for (int i = vC - 1; i >= 0; i--) {
            if (valueOfCell[vR][vC - 1] == 0) break;
            if (i == vC - 1 && (value + valueOfCell[vR][i] != 3)) {
                break;
            }
            if (i < vC - 1 && valueOfCell[vR][i] == 0) break;
            if (valueOfCell[vR][i] == value) {
                isFlippable = true;
                if (isTesting) return true;
                break;
            }
        }


        if (!isTesting) {
            int i = vC - 1;
            while (isFlippable && i >= 0) {
                if (valueOfCell[vR][i] == value) isFlippable = false;
                else {
                    valueOfCell[vR][i] = value;
                    ActivityGame.WEST = true;
                }
                i--;
            }
        }

        return isFlippable;
    }

    boolean valuesEast(int[][] valueOfCell, int size, int vR, int vC, boolean isTesting) {
        int value = ((clickedTimes % 2) == 0) ? 1 : 2;
        boolean isFlippable = false;

        ActivityGame.EAST = false;
        for (int i = vC + 1; i < size; i++) {
            if (valueOfCell[vR][vC + 1] == 0) break;
            if (i == vC + 1 && (value + valueOfCell[vR][i] != 3)) break;
            if (i > vC + 1 && valueOfCell[vR][i] == 0) break;
            if (valueOfCell[vR][i] == value) {
                isFlippable = true;
                if (isTesting) return true;
                break;
            }
        }


        if (!isTesting) {
            int i = vC + 1;
            while (isFlippable && i < size) {
                if (valueOfCell[vR][i] == value) isFlippable = false;
                else {
                    valueOfCell[vR][i] = value;
                    ActivityGame.EAST = true;
                }
                i++;
            }
        }

        return isFlippable;
    }

    boolean valuesSouthEast(int[][] valueOfCell, int size, int vR, int vC, boolean isTesting) {
        int value = ((clickedTimes % 2) == 0) ? 1 : 2;
        boolean isFlippable = false;
        int c = vC + 1;
        int r = vR + 1;

        ActivityGame.SOUTHEAST = false;
        while (c < size && r < size) {
            if (valueOfCell[vR + 1][vC + 1] == 0) break;
            if (c == vC + 1 && (value + valueOfCell[r][c] != 3)) break;
            if (c > vC + 1 && valueOfCell[r][c] == 0) break;
            if (valueOfCell[r][c] == value) {
                isFlippable = true;
                if (isTesting) return true;
                break;
            }
            c++;
            r++;
        }


        if (!isTesting) {
            c = vC + 1;
            r = vR + 1;
            while (isFlippable && c < size && r < size) {
                if (valueOfCell[r][c] == value) isFlippable = false;
                else {
                    valueOfCell[r][c] = value;
                    ActivityGame.SOUTHEAST = true;
                }
                c++;
                r++;
            }
        }

        return isFlippable;
    }

    boolean valuesNorthWest(int[][] valueOfCell, int vR, int vC, boolean isTesting) {
        int value = ((clickedTimes % 2) == 0) ? 1 : 2;
        boolean isFlippable = false;
        int c = vC - 1;
        int r = vR - 1;

        ActivityGame.NORTHWEST = false;
        while (c >= 0 && r >= 0) {
            if (valueOfCell[vR - 1][vC - 1] == 0) break;
            if (c == vC - 1 && (value + valueOfCell[r][c] != 3)) break;
            if (c < vC - 1 && valueOfCell[r][c] == 0) break;
            if (valueOfCell[r][c] == value) {
                isFlippable = true;
                if (isTesting) return true;
                break;
            }
            c--;
            r--;
        }

        if (!isTesting) {
            c = vC - 1;
            r = vR - 1;
            while (isFlippable && c >= 0 && r >= 0) {
                if (valueOfCell[r][c] == value) isFlippable = false;
                else {
                    valueOfCell[r][c] = value;
                    ActivityGame.NORTHWEST = true;
                }
                c--;
                r--;
            }
        }

        return isFlippable;
    }

    boolean valuesNorthEast(int[][] valueOfCell, int size, int vR, int vC, boolean isTesting) {
        int value = ((clickedTimes % 2) == 0) ? 1 : 2;
        boolean isFlippable = false;
        int c = vC + 1;
        int r = vR - 1;

        ActivityGame.NORTHEAST = false;
        while (c < size && r >= 0) {
            if (valueOfCell[vR - 1][vC + 1] == 0) break;
            if (c == vC + 1 && (value + valueOfCell[r][c] != 3)) break;
            if (c > vC + 1 && valueOfCell[r][c] == 0) break;
            if (valueOfCell[r][c] == value) {
                isFlippable = true;
                if (isTesting) return true;
                break;
            }
            c++;
            r--;
        }

        if (!isTesting) {
            c = vC + 1;
            r = vR - 1;
            while (isFlippable && c < size && r >= 0) {
                if (valueOfCell[r][c] == value) isFlippable = false;
                else {
                    valueOfCell[r][c] = value;
                    ActivityGame.NORTHEAST = true;
                }
                c++;
                r--;
            }
        }

        return isFlippable;
    }

    boolean valuesSouthWest(int[][] valueOfCell, int size, int vR, int vC, boolean isTesting) {
        int value = ((clickedTimes % 2) == 0) ? 1 : 2;
        boolean isFlippable = false;
        int c = vC - 1;
        int r = vR + 1;

        ActivityGame.SOUTHWEST = false;
        while (r < size && c >= 0) {
            if (valueOfCell[vR + 1][vC - 1] == 0) break;
            if (c == vC - 1 && (value + valueOfCell[r][c] != 3)) break;
            if (c < vC - 1 && valueOfCell[r][c] == 0) break;
            if (valueOfCell[r][c] == value) {
                isFlippable = true;
                if (isTesting) return true;
                break;
            }
            c--;
            r++;
        }

        if (!isTesting) {
            c = vC - 1;
            r = vR + 1;
            while (isFlippable && r < size && c >= 0) {
                if (valueOfCell[r][c] == value) isFlippable = false;
                else {
                    valueOfCell[r][c] = value;
                    ActivityGame.SOUTHWEST = true;
                }
                c--;
                r++;
            }
        }

        return isFlippable;
    }
}
