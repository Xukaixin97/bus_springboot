package com.example.blq.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.Random;

public class GetSMS {
    public static void main(String[] args) {

    }

    /**
     * 向指定号码发送code
     *
     * @param telephone 手机号
     * @return 返回发送的code
     */
    public static String getSMS(String telephone) {
        //随机生成6为验证码
        String code = GetSMS.randomCode();
        DefaultProfile profile = DefaultProfile.getProfile
                ("cn-hangzhou", "LTAIZa230orcFQQB", "zrPIQ3b1KrtEXw0bxBgpbuKUSgDjzw");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", telephone);
        request.putQueryParameter("SignName", "出行Halo");
        request.putQueryParameter("TemplateCode", "SMS_161575247");
        request.putQueryParameter("TemplateParam", "{\"code\":" + code + "}");
        try {
            CommonResponse response = client.getCommonResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return code;
    }

    public static String randomCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }
}