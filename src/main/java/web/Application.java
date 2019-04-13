package web;

import common.Util;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.charset.Charset;
import java.util.Arrays;
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
            System.out.println("search...");
            System.out.println("queryStr " + queryStr);
//            byte[] bytes = queryStr.getBytes(Charset.defaultCharset());
//            String queryStr2 = new String(bytes,)
            List<List<String>> result = Query.getResult(queryStr);
            System.out.print("result.size() ");
            System.out.println(result.size());
            model.addAttribute("result", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "result";
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Arguments are not correct.");
            return;
        }
        String env = args[0];
        Util.setEnv(env);

        SpringApplication.run(Application.class, args);
    }
}

