package com.github.eljah;

import com.chargebee.Result;
import com.chargebee.models.HostedPage;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by eljah32 on 12/28/2015.
 */
public class ThankYouServlet  extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name=req.getParameter("name");
        String planId=req.getParameter("planId");
            resp.getWriter().println("Thank you "+name+" for buying "+planId);

    }
}
