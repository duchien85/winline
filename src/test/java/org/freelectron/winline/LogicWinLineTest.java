package org.freelectron.winline;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

/**
 * Created by leobel on 7/13/16.
 */
public class LogicWinLineTest {

    @Test
    public void getBoard() throws Exception {
        Checker[][] board = new Checker[9][9];
        board[0][0] = new Checker(new MPoint(0,0), LogicWinLine.Color.BLUE);
        board[1][1] = new Checker(new MPoint(1,1), LogicWinLine.Color.BLACK);
        board[2][2] = new Checker(new MPoint(2,2), LogicWinLine.Color.YELLOW);
        board[3][3] = new Checker(new MPoint(3,3), LogicWinLine.Color.RED);
        board[4][4] = new Checker(new MPoint(4,4), LogicWinLine.Color.WHITE);
        board[5][5] = new Checker(new MPoint(5,5), LogicWinLine.Color.GREEN);
        Checker[]next = new Checker[]{new Checker(LogicWinLine.Color.BLUE), new Checker(LogicWinLine.Color.BLUE), new Checker(LogicWinLine.Color.BLUE)};
        LogicWinLine game = new LogicWinLine(board, next, 0);

        assertThat(game.getBoard(), notNullValue());
        for (int d = 0; d < board.length; d++){
            assertArrayEquals(board[d], game.getBoard()[d]);
        }
    }

    @Test
    public void moveChecker() throws Exception {
        Checker[][] board = new Checker[9][9];
        board[0][0] = new Checker(new MPoint(0,0), LogicWinLine.Color.BLUE);
        board[1][1] = new Checker(new MPoint(1,1), LogicWinLine.Color.BLACK);
        board[2][2] = new Checker(new MPoint(2,2), LogicWinLine.Color.YELLOW);
        board[3][3] = new Checker(new MPoint(3,3), LogicWinLine.Color.RED);
        board[4][4] = new Checker(new MPoint(4,4), LogicWinLine.Color.WHITE);
        board[5][5] = new Checker(new MPoint(5,5), LogicWinLine.Color.GREEN);
        Checker[]next = new Checker[]{new Checker(LogicWinLine.Color.BLUE), new Checker(LogicWinLine.Color.BLUE), new Checker(LogicWinLine.Color.BLUE)};
        LogicWinLine game = new LogicWinLine(board, next, 0);

        Checker f = board[0][0];
        MPoint dest = new MPoint(3, 4);
        game.moveChecker(f.getPosition(), dest, f);

        assertThat(game.getCheckerCount(), is(6));
        assertThat(game.getBoard()[0][0], nullValue());
        assertThat(game.getBoard()[3][4], is(f));
    }

    @Test
    public void getNext() throws Exception {
        Checker[][] board = new Checker[9][9];
        board[0][0] = new Checker(new MPoint(0,0), LogicWinLine.Color.BLUE);
        board[1][1] = new Checker(new MPoint(1,1), LogicWinLine.Color.BLACK);
        board[2][2] = new Checker(new MPoint(2,2), LogicWinLine.Color.YELLOW);
        board[3][3] = new Checker(new MPoint(3,3), LogicWinLine.Color.RED);
        board[4][4] = new Checker(new MPoint(4,4), LogicWinLine.Color.WHITE);
        board[5][5] = new Checker(new MPoint(5,5), LogicWinLine.Color.GREEN);
        Checker[]next = new Checker[]{new Checker(LogicWinLine.Color.BLUE), new Checker(LogicWinLine.Color.BLUE), new Checker(LogicWinLine.Color.BLUE)};
        LogicWinLine game = new LogicWinLine(board, next, 0);

        assertArrayEquals(next, game.getdNext());
    }

    @Test
    public void buildNext() throws Exception {
        LogicWinLine game = new LogicWinLine(9);

        assertThat(game.getdNext().length, is(3));
        assertThat(game.getdNext()[0], notNullValue());
        assertThat(game.getdNext()[1], notNullValue());
        assertThat(game.getdNext()[2], notNullValue());
    }

