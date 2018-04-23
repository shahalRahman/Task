package com.shahal.assignmentproject.model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by shahal on 23-04-2018.
 */

public class AnnouncementData {
    @SerializedName("ID")
    private DataFormat id;

    @SerializedName("NATIVE_DATE")
    private DataFormat nativeDate;

    @SerializedName("ANNOUNCEMENT_DATE")
    private DataFormat announcementDate;

    @SerializedName("EXPIRY")
    private DataFormat expiry;

    @SerializedName("ANNOUNCEMENT_DESCRIPTION")
    private DataFormat announcementDescription;

    @SerializedName("ANNOUNCEMENT_TITLE")
    private DataFormat announcementTitle;

    @SerializedName("ANNOUNCEMENT_IMAGE")
    private DataFormat announcementImage;

    @SerializedName("ANNOUNCEMENT_IMAGE_THUMBNAIL")
    private DataFormat announcementImageThumbnail;

    @SerializedName("ANNOUNCEMENT_HTML")
    private DataFormat announcementHtml;

    public DataFormat getId() {
        return id;
    }

    public DataFormat getNativeDate() {
        return nativeDate;
    }

    public DataFormat getAnnouncementDate() {
        return announcementDate;
    }

    public DataFormat getExpiry() {
        return expiry;
    }

    public DataFormat getAnnouncementDescription() {
        return announcementDescription;
    }

    public DataFormat getAnnouncementTitle() {
        return announcementTitle;
    }

    public DataFormat getAnnouncementImage() {
        return announcementImage;
    }

    public DataFormat getAnnouncementImageThumbnail() {
        return announcementImageThumbnail;
    }

    public DataFormat getAnnouncementHtml() {
        return announcementHtml;
    }

    public class DataFormat {
        @SerializedName("Tag")
        private String tag;

        @SerializedName("TypeCode")
        private int typeCode;

        @SerializedName("Value")
        private String value;

        @SerializedName("IsBinaryUnique")
        private boolean isBinaryUnique;

        public String getTag() {
            return tag;
        }

        public int getTypeCode() {
            return typeCode;
        }

        public String getValue() {
            return value;
        }

        public boolean isBinaryUnique() {
            return isBinaryUnique;
        }
    }
}

