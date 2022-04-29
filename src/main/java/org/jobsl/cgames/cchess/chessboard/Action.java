package org.jobsl.cgames.cchess.chessboard;

import org.jobsl.cgames.cchess.base.Point;
import org.jobsl.cgames.cchess.chessmen.Chessman;

/**
 * 动作行为
 *
 * @author jobslee
 */
public interface Action {
    /**
     * 选择/反选
     *
     * @param chessman
     */
    void chooseOrReverse(Chessman chessman);

    /**
     * 移动/覆盖
     *
     * @param next
     */
    void moveOrCover(Point next);
}
