package org.freelectron.winline;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by leobel on 7/12/16.
 */
public class LogicWinLine implements Serializable {

    public int checkerCount;
    private Color[] colors;
    private int dimesion;
    private boolean[] usedPositions;
    private Checker[] next;
    private int score;
    private Random random;
    private Checker[][] board;
    private int[] vx;
    private int[] vy;

    public enum Color {
        RED,
        YELLOW,
        BLUE,
        GREEN,
        BLACK,
        WHITE,
        Empty
    }

    public LogicWinLine(int dimesion) throws Exception {
        this.colors = new Color[]{Color.YELLOW, Color.BLUE, Color.WHITE, Color.BLACK, Color.RED, Color.GREEN};
        this.vx = new int[]{ 0, 0, -1, 1, -1, 1,  1, -1};
        this.vy = new int[]{-1, 1,  0, 0, -1, 1, -1,  1};
        this.random = new Random();
        this.usedPositions = new boolean[(dimesion * dimesion)];
        this.dimesion = dimesion;
        this.score = 0;
        this.board = (Checker[][]) Array.newInstance(Checker.class, new int[]{dimesion, dimesion});
        this.next = new Checker[3];
        startBoard(6);
        buildNext(3);
        this.checkerCount = 6;
    }

    public LogicWinLine(Checker[][] board, Checker[] next, int score) {
        this.colors = new Color[]{Color.YELLOW, Color.BLUE, Color.WHITE, Color.BLACK, Color.RED, Color.GREEN};
        this.vx = new int[]{ 0, 0, -1, 1, -1, 1,  1, -1};
        this.vy = new int[]{-1, 1,  0, 0, -1, 1, -1,  1};
        this.board = board;
        this.next = next;
        this.score = score;
        this.dimesion = board.length;
        this.usedPositions = new boolean[(this.dimesion * this.dimesion)];
        this.random = new Random();
        updateGame();
    }

