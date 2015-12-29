package com.github.eljah;

import com.chargebee.Environment;
import com.chargebee.Result;
import com.chargebee.models.HostedPage;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by eljah32 on 12/27/2015.
 */
public class RedirectHandlerServet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if ("succeeded".equals(req.getParameter("state"))) {
    /*
     * Retrieving the hosted page and getting the details
     * of the subscription created through hosted page.
     */
            String hostedPageId = req.getParameter("id");
            Result result = HostedPage.retrieve(hostedPageId).request();
            HostedPage hostedPage = result.hostedPage();
            if(!hostedPage.state().equals(HostedPage.State.SUCCEEDED)) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
    /*
     * Forwarding the user to thank you page.
    */
            HostedPage.Content content = hostedPage.content();
            String queryParameters = "name=" + content.customer().firstName()
                    + "&planId=" + content.subscription().planId();
            resp.sendRedirect("thankyou?" + queryParameters);
        } else {
    /*
     * If the state is not success then displaying
     * error page to the customer.
     */
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }
}
