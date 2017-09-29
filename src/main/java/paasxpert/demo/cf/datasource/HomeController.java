package paasxpert.demo.cf.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.util.UUID;

@Controller
public class HomeController {

    @Autowired(required = false) DataSource dataSource;
    @Autowired(required = false) RedisConnectionFactory redisConnectionFactory;

    @RequestMapping("/")
    public String home(Model model, HttpSession session) {
        model.addAttribute("datasource", ParseUtil.toString(dataSource));
        model.addAttribute("redis", ParseUtil.toString(redisConnectionFactory));
        model.addAttribute("uid", getSession(session));
        return "home";
    }

    private String getSession(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return uid.toString();
    }

}
