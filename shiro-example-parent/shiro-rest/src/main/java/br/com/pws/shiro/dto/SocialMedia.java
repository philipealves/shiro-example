package br.com.pws.shiro.dto;

public class SocialMedia {
    private String url;
    private String style;
    private String icon;

    public SocialMedia() {
        super();
    }

    public SocialMedia(String url, String style, String icon) {
        super();
        this.url = url;
        this.style = style;
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