    @Test
    public void getPath() throws Exception {
        Checker[][] board = new Checker[9][9];
        board[0][0] = new Checker(new MPoint(0,0), LogicWinLine.Color.BLUE);
        board[0][2] = new Checker(new MPoint(0,2), LogicWinLine.Color.WHITE);
        board[1][1] = new Checker(new MPoint(1,1), LogicWinLine.Color.BLACK);
        board[2][2] = new Checker(new MPoint(2,2), LogicWinLine.Color.YELLOW);
        board[3][3] = new Checker(new MPoint(3,3), LogicWinLine.Color.RED);
        board[4][4] = new Checker(new MPoint(4,4), LogicWinLine.Color.WHITE);
        board[5][5] = new Checker(new MPoint(5,5), LogicWinLine.Color.GREEN);
        board[6][6] = new Checker(new MPoint(6,6), LogicWinLine.Color.GREEN);
        board[7][7] = new Checker(new MPoint(7,7), LogicWinLine.Color.GREEN);
        board[8][8] = new Checker(new MPoint(8,8), LogicWinLine.Color.GREEN);
        Checker[]next = new Checker[]{new Checker(LogicWinLine.Color.BLUE), new Checker(LogicWinLine.Color.BLUE), new Checker(LogicWinLine.Color.BLUE)};
        LogicWinLine game = new LogicWinLine(board, next, 0);

        String path = game.getPath(board[1][1].getPosition(), new MPoint(1,7));

        assertThat(path, notNullValue());
        assertThat(path.isEmpty(), is(false));
        assertThat(path, is("DDDDDD"));
    }

    @Test
    public void getPathUpStairs() throws Exception {
        Checker[][] board = new Checker[9][9];
        board[0][0] = new Checker(new MPoint(0,0), LogicWinLine.Color.BLUE);
        board[0][2] = new Checker(new MPoint(0,2), LogicWinLine.Color.WHITE);
        board[1][1] = new Checker(new MPoint(1,1), LogicWinLine.Color.BLACK);
        board[2][2] = new Checker(new MPoint(2,2), LogicWinLine.Color.YELLOW);
        board[3][3] = new Checker(new MPoint(3,3), LogicWinLine.Color.RED);
        board[4][4] = new Checker(new MPoint(4,4), LogicWinLine.Color.WHITE);
        board[5][5] = new Checker(new MPoint(5,5), LogicWinLine.Color.GREEN);
        board[6][6] = new Checker(new MPoint(6,6), LogicWinLine.Color.GREEN);
        board[7][7] = new Checker(new MPoint(7,7), LogicWinLine.Color.GREEN);
        board[8][8] = new Checker(new MPoint(8,8), LogicWinLine.Color.GREEN);
        Checker[]next = new Checker[]{new Checker(LogicWinLine.Color.BLUE), new Checker(LogicWinLine.Color.BLUE), new Checker(LogicWinLine.Color.BLUE)};
        LogicWinLine game = new LogicWinLine(board, next, 0);

        String path = game.getPath(board[6][6].getPosition(), new MPoint(1,3));
        assertThat(path, notNullValue());
        assertThat(path.isEmpty(), is(false));
        assertThat(path, is("AAIAIAIA"));
    }

    @Test
    public void getPathSquare() throws Exception {
        Checker[][] board = new Checker[9][9];
        board[0][0] = new Checker(new MPoint(0,0), LogicWinLine.Color.BLUE);
        board[0][2] = new Checker(new MPoint(0,2), LogicWinLine.Color.WHITE);
        board[1][1] = new Checker(new MPoint(1,1), LogicWinLine.Color.BLACK);
        board[2][2] = new Checker(new MPoint(2,2), LogicWinLine.Color.YELLOW);
        board[3][3] = new Checker(new MPoint(3,3), LogicWinLine.Color.RED);
        board[4][4] = new Checker(new MPoint(4,4), LogicWinLine.Color.WHITE);
        board[5][5] = new Checker(new MPoint(5,5), LogicWinLine.Color.GREEN);
        board[6][6] = new Checker(new MPoint(6,6), LogicWinLine.Color.GREEN);
        board[7][7] = new Checker(new MPoint(7,7), LogicWinLine.Color.GREEN);
        board[8][8] = new Checker(new MPoint(8,8), LogicWinLine.Color.GREEN);
        Checker[]next = new Checker[]{new Checker(LogicWinLine.Color.BLUE), new Checker(LogicWinLine.Color.BLUE), new Checker(LogicWinLine.Color.BLUE)};
        LogicWinLine game = new LogicWinLine(board, next, 0);

        String path = game.getPath(board[1][1].getPosition(), new MPoint(5,6));
        assertThat(path, notNullValue());
        assertThat(path.isEmpty(), is(false));
        assertThat(path, is("DDDDDBBBB"));
    }


