package com.sonnet.ajax.tets;

import com.alibaba.fastjson.JSONObject;
import com.sonnet.ajax.pojo.Agent;

public class JsonTest {

    public static void main(String[] args) {
        Agent agent = new Agent();
        agent.setAid(1);
        agent.setAno("ano");
        agent.setAname("代理商");
        agent.setAregion("成都");
        System.out.println(agent.toJson());
        // 将一个对象转换为JSON格式的字符串
        System.out.println(JSONObject.toJSONString(agent));
    }
}
