package io.ashimjk.spring.cloud.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RefreshScope
public class RatesController {

    @Value("${rate}")
    private String rate;

    @Value("${lanecount}")
    private String laneCount;

    @Value("${tollstart}")
    private String tollStart;

    @Value("${environment}")
    private String environment;

    @Value("${connstring}")
    private String connstring;

    @RequestMapping("/rate")
    public String getRates(Model model) {
        model.addAttribute("rateAmount", rate);
        model.addAttribute("lanes", laneCount);
        model.addAttribute("tollStart", tollStart);
        model.addAttribute("connstring", connstring);
        model.addAttribute("environment", environment);

        return "rateView";
    }

}
