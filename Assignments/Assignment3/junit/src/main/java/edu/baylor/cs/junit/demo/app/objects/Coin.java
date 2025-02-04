package edu.baylor.cs.junit.demo.app.objects;

/**
 * Coin enum
 * 
 * @author cerny
 *
 */
public enum Coin {
	zero(0f), cent(.01f), dime(.10f), nicle(.05f), quarter(.25f), dollar(1.0f);

	public float value;

	Coin(float value) {
		this.value = value;
	};
}
