package com.aps.biometricauthapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Address implements Serializable {
    @SerializedName("cep")
    private String cep;
    @SerializedName("logradouro")
    private String publicPlace;
    @SerializedName("complemento")
    private String complement;
    @SerializedName("bairro")
    private String district;
    @SerializedName("localidade")
    private String location;
    @SerializedName("uf")
    private String uf;
    @SerializedName("ibge")
    private String ibge;
    @SerializedName("gia")
    private String gia;
    @SerializedName("ddd")
    private String ddd;
    @SerializedName("siafi")
    private String siafi;
    @SerializedName("erro")
    private Boolean error;

    public Address(String cep,
                   String publicPlace,
                   String complement,
                   String district,
                   String location,
                   String uf) {
        this.cep = cep;
        this.publicPlace = publicPlace;
        this.complement = complement;
        this.district = district;
        this.location = location;
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public void setPublicPlace(String publicPlace) {
        this.publicPlace = publicPlace;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getSiafi() {
        return siafi;
    }

    public void setSiafi(String siafi) {
        this.siafi = siafi;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Address{" +
                "cep='" + cep + '\'' +
                ", publicPlace='" + publicPlace + '\'' +
                ", complement='" + complement + '\'' +
                ", district='" + district + '\'' +
                ", location='" + location + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }
}
