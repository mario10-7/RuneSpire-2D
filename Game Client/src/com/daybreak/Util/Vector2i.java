package com.daybreak.Util;


public class Vector2i {

	float x;
	float y;

	public Vector2i(float x, float y) {
		set(x, y);
	}

	public Vector2i(Vector2i vector) {
		set(vector.x, vector.y);
	}

	public Vector2i() {
		set(0, 0);
	}
	
	public Vector2i add(Vector2i vector){
		this.x += vector.x;
		this.y += vector.y;
		return this;
	}

	public Vector2i substract(Vector2i vector){
		this.x -= vector.x;
		this.y -= vector.y;
		return this;
	}
	
	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Vector2i setX(float x) {
		this.x = x;
		return this;
	}

	public Vector2i setY(float y) {
		this.y = y;
		return this;
	}

	public boolean equals(Object object) {
		if (!(object instanceof Vector2i)) return false;
	    Vector2i vec = (Vector2i) object;
	    if (vec.getX() == this.getX() && vec.getY() == this.getY()) return true;
		return false;
	}
}
