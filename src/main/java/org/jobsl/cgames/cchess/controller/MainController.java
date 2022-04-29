package org.jobsl.cgames.cchess.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.jobsl.cgames.cchess.base.Constants;
import org.jobsl.cgames.cchess.base.Point;
import org.jobsl.cgames.cchess.chessboard.ChessBoard;
import org.jobsl.cgames.cchess.chessmen.ChessColor;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 游戏主控制类
 *
 * @author JobsLee
 */
public class MainController implements LifeCycle {
    @FXML
    private CheckMenuItem menu_single_mode;
    @FXML
    private CheckMenuItem menu_multiple_mode;
    @FXML
    private AnchorPane board_pane;
    @FXML
    private ImageView board_image;
    @FXML
    private Text identity_info;
    @FXML
    private Text total_count;
    @FXML
    private Text total_time;
    @FXML
    private Label net_lable;
    @FXML
    private Text net_status;
    @FXML
    private TextArea more_info;

    private GameMode gameMode;
    private ChessBoard chessBoard;

    @FXML
    public void singleModeAction() {
        menu_multiple_mode.setSelected(false);
        if (Objects.nonNull(chessBoard)) {
            destory();
        }
        init();
        gameMode = GameMode.SINGLE;
    }

    @FXML
    public void multipleModeAction() {
        menu_single_mode.setSelected(false);
        if (Objects.nonNull(chessBoard)) {
            destory();
        }
        gameMode = GameMode.MULTIPLE;
    }

    @FXML
    public void openFileAction() {
    }

    @FXML
    public void saveFileAction() {
    }

    @FXML
    public void exitAction() {
        if (Objects.nonNull(chessBoard)) {
            destory();
        }
        System.exit(0);
    }

    @FXML
    public void netSatusAction() {
    }

    @FXML
    public void netServerSettingAction() {
    }

    @FXML
    public void helpUpdateAction() {

    }

    @FXML
    public void helpAboutAction() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("关于中国象棋FX完美版");
        alert.setHeaderText("本程序由JobsLeeCN开发，基于MIT开源协议。");
        alert.setContentText("欢迎Stars！GitHub: https://github.com/JobsLeeCN/");
        alert.showAndWait();
    }

    @Override
    public void init() {
        chessBoard = new ChessBoard().init(ChessColor.RED);
        fresh();
    }

    @Override
    public void pause() {

    }

    @Override
    public void destory() {
        chessBoard = null;
        board_pane.getChildren().removeIf(child -> !(child instanceof ImageView));
    }

    private void fresh() {
        board_pane.getChildren().removeIf(child -> !(child instanceof ImageView));
        board_pane.getChildren().addAll(Stream.of(chessBoard.getMetas())
                .flatMap(metas -> Stream.of(metas))
                .filter(meta -> Objects.nonNull(meta))
                .map(meta -> {
                    // 渲染处理
                    Point point = meta.getPoint();
                    meta.setLayoutX(5 + point.getX() * Constants.CHESSMAN_WIDTH);
                    meta.setLayoutY(4 + (9 - point.getY()) * Constants.CHESSMAN_HEIGHT);
                    // 事件处理
                    meta.setOnAction(event -> {
                        chessBoard.chooseOrReverse(meta);
                        chessBoard.moveOrCover(meta.getPoint());
                        fresh();
                    });
                    return meta;
                })
                .collect(Collectors.toList()));
    }
}
