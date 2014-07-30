package com.pinganfu.finmng.web.web.common;


import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.regex.Pattern;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    public XssHttpServletRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        String header = super.getHeader("Content-Type");
        int index = header.indexOf("application/json");
        if(index >= 0){
            String str = InputStreamTOString(super.getInputStream(), "UTF-8");
            str = URLDecoder.decode(str, "utf-8");
            str = cleanXSS(str);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
            return new MyServletInputStream(byteArrayInputStream) ;
        }
        return super.getInputStream();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values==null)  {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = cleanXSS(values[i]);
        }
        return encodedValues;
    }
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (value == null) {
            return null;
        }
        return cleanXSS(value);
    }
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null){
            return null;
        }
        return cleanXSS(value);
    }
    private String cleanXSS(String paramString) {
        if (paramString == null){
            return "";
        }
        String str = paramString;
        Pattern localPattern = Pattern.compile("<script>", 2);
        str = localPattern.matcher(str).replaceAll("");
        localPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\'(.*?)\\'", 42);
        str = localPattern.matcher(str).replaceAll("");
        localPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", 42);
        str = localPattern.matcher(str).replaceAll("");
        localPattern = Pattern.compile("</script>", 2);
        str = localPattern.matcher(str).replaceAll("");
        localPattern = Pattern.compile("<script(.*?)>", 42);
        str = localPattern.matcher(str).replaceAll("");
        localPattern = Pattern.compile("eval\\((.*?)\\)", 42);
        str = localPattern.matcher(str).replaceAll("");
        localPattern = Pattern.compile("expression\\((.*?)\\)", 42);
        str = localPattern.matcher(str).replaceAll("");
        localPattern = Pattern.compile("javascript:", 2);
        str = localPattern.matcher(str).replaceAll("");
        localPattern = Pattern.compile("javascript.:", 2);
        str = localPattern.matcher(str).replaceAll("");
        localPattern = Pattern.compile("vbscript:", 2);
        str = localPattern.matcher(str).replaceAll("");
        localPattern = Pattern.compile("onload(.*?)=", 42);
        str = localPattern.matcher(str).replaceAll("");
        return str;
    }
    public static String InputStreamTOString(InputStream in,String encoding) throws IOException {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[2046];
        int count = -1;
        while((count = in.read(data,0,2046)) != -1){
            outStream.write(data, 0, count);
        }
        data = null;
        return new String(outStream.toByteArray(), encoding);
    }
    public class MyServletInputStream extends ServletInputStream{
        private ByteArrayInputStream bis;
        public MyServletInputStream(ByteArrayInputStream bis){
            this.bis = bis;
        }
        @Override
        public int read() throws IOException {
           return bis.read();
        }
    }
}
