package org.jobsl.cgames.cchess.chessmen;

import org.jobsl.cgames.cchess.base.Point;
import org.jobsl.cgames.cchess.chessboard.ChessBoard;

/**
 * 规则检查
 *
 * @author JobsLee
 */
public interface ChessRule {
    /**
     * 规则检查
     *
     * @param current 当前坐标
     * @param next    下个坐标
     * @param board   棋盘对象
     * @return
     */
    boolean checkRule(Point current, Point next, ChessBoard board);
}
