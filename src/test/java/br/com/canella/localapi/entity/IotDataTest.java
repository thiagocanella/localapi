package br.com.canella.localapi.entity;

import org.junit.jupiter.api.*;

public class IotDataTest {
	@Test
	public void prePersist() {
		IotData i = new IotData();
		i.prePersist();
	}
}
