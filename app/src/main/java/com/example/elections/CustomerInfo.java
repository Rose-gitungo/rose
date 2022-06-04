package com.example.elections;

public class CustomerInfo {

    private String first;
    private String last;
    private String emailsign;
    private String phoneno;
    private String idno;
    private String password2;
    private String confrimpassword;

//required when using firebase
public  CustomerInfo(){

}
//creating getters and setters


    public String getfirst() {
        return first;
    }

    public void setfirst(String first) {
        this.first = first;
    }

    public String getlast() {
        return last;
    }

    public void setlast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return emailsign;
    }

    public void setEmail(String email) {
        this.emailsign = email;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getConfrimpassword() {
        return confrimpassword;
    }

    public void setConfrimpassword(String confrimpassword) {
        this.confrimpassword = confrimpassword;
    }
}

