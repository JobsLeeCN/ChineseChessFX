package org.jobsl.cgames.cchess.chessmen.impl;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.jobsl.cgames.cchess.base.Constants;
import org.jobsl.cgames.cchess.base.Point;
import org.jobsl.cgames.cchess.chessboard.ChessBoard;
import org.jobsl.cgames.cchess.chessmen.*;

/**
 * 帥/將
 *
 * @author JobsLee
 */
public class ChessShuai extends Chessman implements ChessRule {
    public ChessShuai(ChessColor color, Point point) {
        super(color, point);
    }

    @Override
    protected void setProperties(ChessColor color) {
        this.code = ChessCode.SHUAI;
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
        // 一次只能沿着X或Y移动一步
        if (xx > 1 || yy > 1 || (xx == 1 && yy == 1)) {
            return res;
        }
        res = ChessColor.RED.equals(color) ?
                // 红 坐标范围 x 3~5 y 0~2
                (next.getX() >= 3 && next.getX() <= 5 && next.getY() >= 0 && next.getY() <= 2) :
                // 黑 坐标范围 x 3~5 y 7~9
                (next.getX() >= 3 && next.getX() <= 5 && next.getY() >= 7 && next.getY() <= 9);
        return res ? super.checkRule(current, next, board) : res;
    }
}
