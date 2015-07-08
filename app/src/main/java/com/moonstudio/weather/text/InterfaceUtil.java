package com.moonstudio.weather.text;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Michael on 2015/7/8.
 */
public class InterfaceUtil {
    public static void main(String path[]) throws Exception {
        URL u=new URL("http://route.showapi.com/119-42?showapi_appid=myappid&showapi_timestamp=20150708142735&date=&showapi_sign=simple_mysecret");
        InputStream in=u.openStream();
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        try {
            byte buf[]=new byte[1024];
            int read = 0;
            while ((read = in.read(buf)) > 0) {
                out.write(buf, 0, read);
            }
        }  finally {
            if (in != null) {
                in.close();
            }
        }
        byte b[]=out.toByteArray( );
        System.out.println(new String(b,"utf-8"));
    }
}
