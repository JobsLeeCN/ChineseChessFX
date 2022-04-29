package org.jobsl.cgames.cchess.chessmen.impl;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.jobsl.cgames.cchess.base.Constants;
import org.jobsl.cgames.cchess.base.Point;
import org.jobsl.cgames.cchess.chessboard.ChessBoard;
import org.jobsl.cgames.cchess.chessmen.*;

/**
 * 仕/士
 *
 * @author JobsLee
 */
public class ChessShi extends Chessman implements ChessRule {
    public ChessShi(ChessColor color, Point point) {
        super(color, point);
    }

    @Override
    protected void setProperties(ChessColor color) {
        this.code = ChessCode.SHI;
        this.color = color;
        this.name = ChessName.getByCodeAndColor(this.code, color);
        super.setBackground(new Background(new BackgroundImage(new Image(super.getImageFullPath(color, this.name)), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(Constants.CHESSMAN_WIDTH, Constants.CHESSMAN_HEIGHT, false, false, false, false))));
    }

    @Override
    public boolean checkRule(Point current, Point next, ChessBoard board) {
        boolean res = false;
        // 横向绝对值
        int xx = Math.abs(next.getX() - current.getX());
        // 纵向绝对值
        int yy = Math.abs(next.getY() - current.getY());
        // 移动范围限定
        if (xx != 1 && yy != 1) {
            return res;
        }
        res = ChessColor.RED.equals(color) ?
                // 红 固定坐标 (3,0) (3,2) (4,1) (5,0) (5,2)
                (next.getX() == 3 && (next.getY() == 0 || next.getY() == 2))
                        || (next.getX() == 4 && next.getY() == 1)
                        || (next.getX() == 5 && (next.getY() == 0 || next.getY() == 2)) :
                // 黑 固定坐标 (3,9) (3,7) (4,8) (5,9) (5,7)
                (next.getX() == 3 && (next.getY() == 9 || next.getY() == 7))
                        || (next.getX() == 4 && next.getY() == 8)
                        || (next.getX() == 5 && (next.getY() == 9 || next.getY() == 7));
        return res ? super.checkRule(current, next, board) : res;
    }
}
