package tiy.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Brice on 9/23/16.
 */

@RestController
public class WebChatJSONController {

    @Autowired
    MessageRepo messages;

    @Autowired
    UserRepo users;

    @RequestMapping(path = "/get-message.json", method = RequestMethod.GET)
    public List<Message> getMessage() {
        List<Message> messageList = new ArrayList<>();
        Iterable<Message> allMessages = messages.findAll();

//        System.out.println("getMessage works");
        for (Message mySalt : allMessages) {
            messageList.add(mySalt);
        }
        return messageList;
    }

    @RequestMapping(path = "/message.json", method = RequestMethod.POST)
    public List<Message> message(@RequestBody Message message, HttpSession session) {
        User user = (User)session.getAttribute("user");
        message.user = user;
        Client myClient = new Client();
        myClient.sendMessage(message.getText());


//        System.out.println("message works");
        try {
            if (user == null) {
                throw new Exception("Unable to add message without an active user in the session");
            }
        } catch (Exception ex) {}

//        message.user = user;

        messages.save(message);
//        System.out.println(message.text);
        return getMessage();
    }
}