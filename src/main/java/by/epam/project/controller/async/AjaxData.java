package by.epam.project.controller.async;

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

    public Map<String, Object> getDataToSession() {
        return dataSession;
    }

    public void setDataToSession(Map<String, Object> dataToSession) {
        this.dataSession = dataToSession;
    }

    public void putDataToDataSession(String key, Object value) {
        this.dataSession.put(key, value);
    }
}
