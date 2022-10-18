package com.team.webdav.config;

import org.apache.catalina.servlets.WebdavServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "webdavSupport",
        urlPatterns = {"/dav/*"},
        initParams = {
                @WebInitParam(name = "listings", value = "true"),
                @WebInitParam(name = "readonly", value = "false"),
                @WebInitParam(name = "debug", value = "0")
        }
)
public class WebdavSupport extends WebdavServlet {
}
