package org.jobsl.cgames.cchess.chessmen.impl;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.jobsl.cgames.cchess.base.Constants;
import org.jobsl.cgames.cchess.base.Point;
import org.jobsl.cgames.cchess.chessboard.ChessBoard;
import org.jobsl.cgames.cchess.chessmen.*;

/**
 * 車
 *
 * @author JobsLee
 */
public class ChessChe extends Chessman implements ChessRule {
    public ChessChe(ChessColor color, Point point) {
        super(color, point);
    }

    @Override
    protected void setProperties(ChessColor color) {
        this.code = ChessCode.CHE;
        this.color = color;
        this.name = ChessName.getByCodeAndColor(this.code, color);
        super.setBackground(new Background(new BackgroundImage(new Image(super.getImageFullPath(color, this.name)), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(Constants.CHESSMAN_WIDTH, Constants.CHESSMAN_HEIGHT, false, false, false, false))));
    }

    @Override
    public boolean checkRule(Point current, Point next, ChessBoard board) {
        // 每次只能移动X或者Y
        if (next.getX() != current.getX() && next.getY() != current.getY()) {
            return false;
        }
        // 移动路径不能有其他棋子
        if (board.countNotEmptyMeta(current, next) > 0) {
            return false;
        }
        return super.checkRule(current, next, board);
    }
}