    private void updateGame() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j] != null) {
                    this.checkerCount++;
                    this.usedPositions[(this.dimesion * i) + j] = true;
                }
            }
        }
    }

    public Checker[][] getBoard() {
        return this.board;
    }

    public void moveChecker(MPoint org, MPoint dest, Checker f) {
        f.setPosition(dest);
        this.board[org.getX()][org.getY()] = null;
        this.board[dest.getX()][dest.getY()] = f;
        this.usedPositions[(this.dimesion * org.getX()) + org.getY()] = false;
        this.usedPositions[(this.dimesion * dest.getX()) + dest.getY()] = true;
    }

    public Checker[] getNext() {
        return this.next;
    }

    public void buildNext(int count) throws Exception {
        for (int i = 0; i < count; i++) {
            this.next[i] = new Checker(getRandomColor());
        }
    }

    public String getPath(MPoint org, MPoint dest) {
        return getPath((this.dimesion * org.getX()) + org.getY(), (this.dimesion * dest.getX()) + dest.getY());
    }

    public int getCheckerCount(){return checkerCount;}

    public List<Integer> addCheckers() throws Exception {
        ArrayList<Integer> result = new ArrayList();
        int maxCount = this.dimesion * this.dimesion;
        for (Checker Checker : this.next) {
            if (this.checkerCount < maxCount) {
                MPoint p = getRandomPosition();
                Checker.setPosition(p);
                this.board[p.getX()][p.getY()] = Checker;
                int position = (this.dimesion * p.getX()) + p.getY();
                this.usedPositions[position] = true;
                result.add(position);
                this.checkerCount++;
            }
        }
        return result;
    }

    public boolean canDelete(MPoint src, MPoint[] pairs, int[] score) {
        for (int j = 0; j < pairs.length; j++) {
            pairs[j] = new MPoint(src.getX(), src.getY());
        }
        for (int i = 0; i < vx.length; i += 2) {
            boolean keepDir1 = true;
            boolean keepDir2 = true;
            for (int k = 1; k <= dimesion; k++) {
                int x = src.getX() + (vx[i] * k);
                int y = src.getY() + (vy[i] * k);
                if(keepDir1 && x >= 0 && x < dimesion && y >= 0 && y < dimesion && board[x][y] != null && board[x][y].equals(board[src.getX()][src.getY()])) {
                    pairs[i].setX(x);
                    pairs[i].setY(y);
                }
                else {
                    keepDir1 = false;
                }
                int x1 = src.getX() + (vx[i + 1] * k);
                int y1 = src.getY() + (vy[i + 1] * k);
                if (keepDir2 && x1 >= 0 && x1 < dimesion && y1 >= 0 && y1 < dimesion && board[x1][y1] != null && board[x1][y1].equals(board[src.getX()][src.getY()])) {
                    pairs[i + 1].setX(x1);
                    pairs[i + 1].setY(y1);
                }
                else{
                    keepDir2 = false;
                }
                if (!keepDir1 && !keepDir2) {
                    break;
                }
            }
        }
        boolean result = false;
        int d = 0;
        for (int i = 0; i < pairs.length; i += 2){
            MPoint p1 = pairs[i];
            MPoint p2 = pairs[i + 1];
            int distance = p1.getX() != p2.getX() ? Math.abs(p1.getX() - p2.getX()) + 1 : Math.abs(p1.getY() - p2.getY()) + 1;
            score[d] = distance;
            if (distance >= 5) {
                result = true;
            }
            d++;
        }
        return result;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public Integer getScore() {
        return this.score;
    }

    public List<Integer> deleteSequence(MPoint from, MPoint to, int direction) {
        int k = 0;
        int x = from.getX();
        int y = from.getY();
        int position;
        ArrayList result = new ArrayList();
        while (true) {
            if (x == to.getX() && y == to.getY()) {
                break;
            }
            if (this.board[x][y] != null) {
                this.board[x][y] = null;
                position = (this.dimesion * x) + y;
                this.usedPositions[position] = false;
                result.add(position);
                this.checkerCount--;
            }
            k++;
            x = from.getX() + (this.vx[direction] * k);
            y = from.getY() + (this.vy[direction] * k);
        }
        if (this.board[to.getX()][to.getY()] != null) {
            this.board[to.getX()][to.getY()] = null;
            position = (this.dimesion * to.getX()) + to.getY();
            this.usedPositions[position] = false;
            result.add(position);
            this.checkerCount--;
        }
        return result;
    }

    public boolean gameOver() {
        return this.checkerCount == 0 || this.checkerCount == this.dimesion * this.dimesion;
    }



    private String getPath(int from, int to) {
        int[] queue = new int[(this.dimesion * this.dimesion)];
        int[] parent = new int[queue.length];
        boolean[] visited = new boolean[queue.length];
        queue[0] = from;
        parent[from] = -1;
        if (from == to) {
            return buildPath(parent, from, to);
        }
        int count = 1;
        int[] vx = new int[]{ 0, 0, -1, 1};
        int[] vy = new int[]{-1, 1,  0, 0};
        int index = 0;
        while (index < count) {
            int current = queue[index++];
            visited[current] = true;
            int x = current / this.dimesion;
            int y = current % this.dimesion;
            for (int i = 0; i< vx.length; i++) {
                int xNeighbor = x + vx[i];
                int yNeighbor = y + vy[i];
                int neighbor = (this.dimesion * xNeighbor) + yNeighbor;
                if (xNeighbor >= 0 && xNeighbor < dimesion && yNeighbor >= 0 && yNeighbor < dimesion && !visited[neighbor] && board[xNeighbor][yNeighbor] == null) {
                    queue[count++] = neighbor;
                    visited[neighbor] = true;
                    parent[neighbor] = current;
                    if (neighbor == to) {
                        return buildPath(parent, from, to);
                    }
                }
            }
        }
        return null;
    }

    private String buildPath(int[] pred, int org, int dest) {
        if (org == dest) {
            return "";
        }
        int neighbor = pred[dest];
        if (neighbor == -1) {
            return null;
        }
        if (dest - neighbor == this.dimesion) {
            return buildPath(pred, org, neighbor) + "B";
        }
        if (neighbor - dest == this.dimesion) {
            return buildPath(pred, org, neighbor) + "A";
        }
        if (neighbor - dest == 1) {
            return buildPath(pred, org, neighbor) + "I";
        }
        return buildPath(pred, org, neighbor) + "D";
    }

    private MPoint getRandomPosition() throws Exception {
        if (leftPosition()) {
            int pos = this.random.nextInt(this.usedPositions.length);
            if (!this.usedPositions[pos]) {
                return new MPoint(pos / this.dimesion, pos % this.dimesion);
            }
            MPoint p;
            if (this.random.nextInt(2) == 0) {
                p = findToRight(pos);
                if (p == null) {
                    return findToLeft(pos);
                }
                return p;
            }
            p = findToLeft(pos);
            if (p == null) {
                return findToRight(pos);
            }
            return p;
        }
        throw new Exception("There isn't available positions");
    }

    private MPoint findToLeft(int pos) {
        for (int k = pos - 1; k >= 0; k--) {
            if (!this.usedPositions[k]) {
                return new MPoint(k / this.dimesion, k % this.dimesion);
            }
        }
        return null;
    }

    private MPoint findToRight(int pos) {
        for (int k = pos + 1; k < this.usedPositions.length; k++) {
            if (!this.usedPositions[k]) {
                return new MPoint(k / this.dimesion, k % this.dimesion);
            }
        }
        return null;
    }

    private Color getRandomColor() {
        return this.colors[this.random.nextInt(this.colors.length)];
    }

    private boolean leftPosition() {
        for (boolean z : this.usedPositions) {
            if (!z) {
                return true;
            }
        }
        return false;
    }

    private void startBoard(int cantidad) throws Exception {
        for (int i = 0; i < cantidad; i++) {
            MPoint p = getRandomPosition();
            this.board[p.getX()][p.getY()] = new Checker(p, getRandomColor());
            this.usedPositions[(this.dimesion * p.getX()) + p.getY()] = true;
        }
    }
}
