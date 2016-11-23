package com.barranquero.deportes.model;

/**
 * Model class for each sport.
 */
public class Sport {
    private int id;
    private int imageId;
    private String name;
    private boolean liked;

    /**
     * Constructor
     * @param id         Sport id
     * @param imageId    Image ID in drawable
     * @param name       Sport name
     */
    public Sport(int id, int imageId, String name) {
        this.id = id;
        this.imageId = imageId;
        this.name = name;
        this.liked = false;
    }

    public int getId() {
        return id;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
