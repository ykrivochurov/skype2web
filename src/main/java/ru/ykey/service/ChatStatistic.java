package ru.ykey.service;

import com.skype.Chat;
import com.skype.ChatMessage;
import com.skype.Skype;
import com.skype.SkypeException;
import com.skype.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * User: y.krivochurov
 * Date: 15.12.12
 * Time: 16:59
 */
public class ChatStatistic {

    private static Map<String, Integer> users = new HashMap<String, Integer>();

    public static void main(String[] args) throws SkypeException {
        ChatMessage lastMessage = null;
        for (Chat chat : Skype.getAllRecentChats()) {
            if (ShitChatReader.CHAT_ID.equals(chat.getId())) {
                for (ChatMessage chatMessage : chat.getAllChatMessages()) {
                    User sender = chatMessage.getSender();
                    String userName = sender.toString() + " (" + sender.getFullName() + ")";
                    Integer count = users.get(userName);
                    if (count == null) {
                        count = 1;
                    }
                    users.put(userName, ++count);
                    lastMessage = chatMessage;
                }
                System.out.println("Last date: " + lastMessage.getTime());
                break;
            }
        }

        ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(users.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for (Map.Entry<String, Integer> stringIntegerEntry : entries) {
            System.out.println(stringIntegerEntry.getKey() + " : " + stringIntegerEntry.getValue());
        }
    }

}
