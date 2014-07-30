package com.pinganfu.finmng.web.support;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class DownloadHelper {

    private HttpServletRequest request;
    private boolean forOpen = false;

    private boolean clearFile = false;

    private Map<String,String> headers = new HashMap<String, String>();

    private File file;

    private String content;

    private Charset charset = Charset.forName("UTF-8");

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public DownloadHelper() {
        super();
        setContentType(null);
    }

    public boolean isForOpen() {
        return forOpen;
    }

    public boolean isClearFile() {
        return clearFile;
    }

    public void setClearFile(boolean clearFile) {
        this.clearFile = clearFile;
    }


    public void setForOpen(boolean forOpen) {
        this.forOpen = forOpen;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public File getFile() {
        return file;
    }

    public String getContent() {
        return content;
    }

    public Charset getCharset() {
        return charset;
    }

    public void setContent(String content,Charset charset){
        setCharset(charset);
        setContentLength(content.getBytes(charset).length);
        this.content = content;
    }


    public void setFile(File file) {
        this.file = file;
        if (file != null) {
            setFileName(file.getName());
            setContentLength(file.length());
        }
    }


    public void setFileName(String origFileName){
        String fileName = origFileName;
        fileName = fileName==null&&getFile()!=null ? getFile().getName() : fileName;
        if(fileName==null){
            return;
        }
        StringBuilder sb = new StringBuilder();
        if(forOpen){
            sb.append("inline;");
        }else{
            sb.append("attachment;");
        }
        if(!fileName.trim().equals("")){
            sb.append("filename=\"");
            try {
                sb.append(new String(fileName.getBytes("UTF-8"),"ISO8859-1"));
            } catch (UnsupportedEncodingException e) {
                sb.append("downloadfile");
            }
            sb.append("\"");
        }
        headers.put("Content-Disposition", sb.toString());
    }


    public void setContentType(String origContentType){
        String contentType = origContentType;
        StringBuilder sb = new StringBuilder();
        contentType = null == contentType ? "application/octet-stream"
                : contentType;
        sb.append(contentType);
        sb.append(";");
        headers.put("Content-Type", sb.toString());

    }

    public void setContentLength(long size){
        headers.put("Content-Length", Long.toString(size));
    }

    public void setCharset(Charset charset){
        this.charset = charset;
        String contentType = headers.get("Content-Type");
        contentType += "charset="+charset.name()+";";
        headers.put("Content-Type", contentType);
    }
}