package org.zerock.myapp.seq_1.servlet;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;


@Log4j2
@NoArgsConstructor

@WebServlet(name = "Hello2Servlet", urlPatterns = { "/Hello2" } )
public class Hello2Servlet extends HttpServlet {
    @Serial private static final long serialVersionUID = 1L;


    @PostConstruct
    void postConstruct() {
        log.trace("postConstruct() invoked.");
    } // postConstruct

    @Override
    public void init(ServletConfig config) {
        log.trace("init({}) invoked.", config);

        String param1 = config.getServletContext().getInitParameter("param1");
        String param2 = config.getServletContext().getInitParameter("param2");

        log.info("\t+ Context Parameters: param1({}), param2({})", param1, param2);
    } // init

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        log.trace("service(req, res) invoked.");

        try {
            res.setCharacterEncoding("utf8");
            res.setContentType("text/html; charset=utf8");

            @Cleanup PrintWriter out = res.getWriter();
            out.println("<h3>/Hello2 (Hello2Servlet)</h3><hr>");
            out.flush();
        } catch(IOException e) {
            throw new IOException(e);
        } catch(Exception e) {
            throw new ServletException(e);
        } // try-catch
    } // service

    @PreDestroy
    void preDestroy() {
        log.trace("preDestroy() invoked.");
    } // preDestroy

    @Override
    public void destroy() {
        log.trace("destroy() invoked.");
    } // destroy

} // end class

