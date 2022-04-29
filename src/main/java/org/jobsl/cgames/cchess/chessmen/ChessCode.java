package org.jobsl.cgames.cchess.chessmen;


import org.jobsl.cgames.cchess.chessmen.impl.*;

/**
 * 棋子编码枚举（按照红方拼音定义）
 *
 * @author jobslee
 */
public enum ChessCode {
    CHE(ChessChe.class),
    MA(ChessMa.class),
    PAO(ChessPao.class),
    BING(ChessBing.class),
    XIANG(ChessXiang.class),
    SHI(ChessShi.class),
    SHUAI(ChessShuai.class),
    EMPTY(EmptyChess.class);

    Class clazz;

    ChessCode(Class clazz) {
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }
}
