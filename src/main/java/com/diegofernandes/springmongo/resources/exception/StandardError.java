package com.diegofernandes.springmongo.resources.exception;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class responsible for containing a basic structure of application error.
 *
 * @author  Diego Fernandes
 * @version 1.0.1
 * @since   2019-05-02
*/
public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public StandardError() {

    }

    public StandardError(Long timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StandardError)) return false;
        StandardError that = (StandardError) o;
        return Objects.equals(getTimestamp(), that.getTimestamp()) &&
                Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getError(), that.getError()) &&
                Objects.equals(getMessage(), that.getMessage()) &&
                Objects.equals(getPath(), that.getPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTimestamp(), getStatus(), getError(), getMessage(), getPath());
    }

    @Override
    public String toString() {
        return "StandardError{" +
                "timestamp=" + timestamp +
                ", status=" + status +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

}
