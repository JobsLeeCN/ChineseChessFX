package org.jobsl.cgames.cchess.chessmen.impl;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.jobsl.cgames.cchess.base.Constants;
import org.jobsl.cgames.cchess.base.Point;
import org.jobsl.cgames.cchess.chessboard.ChessBoard;
import org.jobsl.cgames.cchess.chessmen.*;

/**
 * 兵/卒
 *
 * @author JobsLee
 */
public class ChessBing extends Chessman implements ChessRule {
    public ChessBing(ChessColor color, Point point) {
        super(color, point);
    }

    @Override
    protected void setProperties(ChessColor color) {
        this.code = ChessCode.BING;
        this.color = color;
        this.name = ChessName.getByCodeAndColor(this.code, color);
        super.setBackground(new Background(new BackgroundImage(new Image(super.getImageFullPath(color, this.name)), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(Constants.CHESSMAN_WIDTH, Constants.CHESSMAN_HEIGHT, false, false, false, false))));
    }

    @Override
    public boolean checkRule(Point current, Point next, ChessBoard board) {
        boolean res = false;
        // 横向
        int xx = next.getX() - current.getX();
        // 纵向
        int yy = next.getY() - current.getY();
        // 绝对值
        int absXx = Math.abs(xx);
        int absYy = Math.abs(yy);
        res = ChessColor.RED.equals(color) ?
                // 红
                // 只能往Y轴正向走
                // 未过河时 只能正向移动1步
                // 过河时 只能横向或者纵向移动1步
                (yy >= 0 && ((current.getY() <= 4 && absYy == 1 && absXx == 0)
                        || (current.getY() > 4 && ((absXx == 1 && absYy == 0) || (absXx == 0 && absYy == 1))))) :
                // 黑
                // 只能往Y轴负向走
                // 未过河时 只能负向移动1步
                // 过河时 只能横向或者纵向移动1步
                (yy <= 0 && ((current.getY() > 4 && absYy == 1 && absXx == 0)
                        || (current.getY() <= 4 && ((absXx == 1 && absYy == 0) || (absXx == 0 && absYy == 1)))));
        return res ? super.checkRule(current, next, board) : res;
    }
}
