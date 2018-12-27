package com.future.model;

import org.springframework.data.annotation.Id;

public class Sequences {
  @Id
        private String id;

        private long seq;

        //getters and setters omitted

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }
}
