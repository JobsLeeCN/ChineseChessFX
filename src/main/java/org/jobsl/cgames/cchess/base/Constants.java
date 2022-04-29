package org.jobsl.cgames.cchess.base;

/**
 * 常量池
 *
 * @author JobsLee
 */
public class Constants {
    public static final String RESOURCE_PATH = Constants.class.getResource("/").toString();// local
    //    public static final String RESOURCE_PATH = "/";// jar
    public static final String RESOURCE_BOARD_PATH = RESOURCE_PATH + "/image";
    public static final String RESOURCE_CHESS_PATH = RESOURCE_BOARD_PATH + "/chess";

    public static final int BOARD_BACKGROUND_WIDTH = 521;
    public static final int BOARD_BACKGROUND_HEIGHT = 577;
    public static final String BOARD_BACKGROUND_IMAGE = RESOURCE_BOARD_PATH + "/BOARD.JPG";

    public static final int CHESSMAN_WIDTH = 57;
    public static final int CHESSMAN_HEIGHT = 57;
    public static final String CHESSMAN_BLANK_IMAGE = RESOURCE_BOARD_PATH + "/BLANK.GIF";
    public static final String CHESSMAN_CHOOSE_IMAGE = RESOURCE_BOARD_PATH + "/CHOOSE.GIF";
    public static final String CHESSMAN_IMAGE_PREFIX_RED = "R_";
    public static final String CHESSMAN_IMAGE_PREFIX_BALCK = "B_";
    public static final String CHESSMAN_IMAGE_SUFFIX_DEFAULT = "";
    public static final String CHESSMAN_IMAGE_SUFFIX_GRAY = "_GRAY";
    public static final String CHESSMAN_IMAGE = "/%s%s%s.GIF";
}
