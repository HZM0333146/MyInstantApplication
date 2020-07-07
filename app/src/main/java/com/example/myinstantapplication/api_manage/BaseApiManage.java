package com.example.myinstantapplication.api_manage;

import android.content.res.Resources;
public abstract class BaseApiManage {
    public String url ,parameter,httpMethod;
    abstract public String getURL();
    abstract public String getHTTPMethod();
    abstract public String getParameter();
    public String getApiKey(int apiKeyId){
        Resources resources=Resources.getSystem();
        String apiKey=resources.getString(apiKeyId);
        return apiKey;
    }
    public class ParameterBuilder {
        private StringBuilder stringBuilder;
        private int count=-1;//-1為沒有資料
        public ParameterBuilder(){
            stringBuilder=new StringBuilder();
        }
        public ParameterBuilder append(String key, String value){
            count++;//計算新增幾筆資料
            if(count>0){//除了第一筆資料以外前面都加上&符號
                stringBuilder.append("&");
            }
            stringBuilder.append(key);
            stringBuilder.append("=");
            stringBuilder.append(value);
            return this;
        }
        public String toParameterString(){
            return stringBuilder.toString();
        }
    }
}
