package com.github.eljah;

import com.chargebee.Environment;
import com.chargebee.Result;
import com.chargebee.models.HostedPage;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by eljah32 on 12/27/2015.
 */
public class ChargeBeeHostedPageServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Environment.configure("eljah-test","dbRnLLMXmylv65VfV5r2KZbUtkrmPbXW");
        Result result = HostedPage.retrieve("lcSCHjYQm8zaJTsrPNN3iD7Kq2Dcda2pf").request();
        HostedPage hostedPage = result.hostedPage();

        String planId = "light";
        String hostUrl = req.getServerName().toString() +":"+ req.getServerPort();
        System.out.println(hostUrl+"/redirecthandler");
        Result responseResult = HostedPage.checkoutNew()
                .subscriptionPlanId(planId)
                .embed(Boolean.FALSE)
                //.redirectUrl(hostUrl + "/redirecthandler")
                //.cancelUrl(hostUrl + "/index.html")
                .request();

        String hostedPageUrl = responseResult.hostedPage().url();
/*
 * This will redirect the customers to the ChargeBee server.
 */
        resp.sendRedirect(hostedPageUrl);

    }
}
