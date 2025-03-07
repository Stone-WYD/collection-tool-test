
package com.wyd.web.model.zm;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;
import com.wyd.web.model.zm.IREQUEST;

import java.io.Serializable;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ZmCommonResult<T> implements Serializable {

    @SerializedName("I_REQUEST")
    private IREQUEST<T> iREQUEST;

    public IREQUEST getIREQUEST() {
        return iREQUEST;
    }

    public void setIREQUEST(IREQUEST<T> iREQUEST) {
        this.iREQUEST = iREQUEST;
    }

}
