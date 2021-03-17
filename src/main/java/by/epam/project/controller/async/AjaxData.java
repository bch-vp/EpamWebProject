package by.epam.project.controller.async;

public class AjaxData {
    private String json;
    private int statusHttp;

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
}
