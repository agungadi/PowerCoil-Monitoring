package org.uts.powercoil.model;



public class Data {
    public String id;
    public String userName;
    public String email;
    public String roles;
    public String password;
    public String nohp;
    public String perusahaan;

    public Data() {
    }

    public Data(String id, String userName, String email, String roles, String password, String nohp, String perusahaan) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.nohp = nohp;
        this.perusahaan = perusahaan;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return userName;
    }

    public void setName(String userName) {
        this.userName = userName;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getNoHp() {
        return nohp;
    }

    public void setNoHp(String nohp) {
        this.nohp = nohp;
    }
    public String getPerusahaan() {
        return perusahaan;
    }

    public void setPerusahaan(String perusahaan) {
        this.perusahaan = perusahaan;
    }

}
