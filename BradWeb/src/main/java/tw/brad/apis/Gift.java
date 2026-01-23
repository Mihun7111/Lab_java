package tw.brad.apis;

public class Gift {
    private long id;
    private String name, feature, addr, tel, picurl;
    private double lat, lng;

    // Getter and Setter for id
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for feature
    public String getFeature() {
        return feature;
    }
    public void setFeature(String feature) {
        this.feature = feature;
    }

    // Getter and Setter for add
    public String getAddr() {
        return addr;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }

    // Getter and Setter for tel
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }

    // Getter and Setter for picurl
    public String getPicurl() {
        return picurl;
    }
    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    // Getter and Setter for lat
    public double getLat() {
        return lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }

    // Getter and Setter for lng
    public double getLng() {
        return lng;
    }
    public void setLng(double lng) {
        this.lng = lng;
    }
	@Override
	public String toString() {
		return String.format("%s:%s:%s", name, addr, tel);
	}
    
}
