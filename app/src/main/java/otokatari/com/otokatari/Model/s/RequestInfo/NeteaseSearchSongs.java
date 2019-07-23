package otokatari.com.otokatari.Model.s.RequestInfo;

public class NeteaseSearchSongs {
    private String keywords;
    private int limit;
    private int offset;

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
