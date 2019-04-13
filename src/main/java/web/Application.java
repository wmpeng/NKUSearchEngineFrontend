package web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@SpringBootApplication
@Controller
public class Application {

    @RequestMapping("/")
//    @ResponseBody
    public String index() {
        return "index";
    }

    @RequestMapping("/search")
    public String search(Model model, @RequestParam("search") String queryStr) {
        model.addAttribute("name", "world");
        try {
            System.out.println("search");
            List<List<String>> result = Query.getResult(queryStr);
            System.out.println(result.size());
            model.addAttribute("result", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "result";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

