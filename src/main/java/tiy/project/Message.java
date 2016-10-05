package tiy.project;

import javax.persistence.*;

/**
 * Created by Brice on 9/23/16.
 */

@Entity
public class Message {

    @Id
    @GeneratedValue
    int id;

    @Column
    String text;

    @ManyToOne
    User user;


    public Message(String text, User user) {
        this.text = text;
        this.user = user;
    }

    public Message() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
