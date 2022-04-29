package org.jobsl.cgames.cchess.chessboard;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import javafx.animation.AnimationTimer;
import org.jobsl.cgames.cchess.base.Point;
import org.jobsl.cgames.cchess.chessmen.ChessCode;
import org.jobsl.cgames.cchess.chessmen.ChessColor;
import org.jobsl.cgames.cchess.chessmen.Chessman;
import org.jobsl.cgames.cchess.chessmen.impl.*;
import org.jobsl.cgames.cchess.controller.GameStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 棋盘对象
 *
 * @author JobsLee
 */
public class ChessBoard implements Action {
    // 单边 2 x 9 x 5 => 完整 9 * 10
    private Chessman[][] metas = new Chessman[9][10];
    // row-ref
    private List<List<Chessman>> rowMetas = Lists.newArrayList();
    // column-ref
    private List<List<Chessman>> columnMetas = Lists.newArrayList();
    // next-ref <uuid,list>
    private Map<String, List<Chessman>> nextMetas = Maps.newHashMap();

    // 上个选择棋子
    private Chessman lastChoose;
    // 当前执方
    private ChessColor currentColor = ChessColor.RED;

    private GameStatus gameStatus = GameStatus.INIT;
    private Integer countRed;
    private Integer countBlack;
    private AnimationTimer timerRed;
    private AnimationTimer timerBlack;

    public ChessBoard init(ChessColor color) {
        // init chessmen
        int midX = 4;
        // red x 0~8 y 0~4
        //
        // (0,3)       (2,3)          (4,3)          (6,3)       (8,3)
        //       (1,2)                                     (7,2)
        //
        // (0,0) (1,0) (2,0) (3,0) -> (4,0) <- (5,0) (6,0) (7,0) (8,0)
        metas[midX][0] = new ChessShuai(ChessColor.RED, new Point(midX, 0));
        metas[midX][3] = new ChessBing(ChessColor.RED, new Point(midX, 3));
        // left
        metas[0][0] = new ChessChe(ChessColor.RED, new Point(0, 0));
        metas[1][0] = new ChessMa(ChessColor.RED, new Point(1, 0));
        metas[2][0] = new ChessXiang(ChessColor.RED, new Point(2, 0));
        metas[3][0] = new ChessShi(ChessColor.RED, new Point(3, 0));
        metas[1][2] = new ChessPao(ChessColor.RED, new Point(1, 2));
        metas[0][3] = new ChessBing(ChessColor.RED, new Point(0, 3));
        metas[2][3] = new ChessBing(ChessColor.RED, new Point(2, 3));
        // right
        metas[(midX - 0) * 2 + 0][0] = new ChessChe(ChessColor.RED, new Point((midX - 0) * 2 + 0, 0));
        metas[(midX - 1) * 2 + 1][0] = new ChessMa(ChessColor.RED, new Point((midX - 1) * 2 + 1, 0));
        metas[(midX - 2) * 2 + 2][0] = new ChessXiang(ChessColor.RED, new Point((midX - 2) * 2 + 2, 0));
        metas[(midX - 3) * 2 + 3][0] = new ChessShi(ChessColor.RED, new Point((midX - 3) * 2 + 3, 0));
        metas[(midX - 1) * 2 + 1][2] = new ChessPao(ChessColor.RED, new Point((midX - 1) * 2 + 1, 2));
        metas[(midX - 0) * 2 + 0][3] = new ChessBing(ChessColor.RED, new Point((midX - 0) * 2 + 0, 3));
        metas[(midX - 2) * 2 + 2][3] = new ChessBing(ChessColor.RED, new Point((midX - 2) * 2 + 2, 3));
        // black x 0~8 y 5~9
        // (0,9) (1,9) (2,9) (3,9) -> (4,9) <- (5,9) (6,9) (7,9) (8,9)
        //
        //       (1,7)                                     (7,7)
        // (0,6)       (2,6)          (4,6)          (6,6)       (8,6)
        //
        metas[midX][9] = new ChessShuai(ChessColor.BLACK, new Point(midX, 9));
        metas[midX][6] = new ChessBing(ChessColor.BLACK, new Point(midX, 6));
        // left
        metas[0][9] = new ChessChe(ChessColor.BLACK, new Point(0, 9));
        metas[1][9] = new ChessMa(ChessColor.BLACK, new Point(1, 9));
        metas[2][9] = new ChessXiang(ChessColor.BLACK, new Point(2, 9));
        metas[3][9] = new ChessShi(ChessColor.BLACK, new Point(3, 9));
        metas[1][7] = new ChessPao(ChessColor.BLACK, new Point(1, 7));
        metas[0][6] = new ChessBing(ChessColor.BLACK, new Point(0, 6));
        metas[2][6] = new ChessBing(ChessColor.BLACK, new Point(2, 6));
        // right
        metas[(midX - 0) * 2 + 0][9] = new ChessChe(ChessColor.BLACK, new Point((midX - 0) * 2 + 0, 9));
        metas[(midX - 1) * 2 + 1][9] = new ChessMa(ChessColor.BLACK, new Point((midX - 1) * 2 + 1, 9));
        metas[(midX - 2) * 2 + 2][9] = new ChessXiang(ChessColor.BLACK, new Point((midX - 2) * 2 + 2, 9));
        metas[(midX - 3) * 2 + 3][9] = new ChessShi(ChessColor.BLACK, new Point((midX - 3) * 2 + 3, 9));
        metas[(midX - 1) * 2 + 1][7] = new ChessPao(ChessColor.BLACK, new Point((midX - 1) * 2 + 1, 7));
        metas[(midX - 0) * 2 + 0][6] = new ChessBing(ChessColor.BLACK, new Point((midX - 0) * 2 + 0, 6));
        metas[(midX - 2) * 2 + 2][6] = new ChessBing(ChessColor.BLACK, new Point((midX - 2) * 2 + 2, 6));
        // init empty
        for (int y = 0; y <= 9; y++) {
            for (int x = 0; x <= 8; x++) {
                if (Objects.isNull(metas[x][y])) {
                    metas[x][y] = new EmptyChess(ChessColor.EMPTY, new Point(x, y));
                }
            }
        }

        // init row ref
        for (int i = 0; i <= 9; i++) {
            rowMetas.add(new ArrayList<Chessman>());
        }
        for (int i = 0; i <= 9; i++) {
            // row ref
            List<Chessman> row = rowMetas.get(i);
            for (int j = 0; j <= 8; j++) {
                row.add(metas[j][i]);
            }
        }
        // init columns ref
        for (int i = 0; i <= 8; i++) {
            columnMetas.add(new ArrayList<Chessman>());
        }
        for (int i = 0; i <= 8; i++) {
            // columns ref
            List<Chessman> columns = columnMetas.get(i);
            for (int j = 0; j <= 9; j++) {
                columns.add(metas[i][j]);
            }
        }
        return this;
    }

