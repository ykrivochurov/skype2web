package ru.ykey.service;

import com.skype.*;
import org.apache.commons.lang.time.DateUtils;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * User: y.krivochurov
 * Date: 15.12.12
 * Time: 14:55
 */
public class ShitChatReader {

    public static final String CHAT_ID = "#yuri.bulkin/$i_yarkov;56ce94e140aeac30";

/*
    public static void main(String[] args) throws Exception {
        Long timeStamp = System.currentTimeMillis();
        if (args != null && args.length > 0 && args[0] != null) {
            try {
                timeStamp = Long.parseLong(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Can't parse to long " + args[0]);
            }
            if ("yesterday".equals(args[0])) {
                timeStamp -= TimeUnit.DAYS.toMillis(1);
            }
        }

        Skype.setDaemon(false); // to prevent exiting from this program
        for (Chat chat : Skype.getAllRecentChats()) {
            if (CHAT_ID.equals(chat.getId())) {
                for (ChatMessage chatMessage : chat.getAllChatMessages()) {
                    if (DateUtils.isSameDay(new Date(timeStamp), chatMessage.getTime())) {
                        openIfUrl(chatMessage);
                    }
                }
                break;
            }
        }

        Skype.addChatMessageListener(new ChatMessageAdapter() {
            public void chatMessageReceived(ChatMessage received) throws SkypeException {
                if (received.getType().equals(ChatMessage.Type.SAID)) {
                    try {
                        openIfUrl(received);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private static void openIfUrl(ChatMessage chatMessage) throws SkypeException, IOException {
        String content = chatMessage.getContent();
        if (content.contains("http")) {
            for (String s : content.split("\\s")) {
                if (s.startsWith("http")) {
                    java.awt.Desktop.getDesktop().browse(java.net.URI.create(s));
                }
            }
        }

    }
*/

}
