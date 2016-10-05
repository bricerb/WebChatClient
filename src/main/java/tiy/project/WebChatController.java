package tiy.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brice on 9/23/16.
 */

@Controller
public class WebChatController {

    @Autowired
    MessageRepo messages;

    @Autowired
    UserRepo users;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName, String password) throws Exception {
        User user = users.findFirstByName(userName);
        if (user == null) {
            user = new User();
//            throw new Exception("Username not found");
            user.setName(userName);
            user.setPassword(password);
            users.save(user);
            session.setAttribute("user", user);
        } else if (!password.equals(user.getPassword())) {
            throw new Exception("Incorrect password");
        } else {
            session.setAttribute("user", user);
        }
        return "redirect:/";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model) {
        List<Message> messageList = new ArrayList<Message>();
        if (session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
        }

        User myUser = (User) session.getAttribute("user");
        if (myUser != null) {
            messageList = messages.findByUser(myUser);
        }
        model.addAttribute("messages", messageList);
        return "home";
    }

    @RequestMapping(path = "/create-user", method = RequestMethod.GET)
    public String createNewUser(HttpSession session, String userName, String password) throws Exception {
        User user = users.findFirstByName(userName);
        if (user == null) {
            user.setName(userName);
            user.setPassword(password);
            users.save(user);
        } else if (!password.equals(user.getPassword())) {
            throw new Exception("Incorrect password");
        } else {
            session.setAttribute("user", user);
        }
        return "home";
    }
}
