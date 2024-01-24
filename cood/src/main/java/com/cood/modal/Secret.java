package com.cood.modal;

public class Secret {
    private String rawSecret;
    private String encryptedSecret;
    private String url;
    private String createdDateTime;
    private Boolean isSecretRead;

    public Secret(){

    }
    public Secret(String encryptedSecret){
        this.encryptedSecret = encryptedSecret;

    }
    public String getRawSecret() {
        return rawSecret;
    }

    public String getEncryptedSecret() {
        return encryptedSecret;
    }

    public String getUrl() {
        return url;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public Boolean getSecretRead() {
        return isSecretRead;
    }

    public void setRawSecret(String rawSecret) {
        this.rawSecret = rawSecret;
    }

    public void setEncryptedSecret(String encryptedSecret) {
        this.encryptedSecret = encryptedSecret;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public void setSecretRead(Boolean secretRead) {
        isSecretRead = secretRead;
    }
}
