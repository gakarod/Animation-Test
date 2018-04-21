package com.pointless;


import com.badlogic.gdx.graphics.Texture;

public interface ImageLoaderCore {
    void loadImage(String url);
    void loadImage(String url, int width, int height);
    void setOnImageLoadedListener(OnImageLoaded listener);
    Texture getImage();
}
