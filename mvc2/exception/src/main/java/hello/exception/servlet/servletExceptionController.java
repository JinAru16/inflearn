package hello.exception.servlet;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Slf4j
@Controller
public class servletExceptionController {
    @GetMapping("/error-ex")
    public void errorEx(){
        throw new RuntimeException("예외발생!");
    }



    @GetMapping("/error-400")
    public void err400(HttpServletResponse response) throws IOException{
        response.sendError(400, "400오류!");
    }
    @GetMapping("/error-404")
    public void err404(HttpServletResponse response) throws IOException{
        response.sendError(404, "404 오류!");
    }

    @GetMapping("/error-500")
    public void err500(HttpServletResponse response) throws IOException{
        response.sendError(500, "500 오류!");
    }
}