    @Test
    public void getPathNotFound() throws Exception {
        Checker[][] board = new Checker[9][9];
        board[0][0] = new Checker(new MPoint(0,0), LogicWinLine.Color.BLUE);
        board[0][2] = new Checker(new MPoint(0,2), LogicWinLine.Color.WHITE);
        board[1][1] = new Checker(new MPoint(1,1), LogicWinLine.Color.BLACK);
        board[2][2] = new Checker(new MPoint(2,2), LogicWinLine.Color.YELLOW);
        board[3][3] = new Checker(new MPoint(3,3), LogicWinLine.Color.RED);
        board[4][4] = new Checker(new MPoint(4,4), LogicWinLine.Color.WHITE);
        board[5][5] = new Checker(new MPoint(5,5), LogicWinLine.Color.GREEN);
        board[6][6] = new Checker(new MPoint(6,6), LogicWinLine.Color.GREEN);
        board[7][7] = new Checker(new MPoint(7,7), LogicWinLine.Color.GREEN);
        board[8][8] = new Checker(new MPoint(8,8), LogicWinLine.Color.GREEN);
        Checker[]next = new Checker[]{new Checker(LogicWinLine.Color.BLUE), new Checker(LogicWinLine.Color.BLUE), new Checker(LogicWinLine.Color.BLUE)};
        LogicWinLine game = new LogicWinLine(board, next, 0);

        String noPath = game.getPath(board[0][0].getPosition(), new MPoint(5,6));
        assertThat(noPath, nullValue());
    }

    @Test
    public void addCheckers() throws Exception {
        Checker[][] board = new Checker[9][9];
        board[0][0] = new Checker(new MPoint(0,0), LogicWinLine.Color.BLUE);
        board[1][1] = new Checker(new MPoint(1,1), LogicWinLine.Color.BLACK);
        board[2][2] = new Checker(new MPoint(2,2), LogicWinLine.Color.YELLOW);
        board[3][3] = new Checker(new MPoint(3,3), LogicWinLine.Color.RED);
        board[4][4] = new Checker(new MPoint(4,4), LogicWinLine.Color.WHITE);
        board[5][5] = new Checker(new MPoint(5,5), LogicWinLine.Color.GREEN);
        board[6][6] = new Checker(new MPoint(6,6), LogicWinLine.Color.GREEN);
        board[7][7] = new Checker(new MPoint(7,7), LogicWinLine.Color.GREEN);
        board[8][8] = new Checker(new MPoint(8,8), LogicWinLine.Color.GREEN);
        Checker[]next = new Checker[]{new Checker(LogicWinLine.Color.BLUE), new Checker(LogicWinLine.Color.BLUE), new Checker(LogicWinLine.Color.BLUE)};
        LogicWinLine game = new LogicWinLine(board, next, 0);
        int count = game.getCheckerCount();
        List<Integer> pos = game.addCheckers();
        for (Integer p: pos) {
            int x = p / 9;
            int y = p % 9;
            assertThat(game.getBoard()[x][y], notNullValue());
        }

        assertThat(game.checkerCount, is(count + pos.size()));
    }


