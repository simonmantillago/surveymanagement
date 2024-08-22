package com.surveymanagement.subresponseoption.domain.entity;

public class SupResponseOptions {
    private int id;
    private int subResponseNumber;
    private String createdAt;
    private int responseOptionId;
    private String updateAt;
    private String subResponseText;
    
    public SupResponseOptions(int id, int subResponseNumber, String createdAt, int responseOptionId, String updateAt,
            String subResponseText) {
        this.id = id;
        this.subResponseNumber = subResponseNumber;
        this.createdAt = createdAt;
        this.responseOptionId = responseOptionId;
        this.updateAt = updateAt;
        this.subResponseText = subResponseText;
    }

    public SupResponseOptions() {
    }

    public SupResponseOptions(int id, int subResponseNumber, int responseOptionId, String subResponseText) {
        this.id = id;
        this.subResponseNumber = subResponseNumber;
        this.responseOptionId = responseOptionId;
        this.subResponseText = subResponseText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubResponseNumber() {
        return subResponseNumber;
    }

    public void setSubResponseNumber(int subResponseNumber) {
        this.subResponseNumber = subResponseNumber;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getResponseOptionId() {
        return responseOptionId;
    }

    public void setResponseOptionId(int responseOptionId) {
        this.responseOptionId = responseOptionId;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getSubResponseText() {
        return subResponseText;
    }

    public void setSubResponseText(String subResponseText) {
        this.subResponseText = subResponseText;
    }

    
    

}
