package org.jobsl.cgames.cchess.chessmen.impl;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.jobsl.cgames.cchess.base.Constants;
import org.jobsl.cgames.cchess.base.Point;
import org.jobsl.cgames.cchess.chessboard.ChessBoard;
import org.jobsl.cgames.cchess.chessmen.*;

/**
 * 炮
 *
 * @author JobsLee
 */
public class ChessPao extends Chessman implements ChessRule {
    public ChessPao(ChessColor color, Point point) {
        super(color, point);
    }

    @Override
    protected void setProperties(ChessColor color) {
        this.code = ChessCode.PAO;
        this.color = color;
        this.name = ChessName.getByCodeAndColor(this.code, color);
        super.setBackground(new Background(new BackgroundImage(new Image(super.getImageFullPath(color, this.name)), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(Constants.CHESSMAN_WIDTH, Constants.CHESSMAN_HEIGHT, false, false, false, false))));
    }

    @Override
    public boolean checkRule(Point current, Point next, ChessBoard board) {
        // 只能沿着X或Y移动
        if (next.getX() != current.getX() && next.getY() != current.getY()) {
            return false;
        }
        // 移动路径不能有两个及以上其他棋子
        if (board.countNotEmptyMeta(current, next) >= 2) {
            return false;
        }
        // 移动路径无棋子的情况 目标路径不能有棋子
        if (board.countNotEmptyMeta(current, next) == 0 && !board.isMetaEmpty(next)) {
            return false;
        }
        // 移动路径有一个棋子的情况（炮翻山，必须吃子） 目标路径必须有对方的棋子
        if (board.countNotEmptyMeta(current, next) == 1 && board.isMetaEmpty(next)) {
            return false;
        }
        return super.checkRule(current, next, board);
    }
}
