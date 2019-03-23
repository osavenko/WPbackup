package com.homework;

public class WPField {
    private String name;
    private String type;
    private boolean primary;
    private boolean autoincrement;
    private String defaultValue;
    private boolean index;

    public boolean isIndex() {
        return index;
    }

    public void setIndex(boolean index) {
        this.index = index;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public void setNullable(boolean nullable) {
        isNullable = nullable;
    }

    private boolean isNullable;

    public boolean isAutoincrement() {
        return autoincrement;
    }

    public void setAutoincrement(boolean autoincrement) {
        this.autoincrement = autoincrement;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public WPField(String name, String type, boolean primary, boolean autoincrement, String defaultValue) {
        this.name = name;
        this.type = type;
        this.primary = primary;
        this.autoincrement = autoincrement;
        this.defaultValue = defaultValue;
    }

    public WPField(){}

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public WPField(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public WPField(String name, String type, boolean primary) {
        this.name = name;
        this.type = type;
        this.primary = primary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
