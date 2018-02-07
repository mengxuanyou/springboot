package com.wbkit.bigscreen.controller;

import com.wbkit.bigscreen.entity.User;
import com.wbkit.bigscreen.services.UserService;
import com.wbkit.bigscreen.util.Connection2hbase;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String hello(ModelMap model) {
        List<User> users = userService.getAllUser();
        System.out.println(users);
        model.addAttribute("users", users);
        return "demo";
    }

    @RequestMapping("/insert")
    @ResponseBody
    public String insert(Integer id, String name) {
        if (null == id || "".equals(id)) {
            return "id is not empty";
        }

        if (null == name || "".equals(name)) {
            return "name is not empty";
        }

        userService.insert(id, name);

        return "success";
    }

    @RequestMapping("/hbase")
    @ResponseBody
    public String hbase() {
        HTable table = Connection2hbase.getHTable("gd:Sum_Statistic");
        Scan scan = new Scan();
        try {
            ResultScanner resultScanner = table.getScanner(scan);
            for (Result result : resultScanner) {
                String date = new String(result.getRow());
                System.out.println(date);
                date = date.substring(date.length() - 8);
                System.out.println(date);

                for (Cell cell : result.listCells()){
                    System.out.println(new String(CellUtil.cloneRow(cell)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
