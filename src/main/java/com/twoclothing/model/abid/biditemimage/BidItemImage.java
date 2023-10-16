package com.twoclothing.model.abid.biditemimage;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.*;


@Entity
@Table(name = "biditemimage")
public class BidItemImage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imageid", updatable = false)
    private Integer imageId;

    @Column(name = "biditemid", updatable = false, nullable = false)
    private Integer bidItemId;

    @Column(name = "image", nullable = false, columnDefinition = "mediumblob")
    private byte[] image;

    public BidItemImage() {

    }

    public BidItemImage(Integer imageId, Integer bidItemId, byte[] image) {
        super();
        this.imageId = imageId;
        this.bidItemId = bidItemId;
        this.image = image;
    }

    @Override
    public String toString() {
        return "BidItemImage [imageId=" + imageId + ", bidItemId=" + bidItemId + ", image=" + Arrays.toString(image)
                + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidItemImage that = (BidItemImage) o;
        return Objects.equals(imageId, that.imageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId);
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getBidItemId() {
        return bidItemId;
    }

    public void setBidItemId(Integer bidItemId) {
        this.bidItemId = bidItemId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


}
