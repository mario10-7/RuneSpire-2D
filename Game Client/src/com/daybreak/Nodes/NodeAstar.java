package com.daybreak.Nodes;


import com.badlogic.gdx.math.Vector2;
import com.daybreak.Util.Vector2i;


public class NodeAstar {
		
	public Vector2i tile;
		public NodeAstar parent;
		public double fCost, gCost, hCost;

		public NodeAstar(Vector2i tile, NodeAstar parent, double gCost, double hCost) {
			this.tile = tile;
			this.parent = parent;
			this.gCost = gCost;
			this.hCost = hCost;
			this.fCost = this.gCost + this.hCost;
		}
	}
