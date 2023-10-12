package com.twoclothing.web.abid.biditemimage;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="biditemimage")
public class BidItemImage implements Serializable{
	
	@Id
	@Column(name ="imageid")
	private Integer imageId;
	
	@Column(name ="biditemid")
	private Integer bidItemId;
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
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(image);
			result = prime * result + Objects.hash(bidItemId, imageId);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			BidItemImage other = (BidItemImage) obj;
			return Objects.equals(bidItemId, other.bidItemId) && Arrays.equals(image, other.image)
					&& Objects.equals(imageId, other.imageId);
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
