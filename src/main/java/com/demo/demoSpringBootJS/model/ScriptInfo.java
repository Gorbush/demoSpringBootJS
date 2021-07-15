package com.demo.demoSpringBootJS.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ScriptInfo {
  /*  id:4
    text: console.log(1+2);
    status: 'complete'
    time_added: 2021-07-08 12:00
    time_started: 2021-07-08 12:01
    time_finished: 2021-07-08 12:02
    result: '3'

   */

    public enum ScriptStatus {
        ENQUEUED,
        RUNNING,
        COMPLETE,
        FAILED
    }


    private Integer id; //autogeneration
    private String textScript;
    private ScriptStatus status;
    private Date timeAdded;
    private Date timeStarted;
    private Date timeFinished;
    private String result;

    public String getTextScript() {
        return textScript;
    }

    public void setTextScript(String textScript) {
        this.textScript = textScript;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ScriptStatus getStatus() {
        return status;
    }

    public void setStatus(ScriptStatus status) {
        this.status = status;
    }

    public Date getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Date timeAdded) {
        this.timeAdded = timeAdded;
    }

    public Date getTimeStarted() {
        return timeStarted;
    }

    public void setTimeStarted(Date timeStarted) {
        this.timeStarted = timeStarted;
    }

    public Date getTimeFinished() {
        return timeFinished;
    }

    public void setTimeFinished(Date timeFinished) {
        this.timeFinished = timeFinished;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScriptInfo that = (ScriptInfo) o;
        return Objects.equals(id, that.id) && Objects.equals(textScript, that.textScript) && Objects.equals(status, that.status) && Objects.equals(timeAdded, that.timeAdded) && Objects.equals(timeStarted, that.timeStarted) && Objects.equals(timeFinished, that.timeFinished) && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, textScript, status, timeAdded, timeStarted, timeFinished, result);
    }

    @Override
    public String toString() {
        return "ScriptInfo{" +
                "id=" + id +
                ", textScript='" + textScript + '\'' +
                ", statusId=" + status +
                ", time_added=" + timeAdded +
                ", time_started=" + timeStarted +
                ", time_finished=" + timeFinished +
                ", result='" + result + '\'' +
                '}';
    }
}
