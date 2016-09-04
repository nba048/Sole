package com.scm.pe.sole.entity.root;

import com.scm.pe.sole.entity.Jok;

import java.util.List;

/**
 * Created by Administrator on 2016/9/2 0002.
 */
public class RootJok {
    private int error_code;

    private String reason;

    private Result result;

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public int getError_code() {
        return this.error_code;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return this.reason;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return this.result;
    }


    public class Result {
        private List<Jok> data;

        public void setData(List<Jok> data) {
            this.data = data;
        }

        public List<Jok> getData() {
            return this.data;
        }

    }
}
