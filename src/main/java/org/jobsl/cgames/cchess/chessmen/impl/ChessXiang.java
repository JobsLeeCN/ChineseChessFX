package org.jobsl.cgames.cchess.chessmen.impl;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.jobsl.cgames.cchess.base.Constants;
import org.jobsl.cgames.cchess.base.Point;
import org.jobsl.cgames.cchess.chessboard.ChessBoard;
import org.jobsl.cgames.cchess.chessmen.*;

/**
 * 相/象
 *
 * @author JobsLee
 */
public class ChessXiang extends Chessman implements ChessRule {
    public ChessXiang(ChessColor color, Point point) {
        super(color, point);
    }

    @Override
    protected void setProperties(ChessColor color) {
        this.code = ChessCode.XIANG;
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
        // 横向绝对值
        int absXX = Math.abs(xx);
        // 纵向绝对值
        int absYY = Math.abs(yy);
        // 移动范围限定
        if (absXX != 2 && absYY != 2) {
            return res;
        }
        // 撇象眼坐标
        if (!board.isMetaEmpty(new Point(current.getX() + (xx / 2), current.getY() + (yy / 2)))) {
            return res;
        }
        res = ChessColor.RED.equals(color) ?
                // 红 坐标限制 (0,2) (2,0) (2,4) (4,2) (6,0) (6,4) (8,2)
                ((next.getX() == 0 && next.getY() == 2)
                        || (next.getX() == 2 && (next.getY() == 0 || next.getY() == 4))
                        || (next.getX() == 4 && next.getY() == 2)
                        || (next.getX() == 6 && (next.getY() == 0 || next.getY() == 4))
                        || (next.getX() == 8 && next.getY() == 2)) :
                // 黑 坐标限制 (0,7) (2,9) (2,5) (4,7) (6,9) (6,5) (8,7)
                ((next.getX() == 0 && next.getY() == 7)
                        || (next.getX() == 2 && (next.getY() == 9 || next.getY() == 5))
                        || (next.getX() == 4 && next.getY() == 7)
                        || (next.getX() == 6 && (next.getY() == 9 || next.getY() == 5))
                        || (next.getX() == 8 && next.getY() == 7));
        return res ? super.checkRule(current, next, board) : res;
    }
}