    @Test
    public void canDelete_OK(){
        Checker[][] board = new Checker[9][9];
        board[0][0] = new Checker(new MPoint(0,0), LogicWinLine.Color.BLUE);
        board[1][1] = new Checker(new MPoint(1,1), LogicWinLine.Color.GREEN);
        board[2][2] = new Checker(new MPoint(2,2), LogicWinLine.Color.YELLOW);
        board[3][3] = new Checker(new MPoint(3,3), LogicWinLine.Color.GREEN);
        board[4][4] = new Checker(new MPoint(4,4), LogicWinLine.Color.GREEN);
        board[5][5] = new Checker(new MPoint(5,5), LogicWinLine.Color.GREEN);
        board[6][6] = new Checker(new MPoint(6,6), LogicWinLine.Color.GREEN);
        board[7][7] = new Checker(new MPoint(7,7), LogicWinLine.Color.GREEN);
        board[8][8] = new Checker(new MPoint(8,8), LogicWinLine.Color.GREEN);
        board[4][2] = new Checker(new MPoint(4,2), LogicWinLine.Color.GREEN);
        board[6][2] = new Checker(new MPoint(6,2), LogicWinLine.Color.GREEN);
        board[4][3] = new Checker(new MPoint(4,3), LogicWinLine.Color.GREEN);
        board[5][3] = new Checker(new MPoint(5,3), LogicWinLine.Color.GREEN);
        board[3][4] = new Checker(new MPoint(3,4), LogicWinLine.Color.GREEN);
        board[6][4] = new Checker(new MPoint(6,4), LogicWinLine.Color.GREEN);
        board[3][5] = new Checker(new MPoint(3,5), LogicWinLine.Color.GREEN);
        board[4][5] = new Checker(new MPoint(4,5), LogicWinLine.Color.GREEN);
        board[4][6] = new Checker(new MPoint(4,6), LogicWinLine.Color.GREEN);
        board[4][7] = new Checker(new MPoint(4,7), LogicWinLine.Color.GREEN);
        Checker[]next = new Checker[]{new Checker(LogicWinLine.Color.BLUE), new Checker(LogicWinLine.Color.BLUE), new Checker(LogicWinLine.Color.BLUE)};
        LogicWinLine game = new LogicWinLine(board, next, 0);

        MPoint[] pairs = new MPoint[8];
        int[]score = new int[4];

        boolean canDelete = game.canDelete(game.getBoard()[4][4].getPosition(), pairs, score);

        assertThat(canDelete, is(true));
        assertEquals(pairs[0], new MPoint(4,2));
        assertThat(pairs[1], is(new MPoint(4,7)));

        assertThat(pairs[2], is(new MPoint(3,4)));
        assertThat(pairs[3], is(new MPoint(4,4)));

        assertThat(pairs[4], is(new MPoint(3,3)));
        assertThat(pairs[5], is(new MPoint(8,8)));

        assertThat(pairs[6], is(new MPoint(6,2)));
        assertThat(pairs[7], is(new MPoint(3,5)));
    }


    @Test
    public void canDelete_Fail() throws Exception {
        Checker[][] board = new Checker[9][9];
        board[0][0] = new Checker(new MPoint(0,0), LogicWinLine.Color.BLUE);
        board[1][1] = new Checker(new MPoint(1,1), LogicWinLine.Color.GREEN);
        board[2][2] = new Checker(new MPoint(2,2), LogicWinLine.Color.YELLOW);
        board[3][3] = new Checker(new MPoint(3,3), LogicWinLine.Color.GREEN);
        board[4][4] = new Checker(new MPoint(4,4), LogicWinLine.Color.WHITE);
        board[5][5] = new Checker(new MPoint(5,5), LogicWinLine.Color.GREEN);
        board[6][6] = new Checker(new MPoint(6,6), LogicWinLine.Color.GREEN);
        board[7][7] = new Checker(new MPoint(7,7), LogicWinLine.Color.GREEN);
        board[8][8] = new Checker(new MPoint(8,8), LogicWinLine.Color.GREEN);
        Checker[]next = new Checker[]{new Checker(LogicWinLine.Color.BLUE), new Checker(LogicWinLine.Color.BLUE), new Checker(LogicWinLine.Color.BLUE)};
        LogicWinLine game = new LogicWinLine(board, next, 0);

        MPoint[] pairs = new MPoint[8];
        int[]score = new int[4];

        boolean result = game.canDelete(game.getBoard()[0][0].getPosition(), pairs, score);

        assertThat(result, is(false));
        int[] expectedScore = new int[]{1,1,1,1};
        MPoint[] expectedPairs = new MPoint[]{game.getBoard()[0][0].getPosition(),game.getBoard()[0][0].getPosition(),game.getBoard()[0][0].getPosition(),game.getBoard()[0][0].getPosition(),game.getBoard()[0][0].getPosition(),game.getBoard()[0][0].getPosition(),game.getBoard()[0][0].getPosition(),game.getBoard()[0][0].getPosition()};
        assertArrayEquals(expectedScore, score);
        assertArrayEquals(expectedPairs, pairs);


    }

