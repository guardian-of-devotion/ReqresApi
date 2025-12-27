package models;

public class SingleResourceResponse {
    private ResourceData data;
    private Support support;

    public ResourceData getData() {
        return data;
    }

    public void setData(ResourceData data) {
        this.data = data;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }
}
