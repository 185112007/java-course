package edu.javacourse.studentorder.domain;

public class CountryArea {
    private String areadId;
    private String areaName;

    public CountryArea() {
    }

    public CountryArea(String areadId, String areaName) {
        this.areadId = areadId;
        this.areaName = areaName;
    }

    public String getAreadId() {
        return areadId;
    }

    public void setAreadId(String areadId) {
        this.areadId = areadId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public String toString() {
        return "CountryArea{" +
                "areadId='" + areadId + '\'' +
                ", areaName='" + areaName + '\'' +
                '}';
    }
}
