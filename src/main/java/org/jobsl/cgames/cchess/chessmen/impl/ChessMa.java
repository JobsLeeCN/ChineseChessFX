package org.jobsl.cgames.cchess.chessmen.impl;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.jobsl.cgames.cchess.base.Constants;
import org.jobsl.cgames.cchess.base.Point;
import org.jobsl.cgames.cchess.chessboard.ChessBoard;
import org.jobsl.cgames.cchess.chessmen.*;

/**
 * 馬
 *
 * @author JobsLee
 */
public class ChessMa extends Chessman implements ChessRule {
    public ChessMa(ChessColor color, Point point) {
        super(color, point);
    }

    @Override
    protected void setProperties(ChessColor color) {
        this.code = ChessCode.MA;
        this.color = color;
        this.name = ChessName.getByCodeAndColor(this.code, color);
        super.setBackground(new Background(new BackgroundImage(new Image(super.getImageFullPath(color, this.name)), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(Constants.CHESSMAN_WIDTH, Constants.CHESSMAN_HEIGHT, false, false, false, false))));
    }

    @Override
    public boolean checkRule(Point currentP, Point nextP, ChessBoard board) {
        boolean res = false;
        // 横向
        int xx = nextP.getX() - currentP.getX();
        // 纵向
        int yy = nextP.getY() - currentP.getY();
        // 绝对值
        int absXx = Math.abs(xx);
        int absYy = Math.abs(yy);
        // 竖着走
        if (absXx == 1 && absYy == 2) {
            // 撇马腿坐标
            if (board.isMetaEmpty(new Point(currentP.getX(), currentP.getY() + (yy / 2)))) {
                res = true;
            }
        }
        // 横着走
        if (absYy == 1 && absXx == 2) {
            // 撇马腿坐标
            if (board.isMetaEmpty(new Point(currentP.getX() + (xx / 2), currentP.getY()))) {
                res = true;
            }
        }
        return res ? super.checkRule(currentP, nextP, board) : res;
    }
}
