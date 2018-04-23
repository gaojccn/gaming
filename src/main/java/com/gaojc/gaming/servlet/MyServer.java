
package com.gaojc.gaming.servlet;

import com.gaojc.gaming.service.MyCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author gaojc
 * @date 2018/4/19
 * @description
 */
public class MyServer extends HttpServlet {
    private static final long serialVersionUID = 1309817796926899691L;
    private static final Logger logger = LoggerFactory.getLogger(MyServer.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.print("=====================");
        logger.info("----------come-here------------------");

        String signature = request.getParameter("signature");

        String timestamp = request.getParameter("timestamp");

        String nonce = request.getParameter("nonce");

        String echostr = request.getParameter("echostr");
        System.out.println("sign:" + signature + ";time:" + timestamp + ";nonce:" + nonce + ";echostr" + echostr);
        logger.info("sign:" + signature + ",time:" + timestamp + ",nonce:" + nonce + ",echostr:" + echostr);

        PrintWriter out = response.getWriter();
        if (signature.equals("1")) {
            out.print("1111111");
        }
        if (MyCheck.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
        out = null;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
