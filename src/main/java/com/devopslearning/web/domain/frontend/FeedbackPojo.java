package com.devopslearning.web.domain.frontend;

import java.io.Serializable;

public class FeedbackPojo implements Serializable {

    public static final long serialVersionUID = 1l;

    private String email;
    private String firstName;
    private String lastName;
    private String feedback;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFeedback() {
        return feedback;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FeedbackPojo{");
        sb.append("email='").append(email).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", feedback='").append(feedback).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
