package com.esnefedroetem.meteordefense.model;

import static org.junit.Assert.*;

import org.junit.Test;

import com.esnefedroetem.meteordefense.model.Projectile;

public class ProjectileTest {

	
	
	@Test
	public void test() {
	}
	
	@Test
	public void testMove(){
		Projectile proj = new Projectile((float)(2*Math.PI/3));
			float x = proj.getX();
			float y = proj.getY();
			proj.move(0.2f);
			assertTrue(proj.getX()!=x && proj.getY()>y);
	}

}
