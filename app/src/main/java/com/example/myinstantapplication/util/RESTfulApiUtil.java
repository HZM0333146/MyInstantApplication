package com.example.myinstantapplication.util;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Key;
import java.util.Map;

public class RESTfulApiUtil {
        private String baseUri, requestMethod, parameter;
        public RESTfulApiUtil(String baseUri, String parameter, String requestMethod){
            this.baseUri=baseUri;
            this.parameter=parameter;
            this.requestMethod=requestMethod;
        }
        public String[] connectUriGetData() throws MalformedURLException,IOException{
            String[] htmlInf=new String[2];
            URL url=null;
            HttpURLConnection urlConnection=null;
            try {
                url=new URL(baseUri);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setChunkedStreamingMode(0);//不知道資料長度時呼叫，避免緩存耗盡
                urlConnection.setRequestMethod(requestMethod);//預設是GET 用POST要改
                urlConnection.setDoOutput(true);//default id false
                urlConnection.setDoInput(true);//default is true

                String outputData = parameter;

                writeHtmldata(urlConnection,outputData);
                int responseCode=urlConnection.getResponseCode();
                if(responseCode==200){
                    htmlInf[0] =String.valueOf(responseCode);
                    htmlInf[1] = readHtmlStream(urlConnection);
                }else {
                    htmlInf[0] =String.valueOf(responseCode);
                    htmlInf[1] ="訪問失敗";
                }
            } finally {
                urlConnection.disconnect();
            }
            return htmlInf;
        }

        private void writeHtmldata(HttpURLConnection urlConnection,String outputData) throws IOException {
            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));//bufferwriter 處理的資料是字串 自動在字元跟位元組之間作轉換
            try {
                writer.write(outputData);
                writer.flush();
            }finally {
                writer.close();
                os.close();
            }
        }

        private String readHtmlStream(HttpURLConnection urlConnection) throws IOException {
            InputStream is = new BufferedInputStream(urlConnection.getInputStream());//讀進來時不需做位元組與字元轉換 不需要用reader
            OutputStream bo = new ByteArrayOutputStream();
            try {
                int i = is.read();
                while (i != -1) {
                    bo.write(i);
                    i = is.read();
                }
            } finally {
                bo.close();
                is.close();
            }
            return bo.toString();
        }
}
