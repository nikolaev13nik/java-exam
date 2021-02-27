package com.ngsoft.part2.SetGame;

public class Card {

	private CardFeatures.Shape shape;
	private CardFeatures.Color color;
	private CardFeatures.ItemCount itemCount;
	private CardFeatures.Texture texture;

	public static Card createNew(CardFeatures.Shape shape, CardFeatures.Color color, CardFeatures.ItemCount itemCount,
			CardFeatures.Texture texture) {
		return new Card(shape, color, itemCount, texture);
	}

	public Card(CardFeatures.Shape shape, CardFeatures.Color color, CardFeatures.ItemCount itemCount,
			CardFeatures.Texture texture) {
		this.shape = shape;
		this.color = color;
		this.itemCount = itemCount;
		this.texture = texture;
	}

	public CardFeatures.Shape getShape() {
		return shape;
	}

	public CardFeatures.Color getColor() {
		return color;
	}

	public CardFeatures.ItemCount getItemCount() {
		return itemCount;
	}

	public CardFeatures.Texture getTexture() {
		return texture;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((itemCount == null) ? 0 : itemCount.hashCode());
		result = prime * result + ((shape == null) ? 0 : shape.hashCode());
		result = prime * result + ((texture == null) ? 0 : texture.hashCode());
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
		Card other = (Card) obj;
		if (color != other.color)
			return false;
		if (itemCount != other.itemCount)
			return false;
		if (shape != other.shape)
			return false;
		if (texture != other.texture)
			return false;
		return true;
	}

	@Override
	public Card clone() {
		return new Card(shape, color, itemCount, texture);
	}
}
