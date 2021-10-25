package com.mojaraagadget;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Base64;


@Named
@RequestScoped
public class ReadObjectBean {

    private String payload;
    private String message;

    public void setPayload(String elString){
        this.payload = elString;
    }
    public String getPayload(){
        return this.payload;
    }

    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }

    public void unserializePaylod(){

        try {
            String param = this.payload;

            if (param == null || param.equals("")){
                this.message = "[+] error : nothing to deserialize.\n";
                return;
            }
            byte[] decodedBytes = Base64.getDecoder().decode(param);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decodedBytes);
            ObjectInputStream in = new ObjectInputStream(byteArrayInputStream);

            // trigger the deserialization
            Object obj = in.readObject();
            this.message = "[+] successfully call readObject on the param value ! \n";

        } catch (Exception e) {
            e.printStackTrace();
            this.message = "\n[+] error : " + e.getMessage();
        }
    }

}
