package org.jobsl.cgames.cchess.chessmen;

import java.util.Objects;

/**
 * 棋子名称枚举
 *
 * @author JobsLee
 */
public enum ChessName {
    // 红
    CHE(ChessCode.CHE, ChessColor.RED, "車", "CHE"),
    MA(ChessCode.MA, ChessColor.RED, "馬", "MA"),
    PAO(ChessCode.PAO, ChessColor.RED, "炮", "PAO"),
    SHI(ChessCode.SHI, ChessColor.RED, "仕", "SHI"),
    XIANG(ChessCode.XIANG, ChessColor.RED, "相", "XIANG"),
    BING(ChessCode.BING, ChessColor.RED, "兵", "BING"),
    SHUAI(ChessCode.SHUAI, ChessColor.RED, "帥", "SHUAI"),
    // 黑
    CHE_B(ChessCode.CHE, ChessColor.BLACK, "車", "CHE"),
    MA_B(ChessCode.MA, ChessColor.BLACK, "馬", "MA"),
    PAO_B(ChessCode.PAO, ChessColor.BLACK, "炮", "PAO"),
    SHI_B(ChessCode.SHI, ChessColor.BLACK, "士", "SHI"),
    XIANG_B(ChessCode.XIANG, ChessColor.BLACK, "象", "XIANG"),
    ZU(ChessCode.BING, ChessColor.BLACK, "卒", "ZU"),
    JIANG(ChessCode.SHUAI, ChessColor.BLACK, "將", "JIANG");

    ChessCode code;
    ChessColor color;
    String chinese;
    String pinyin;

    ChessName(ChessCode code, ChessColor color, String chinese, String pinyin) {
        this.code = code;
        this.color = color;
        this.chinese = chinese;
        this.pinyin = pinyin;
    }

    public ChessCode getCode() {
        return code;
    }

    public ChessColor getColor() {
        return color;
    }

    public String getChinese() {
        return chinese;
    }

    public String getPinyin() {
        return pinyin;
    }

    public static ChessName getByCodeAndColor(ChessCode code, ChessColor color) {
        for (ChessName value : ChessName.values()) {
            if (Objects.equals(code, value.code) && Objects.equals(color, value.color)) {
                return value;
            }
        }
        return null;
    }
}
