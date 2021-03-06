package ru.ykey.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * User: yurikrivochurov
 * Date: 9/7/13
 */
@Entity
@Table(name = "chat_date")
public class ChatDate extends BaseEntity {

    @Column(name = "message_count")
    private Integer count = 0;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column
    private Date date;

    @JsonIgnore
    @ManyToOne
    private Chat chat;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Chat getChat() {
        return chat;
    }

    @Transient
    public Long getIdOfChat() {
        return chat.getId();
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}