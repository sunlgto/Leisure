package com.yh.lhut;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

@SpringBootTest
class LHutApplicationTests {


    @Autowired
    private RestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {

        //https://love.siriling.com:1314/index.php/blessing.html/comment

        //author: robot
        //mail: 98111789@qq.com
        //url:
        //text: 9
        //restTemplate.postForObject()
        String url ="https://love.siriling.com:1314/index.php/blessing.html/comment?author=111&url=222&text=231&mail=222@qq.com";
        //  url text  mail
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("author","23123");
        jsonObject.put("url","321312");
        jsonObject.put("text","321312");
        jsonObject.put("mail","321312");
        System.out.println(sendPost(url, jsonObject.toJSONString()));

    }

    public static String sendPost(String url,String param) throws Exception{
		BufferedReader in = null;
		String result = "";
		URL realUrl = new URL(url);
		// 打开和URL之间的连接
		URLConnection conn = realUrl.openConnection();
		//设置超时时间
		conn.setConnectTimeout(600000);
		conn.setReadTimeout(600000);
		// conn.setRequestProperty("ContentType","text/xml;charset=utf-8");
		conn.setRequestProperty("Charset", "UTF-8");
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		//设置Content-type 为 application/json
		conn.addRequestProperty("Content-type", "application/json");
		// 发送POST请求必须设置如下两行
		conn.setDoOutput(true);
		conn.setDoInput(true);
		// 获取URLConnection对象对应的输出流
		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
		// 发送请求参数
//	        if(StringUtils.isNotBlank(param)){
//	        	param = URLEncoder.encode(param, "GBK");
//	        }
		out.write(param);
		// flush输出流的缓冲
		out.flush();
		// 定义BufferedReader输入流来读取URL的响应
		in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		String line;
		while ((line = in.readLine()) != null) {
			result += line;
		}
		if(out!=null){
			out.close();
		}
		if(in!=null){
			in.close();
		}
		return result;
	}

}
