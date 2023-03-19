package com.lastmin.ichor.domains;

public class Request {
    private String orgName;
    private String bloodGroup;
    private String Description;

    @Override
    public String toString() {
        return "Request{" +
                "orgName='" + orgName + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }

    public Request(){

    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Request(String orgName, String bloodGroup, String description) {
        this.orgName = orgName;
        this.bloodGroup = bloodGroup;
        Description = description;
    }
}
