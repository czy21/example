package com.team.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team.dal.system.MenuDao;
import com.team.entity.system.Menu;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class BaseController {

    @Autowired
    private MenuDao menuDao;

    @GetMapping("/")
    public String Index() {
        return "主页";
    }

    @GetMapping("csrf")
    public String Csrf() {
        return "csrf";
    }

    @GetMapping("/api/docs")
    public Object Docs() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet("http://localhost:8075/v2/api-docs");
            try (CloseableHttpResponse response = httpClient.execute(httpget)) {
                return JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @GetMapping("Test")
    public Object Test() {

        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Menu::getIsMenu, true);
       return menuDao.selectList(queryWrapper);

    }
}
