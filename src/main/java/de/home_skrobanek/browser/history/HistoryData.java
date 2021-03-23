package de.home_skrobanek.browser.history;

public class HistoryData {

    private String title, site, time;

    public HistoryData(String title, String site, String time) {
        this.title = title;
        this.site = site;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
