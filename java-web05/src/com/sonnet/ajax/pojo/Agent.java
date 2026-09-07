package com.sonnet.ajax.pojo;

public class Agent {

    private int aid;

    private String ano;

    private String aname;

    private String aregion;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getAregion() {
        return aregion;
    }

    public void setAregion(String aregion) {
        this.aregion = aregion;
    }

    public String toJson() {
        return "{ \"aid\":" + ",\"ano\":\"" + ano + "\",\"aname\":\"" + aname + "\",\"aregion\":\"" + aregion + "\"}";
    }
}
