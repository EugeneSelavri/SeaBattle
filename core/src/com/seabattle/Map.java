package com.seabattle;

public class Map {

    public static int[][] generate() {
        int i;
        int j;
        int dir;
        int [][]map = new int[10][10];

        for (int k = 1; k < 5 ; k++) {
            int deck = 5 - k;

            for (int l = 0; l < k ; l++) {
                do {
                    i = (int) (Math.random() * map.length);
                    j = (int) (Math.random() * map.length);
                    dir = (int) (Math.random() * 4);
                } while (!checkDir(i, j, deck, dir, map));
                fillDir(i, j, deck, dir, map);
            }
        }
        return map;
    }


    private static boolean checkDir(int i, int j, int deck, int dir, int[][] map) {
        switch (dir) {
            case 0: return checkWest(i, j, deck, map);
            case 1: return checkNorth(i, j, deck, map);
            case 2: return checkEast(i, j, deck, map);
            case 3: return checkSouth(i, j, deck, map);
            default: return false;
        }
    }

    private static void fillDir(int i, int j, int deck, int dir, int[][] map) {
        switch (dir) {
            case 0: fillWest(i, j, deck, map); break;
            case 1: fillNorth(i, j, deck, map); break;
            case 2: fillEast(i, j, deck, map); break;
            case 3: fillSouth(i, j, deck, map); break;
            default: break;
        }
    }

    private static boolean checkNorth(int i, int j, int deck, int[][] map) {
        if (i - deck < 0) {
            return false;
        }

        for (int k = i + 1; k >= i - deck; k--) {
            if (isAvailable(k, j, map)) {
                if (map[k][j] != 0) {
                    return false;
                }
            }

            if (isAvailable(k, j-1, map)) {
                if (map[k][j-1] != 0) {
                    return false;
                }
            }

            if (isAvailable(k, j+1, map)) {
                if (map[k][j+1] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean checkSouth(int i, int j, int deck, int[][] map) {
        if (i + deck > map.length - 1) {
            return false;
        }

        for (int k = i - 1; k <= i + deck; k++) {
            if (isAvailable(k, j, map)) {
                if (map[k][j] != 0) {
                    return false;
                }
            }

            if (isAvailable(k, j-1, map)) {
                if (map[k][j-1] != 0) {
                    return false;
                }
            }

            if (isAvailable(k, j+1, map)) {
                if (map[k][j+1] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean checkWest(int i, int j, int deck, int[][] map) {
        if (j - deck < 0) {
            return false;
        }

        for (int k = j + 1; k >= j - deck; k--) {
            if (isAvailable(i, k, map)) {
                if (map[i][k] != 0) {
                    return false;
                }
            }

            if (isAvailable(i - 1, k, map)) {
                if (map[i - 1][k] != 0) {
                    return false;
                }
            }

            if (isAvailable(i + 1, k, map)) {
                if (map[i + 1][k] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean checkEast(int i, int j, int deck, int[][] map) {
        if (j + deck > map[i].length - 1) {
            return false;
        }

        for (int k = j - 1; k <= j + deck; k++) {
            if (isAvailable(i, k, map)) {
                if (map[i][k] != 0) {
                    return false;
                }
            }

            if (isAvailable(i - 1, k, map)) {
                if (map[i - 1][k] != 0) {
                    return false;
                }
            }

            if (isAvailable(i + 1, k, map)) {
                if (map[i + 1][k] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void fillWest(int i, int j, int deck, int[][] map) {
        for (int k = j; k > j - deck ; k--) {
            map[i][k] = 1;
        }
    }

    private static void fillEast(int i, int j, int deck, int[][] map) {
        for (int k = j; k < j + deck ; k++) {
            map[i][k] = 1;
        }
    }

    private static void fillNorth(int i, int j, int deck, int[][] map) {
        for (int k = i; k > i - deck ; k--) {
            map[k][j] = 1;
        }
    }

    private static void fillSouth(int i, int j, int deck, int[][] map) {
        for (int k = i; k < i + deck ; k++) {
            map[k][j] = 1;
        }
    }

    private static boolean isAvailable(int i, int j, int[][] map) {
        return i >= 0 && i < map.length && j >= 0 && j < map[0].length;
    }
}
