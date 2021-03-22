package by.epam.project.controller.async;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class AjaxData {
    private String json;
    private int statusHttp;
    private Map<String, Object> dataSession = new HashMap<>();

    public AjaxData(int statusHttp) {
        this.statusHttp = statusHttp;
    }

    public AjaxData() {
        this.statusHttp = HttpServletResponse.SC_OK;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public int getStatusHttp() {
        return statusHttp;
    }

    public void setStatusHttp(int statusHttp) {
        this.statusHttp = statusHttp;
    }

    public Map<String, Object> getDataSession() {
        return dataSession;
    }

    public void setDataSession(Map<String, Object> dataSession) {
        this.dataSession = dataSession;
    }

    public void putDataToDataSession(String key, Object value) {
        this.dataSession.put(key, value);
    }
}
