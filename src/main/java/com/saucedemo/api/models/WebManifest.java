package com.saucedemo.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

// saucedemo's /manifest.json: the app's name, colours and icons.
@JsonIgnoreProperties(ignoreUnknown = true)
public record WebManifest(String name,
                          @JsonProperty("short_name") String shortName,
                          @JsonProperty("start_url") String startUrl,
                          String display,
                          @JsonProperty("theme_color") String themeColor,
                          @JsonProperty("background_color") String backgroundColor,
                          List<ManifestIcon> icons) {

    public record ManifestIcon(String src, String sizes, String type) {
    }
}
