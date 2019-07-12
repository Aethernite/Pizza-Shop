package PizzaShop.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@org.springframework.stereotype.Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(ModelAndView modelAndView, HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            modelAndView.setViewName("base-layout");
            modelAndView.addObject("view", "views/error");
            modelAndView.addObject("error", statusCode);
        }
        return modelAndView;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }


}