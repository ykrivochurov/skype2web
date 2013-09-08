package ru.ykey.util;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: yurikrivochurov
 * Date: 9/8/13
 * s2w - CL tool для выполнения определенных действий на сайте.
 * Можно сделать его обучаемым, например задавать новую команду и JS для это команды...
 */
public class TextParser {

    public static final Pattern URL_REGEXP = Pattern.compile("(http|https|ftp|ftps)\\:\\/\\/[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(\\/\\S*)?");
    public static final String[] IMG_PATTERNS = new String[]{".png", ".jpg", ".gif", ".jpeg"};
    public static final String YOUTUBE_PATTERN = ".youtube.";
    public static final Pattern S2W_PREFIX = Pattern.compile("s2w:(.*)\n");
    //todo twitter
    //todo VK
    //todo facebook
    //todo instagram

    public static String parse(String text) {
        text = StringEscapeUtils.escapeHtml4(text);
        Matcher matcher = URL_REGEXP.matcher(text);
        while (matcher.find()) {
            String matchedText = matcher.group();
            if (isImg(matchedText)) {
                text = text.replace(matchedText, "<img src=\"" + matchedText + "\">");
            } else if (isYoutube(matchedText)) {
                text = text.replace(matchedText, "<iframe width=\"480\" height=\"320\" src=\"//www.youtube.com/embed/" + getYoutubeVideoId(matchedText) + "\" frameborder=\"0\" allowfullscreen></iframe>");
            } else {
                text = text.replace(matchedText, "<a href=\"" + matchedText + "\">" + matchedText + "</a>");
            }
        }
        return text;
    }

    private static boolean isImg(String text) {
        for (String imgPattern : IMG_PATTERNS) {
            if (StringUtils.containsIgnoreCase(text, imgPattern)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isYoutube(String text) {
        return StringUtils.containsIgnoreCase(text, YOUTUBE_PATTERN);
    }

    private static String getYoutubeVideoId(String text) {
        Pattern pattern = Pattern.compile("(\\&|\\?)v=([a-zA-Z0-9]*)");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            return matcher.group(2);
        }
        return "";
    }
}