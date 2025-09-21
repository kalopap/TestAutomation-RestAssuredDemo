package pojos;

public class Article {

    private String id;
    private String locale;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArticleDetails getDetails() {
        return details;
    }

    public void setDetails(ArticleDetails details) {
        this.details = details;
    }

    private String title;
    private ArticleDetails details;


}
