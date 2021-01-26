package com.vanderkast.smsforward.model;

public class Input {
    private final String date;
    private final String number;
    private final String email;
    private final boolean asCsvFile;

    public String getEmail() {
        return email;
    }

    public static final class Builder {
        private String date;
        private String number;
        private String email;
        private boolean asCsvFile;

        public Input build() {
            return new Input(date, number, email, asCsvFile);
        }

        public Builder setDate(String date) {
            this.date = date;
            return this;
        }

        public Builder setNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder setAsCsvFile(boolean asCsvFile) {
            this.asCsvFile = asCsvFile;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }
    }

    public Input(String date, String number, String email, boolean asCsvFile) {
        this.date = date;
        this.number = number;
        this.email = email;
        this.asCsvFile = asCsvFile;
    }

    public String getDate() {
        return date;
    }

    public String getNumber() {
        return number;
    }

    public boolean isAsCsvFile() {
        return asCsvFile;
    }
}
