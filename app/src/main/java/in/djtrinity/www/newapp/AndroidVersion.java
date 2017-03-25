package in.djtrinity.www.newapp;

public class AndroidVersion {

    private String android_version_name;
    private String android_image_url;
    private String eventdesc;
    public String eventdates;
    String venue;

    public String getAndroid_version_name() {
        return android_version_name;
    }

    public void setAndroid_version_name(String android_version_name) {
        this.android_version_name = android_version_name;
    }

    public String getAndroid_image_url() {
        return android_image_url;
    }

    public void setAndroid_image_url(String android_image_url) {
        this.android_image_url = android_image_url;
    }
    public String geteventdesc()
    {
        return eventdesc;
    }
    public void setEventdesc(String eventdesc)
    {
        this.eventdesc=eventdesc;
    }
    public String geteventdates()
    {

        return eventdates;
    }
    public void seteventdates(String eventdates)
    {
        this.eventdates=eventdates;
    }
    public void setvenue(String venue)
    {
        this.venue=venue;
    }
    public String getVenue()
    {
        return venue;
    }

}