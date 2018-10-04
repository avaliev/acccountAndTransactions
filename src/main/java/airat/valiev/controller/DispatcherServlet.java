package airat.valiev.controller;

import airat.valiev.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class DispatcherServlet extends HttpServlet {


    AccountService accountService = new AccountService();


    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>Hello from HelloServlet</h1>");


        // this is the example of mapping http request to BL service
        if (request.getRequestURI().contains("/account")) {
            if (request.getParameter("action").equals("create")) {
                accountService.createAccount(Long.parseLong(request.getParameter("personId")),
                        BigDecimal.valueOf(Double.parseDouble(request.getParameter("balans"))));

            }

        }
        System.out.println("request URI:" + request.getRequestURI());
        System.out.println("request URL:" + request.getRequestURL());
    }
}