    public Chessman[][] getMetas() {
        return metas;
    }

    public boolean isMetaEmpty(Point point) {
        Chessman meta = metas[point.getX()][point.getY()];
        return Objects.isNull(meta) || meta instanceof EmptyChess;
    }

    /**
     * 统计横竖向不为空的元位置个数
     *
     * @param current
     * @param next
     * @return
     */
    public int countNotEmptyMeta(Point current, Point next) {
        int count = 0;
        int px = next.getX() - current.getX();
        int py = next.getY() - current.getY();
        // 横向移动
        if (Math.abs(px) > 0) {
            if (px > 0) {
                for (int i = 1; i < Math.abs(px); i++) {
                    if (!isMetaEmpty(new Point(current.getX() + i, current.getY()))) {
                        count++;
                    }
                }
            }
            if (px < 0) {
                for (int i = 1; i < Math.abs(px); i++) {
                    if (!isMetaEmpty(new Point(current.getX() - i, current.getY()))) {
                        count++;
                    }
                }
            }
        }
        // 纵向移动
        if (Math.abs(py) > 0) {
            if (py > 0) {
                for (int i = 1; i < Math.abs(py); i++) {
                    if (!isMetaEmpty(new Point(current.getX(), current.getY() + i))) {
                        count++;
                    }
                }
            }
            if (py < 0) {
                for (int i = 1; i < Math.abs(py); i++) {
                    Point tryPoint = new Point(current.getX(), current.getY() - i);
                    if (!isMetaEmpty(tryPoint)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    @Override
    public void chooseOrReverse(Chessman chessman) {
        if (currentColor.equals(chessman.getColor())) {
            if (Objects.nonNull(lastChoose)) {
                lastChoose.reverseChoose();
                lastChoose.repeatDraw();
            }
            chessman.reverseChoose();
            chessman.repeatDraw();
            lastChoose = chessman;
        }
    }

    @Override
    public void moveOrCover(Point next) {
        if (Objects.nonNull(lastChoose) && currentColor.equals(lastChoose.getColor())) {
            // 校验规则
            if (!lastChoose.checkRule(lastChoose.getPoint(), next, this)) {
                return;
            }
            int lastX = lastChoose.getPoint().getX();
            int lastY = lastChoose.getPoint().getY();
            Chessman temp = metas[next.getX()][next.getY()];
            metas[next.getX()][next.getY()] = lastChoose.setPoint(next);
            if (!isMetaEmpty(next)) {
                // 吃子操作
                try {
                    metas[lastX][lastY] = (Chessman) ChessCode.EMPTY.getClazz().getConstructor(ChessColor.class, Point.class).newInstance(ChessColor.EMPTY, new Point(lastX, lastY));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // 移动操作
                metas[lastX][lastY] = temp.setPoint(new Point(lastX, lastY));
            }
            chooseOrReverse(lastChoose);
            changeCurrentColor();
        }
    }

    /**
     * 更换执方颜色
     */
    private void changeCurrentColor() {
        currentColor = (ChessColor.RED.equals(currentColor) ? ChessColor.BLACK : ChessColor.RED);
    }
}