    @Test
    public void addScore() throws Exception {
        Checker[][] board = new Checker[9][9];
        board[0][0] = new Checker(new MPoint(0,0), LogicWinLine.Color.BLUE);
        board[1][1] = new Checker(new MPoint(1,1), LogicWinLine.Color.GREEN);
        board[2][2] = new Checker(new MPoint(2,2), LogicWinLine.Color.YELLOW);
        board[3][3] = new Checker(new MPoint(3,3), LogicWinLine.Color.GREEN);
        board[4][4] = new Checker(new MPoint(4,4), LogicWinLine.Color.WHITE);
        board[5][5] = new Checker(new MPoint(5,5), LogicWinLine.Color.GREEN);
        board[6][6] = new Checker(new MPoint(6,6), LogicWinLine.Color.GREEN);
        board[7][7] = new Checker(new MPoint(7,7), LogicWinLine.Color.GREEN);
        board[8][8] = new Checker(new MPoint(8,8), LogicWinLine.Color.GREEN);
        Checker[]next = new Checker[]{new Checker(LogicWinLine.Color.BLUE), new Checker(LogicWinLine.Color.BLUE), new Checker(LogicWinLine.Color.BLUE)};
        LogicWinLine game = new LogicWinLine(board, next, 0);

        game.addScore(123);

        assertThat(game.getScore(), is(123));

        game.addScore(15);

        assertThat(game.getScore(), is(138));
    }

    @Test
    public void getScore() throws Exception {
        Checker[][] board = new Checker[9][9];
        board[0][0] = new Checker(new MPoint(0,0), LogicWinLine.Color.BLUE);
        board[1][1] = new Checker(new MPoint(1,1), LogicWinLine.Color.GREEN);
        board[2][2] = new Checker(new MPoint(2,2), LogicWinLine.Color.YELLOW);
        board[3][3] = new Checker(new MPoint(3,3), LogicWinLine.Color.GREEN);
        board[5][4] = new Checker(new MPoint(5,4), LogicWinLine.Color.GREEN);
        board[5][5] = new Checker(new MPoint(5,5), LogicWinLine.Color.GREEN);
        board[6][6] = new Checker(new MPoint(6,6), LogicWinLine.Color.GREEN);
        board[7][7] = new Checker(new MPoint(7,7), LogicWinLine.Color.GREEN);
        board[8][8] = new Checker(new MPoint(8,8), LogicWinLine.Color.GREEN);
        board[4][2] = new Checker(new MPoint(4,2), LogicWinLine.Color.GREEN);
        board[6][2] = new Checker(new MPoint(6,2), LogicWinLine.Color.GREEN);
        board[4][3] = new Checker(new MPoint(4,3), LogicWinLine.Color.GREEN);
        board[5][3] = new Checker(new MPoint(5,3), LogicWinLine.Color.GREEN);
        board[3][4] = new Checker(new MPoint(3,4), LogicWinLine.Color.GREEN);
        board[6][4] = new Checker(new MPoint(6,4), LogicWinLine.Color.GREEN);
        board[3][5] = new Checker(new MPoint(3,5), LogicWinLine.Color.GREEN);
        board[4][5] = new Checker(new MPoint(4,5), LogicWinLine.Color.GREEN);
        board[4][6] = new Checker(new MPoint(4,6), LogicWinLine.Color.GREEN);
        board[4][7] = new Checker(new MPoint(4,7), LogicWinLine.Color.GREEN);
        Checker[]next = new Checker[]{new Checker(LogicWinLine.Color.BLUE), new Checker(LogicWinLine.Color.BLUE), new Checker(LogicWinLine.Color.BLUE)};
        LogicWinLine game = new LogicWinLine(board, next, 0);

        Checker f = game.getBoard()[5][4];
        game.moveChecker(f.getPosition(), new MPoint(4,4), f);

        MPoint[] pairs = new MPoint[8];
        int[] score = new int[4];

        boolean result = game.canDelete(game.getBoard()[4][4].getPosition(), pairs, score);
        int finalScore = 0;
        for (int i = 0; i < score.length; i++){
            if(score[i] >= 5){
                finalScore += finalScore ==0 ? score[i] : score[i] - 1;
            }

        }
        game.addScore(finalScore);

        assertThat(result, is(true));
        assertThat(finalScore, is(11));
        assertThat(game.getScore(), is(finalScore));
    }
}