package org.jobsl.cgames.cchess.chessmen;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.jobsl.cgames.cchess.base.Constants;
import org.jobsl.cgames.cchess.base.Point;
import org.jobsl.cgames.cchess.chessboard.ChessBoard;
import org.jobsl.cgames.cchess.chessmen.impl.EmptyChess;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

/**
 * 棋子抽象
 *
 * @author JobsLee
 */
public abstract class Chessman extends Button implements ChessRule {
    protected String uuid;
    protected ChessCode code;
    protected ChessColor color;
    protected ChessName name;
    private Point point;
    private boolean isChoose;

    public Chessman(ChessColor color, Point point) {
        this.uuid = UUID.randomUUID().toString();
        this.point = point;
        setProperties(color);
        super.setPrefSize(Constants.CHESSMAN_WIDTH, Constants.CHESSMAN_HEIGHT);
        super.setMinSize(Constants.CHESSMAN_WIDTH, Constants.CHESSMAN_HEIGHT);
        super.setMaxSize(Constants.CHESSMAN_WIDTH, Constants.CHESSMAN_HEIGHT);
    }

    protected abstract void setProperties(ChessColor color);

    protected String getImageFullPath(ChessColor color, ChessName name) {
        return (this instanceof EmptyChess) ? Constants.CHESSMAN_BLANK_IMAGE : (Constants.RESOURCE_CHESS_PATH + String.format(Constants.CHESSMAN_IMAGE, ChessColor.RED.equals(color) ? Constants.CHESSMAN_IMAGE_PREFIX_RED : Constants.CHESSMAN_IMAGE_PREFIX_BALCK, name.getPinyin(), Constants.CHESSMAN_IMAGE_SUFFIX_DEFAULT));
    }

    @Override
    public boolean checkRule(Point current, Point next, ChessBoard board) {
        // 棋盘边界判断
        return (next.getX() >= 0 && next.getX() < 9 && next.getY() >= 0 && next.getY() < 10);
    }

    /**
     * 图层重新绘制
     */
    public void repeatDraw() {
        try {
            BufferedImage bufferedImage = ImageIO.read(new URL(getImageFullPath(color, this.name)));
            // 创建Graphics2D对象，用在底图对象上绘图
            Graphics2D g2d = bufferedImage.createGraphics();
            // 在图形和图像中实现混合和透明效果
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1F));
            // 绘制
            g2d.drawImage(ImageIO.read(new URL(isChoose ? Constants.CHESSMAN_CHOOSE_IMAGE : Constants.CHESSMAN_BLANK_IMAGE)), 0, 0, Constants.CHESSMAN_WIDTH, Constants.CHESSMAN_HEIGHT, null);
            // 释放图形上下文使用的系统资源
            g2d.dispose();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "gif", outputStream);
            super.setBackground(new Background(new BackgroundImage(new Image(new ByteArrayInputStream(outputStream.toByteArray())), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(Constants.CHESSMAN_WIDTH, Constants.CHESSMAN_HEIGHT, false, false, false, false))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUuid() {
        return uuid;
    }

    public ChessCode getCode() {
        return code;
    }

    public ChessColor getColor() {
        return color;
    }

    public ChessName getName() {
        return name;
    }

    public Point getPoint() {
        return point;
    }

    public Chessman setPoint(Point point) {
        this.point = point;
        return this;
    }

    public void reverseChoose() {
        isChoose = !isChoose;
    }
}
